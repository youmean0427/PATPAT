package com.ssafy.patpat.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.patpat.common.redis.RedisService;
import com.ssafy.patpat.common.redis.RefreshRedis;
import com.ssafy.patpat.common.redis.RefreshRedisRepository;
import com.ssafy.patpat.common.security.jwt.TokenProvider;
import com.ssafy.patpat.common.util.SecurityUtil;
import com.ssafy.patpat.user.dto.ResultDto;
import com.ssafy.patpat.user.dto.TokenDto;
import com.ssafy.patpat.user.dto.UserDto;
import com.ssafy.patpat.user.dto.UserResponseDto;
import com.ssafy.patpat.user.entity.Authority;
import com.ssafy.patpat.user.entity.User;
import com.ssafy.patpat.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    private final KakaoService kakaoService;
    private final NaverService naverService;

    private final GoogleService googleService;

    private final RedisService redisService;


    @Transactional
    public UserResponseDto login(String provider, String code) throws JsonProcessingException {
        UserDto userDto;
        if(provider.equals("kakao")){
            String kakaoAccessToken = kakaoService.getAccessToken(code);
            userDto = kakaoService.getUserInfo(kakaoAccessToken);
        }else if(provider.equals("naver")){
            String naverAccessToken = naverService.getAccessToken(code);
            userDto = naverService.getUserInfo(naverAccessToken);
        }else{
            String googleAccessToken = googleService.getAccessToken(code);
            userDto = googleService.getUserInfo(googleAccessToken);
        }

        Optional<User> userOptional = userRepository.findOneWithAuthoritiesByEmail(userDto.getEmail());
        User user;
        if(userOptional.orElse(null) == null) {
            user = signup(userDto);

        }else{
            user = userOptional.get();
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getProvider() + user.getProviderId());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        TokenDto token = new TokenDto();

        /** 토큰 생성 */
        String accessToken = tokenProvider.createAccessToken(authentication);
        String refreshToken = tokenProvider.createRefreshToken(authentication);

        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);

        /** 리프레쉬 토큰 레디스 저장 */
        redisService.setValues(refreshToken, user.getEmail());

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setTokenDto(token);
        userResponseDto.setUserDto(user);

        return userResponseDto;
    }

    @Transactional
    public User signup(UserDto userDto){

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        List<Authority> list = new ArrayList<>();
        list.add(authority);

        String password = passwordEncoder.encode(userDto.getProvider() + userDto.getProviderId());

        User user = User.builder()
                .email(userDto.getEmail())
                .ageRange(userDto.getAgeRange())
                .profileImage(userDto.getProfileImageUrl())
                .provider(userDto.getProvider())
                .providerId(userDto.getProviderId())
                .password(password)
                .nickname(userDto.getUsername())
                .authorities(list)
                .build();

        return userRepository.save(user);
    }

    /** access token이 만료되기 일보 직전이라 access만 재발급할 때 */
    @Transactional
    public TokenDto refresh(String refreshToken){
        TokenDto token = new TokenDto();

        if(!tokenProvider.checkRefreshToken(refreshToken)){
            // 추후 예외 처리 예정
            return null;
        }
        Authentication authentication = tokenProvider.getAuthentication(tokenProvider.resolveToken(refreshToken));

        String accessToken = tokenProvider.createAccessToken(authentication);

        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken.substring(7));
        
        return token;
    }

    /** access token이 만료됐지만 refresh token은 살아 있어서 둘다 재발급할 때 */
    @Transactional
    public TokenDto reissue(String refreshToken){
        TokenDto token = new TokenDto();

        if(!tokenProvider.checkRefreshToken(refreshToken)){
            // 추후 예외 처리 예정
            return null;
        }
        Authentication authentication = tokenProvider.getAuthentication(tokenProvider.resolveToken(refreshToken));
        redisService.delValues(refreshToken);

        String accessToken = tokenProvider.createAccessToken(authentication);
        String newRefreshToken = tokenProvider.createRefreshToken(authentication);

        String email = SecurityUtil.getCurrentEmail().get();

        redisService.setValues(newRefreshToken, email);
        token.setAccessToken(accessToken);
        token.setRefreshToken(newRefreshToken);


        return token;
    }

    @Transactional
    public ResultDto logout(TokenDto tokenDto) throws Exception{
        ResultDto resultDto = new ResultDto();
        String accessToken = tokenProvider.resolveToken(tokenDto.getAccessToken());
        String refreshToken = tokenProvider.resolveToken(tokenDto.getRefreshToken());

        // 토큰 유효성 검사
        if(!tokenProvider.validateToken(accessToken)){
            resultDto.setResult("fail");
        }else{
            // 토큰이 유효하다면 해당 토큰의 남은 기간과 함께 redis에 logout으로 저장
            long validExpiration = tokenProvider.getExpiration(accessToken);
            redisService.setLogoutValues(accessToken, validExpiration);

            // redis에 저장된 refresh 토큰 삭제
            if(redisService.getValues(refreshToken) != null){
                redisService.delValues(refreshToken);
            }
            resultDto.setResult("success");
        }
        return resultDto;
    }

    @Transactional
    public ResultDto updateUser(UserDto userDto){
        ResultDto resultDto = new ResultDto();
        User user = userRepository.findOneWithAuthoritiesById(userDto.getUserId()).get();

        user.setNickname(userDto.getUsername());
        user.setProfileImage(userDto.getProfileImageUrl());

        userRepository.save(user);

        resultDto.setResult("success");

        return resultDto;
    }

    @Transactional
    public ResultDto deleteUser(TokenDto tokenDto, UserDto userDto) throws Exception {

        ResultDto resultDto = logout(tokenDto);
        User user = userRepository.findOneWithAuthoritiesById(userDto.getUserId()).get();
        userRepository.delete(user);

        resultDto.setResult("success");

        return resultDto;

    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(String email) {
        return userRepository.findOneWithAuthoritiesByEmail(email);
    }

    @Transactional(readOnly = true)
    public Optional<User> getMyUserWithAuthorities(){
        return SecurityUtil.getCurrentEmail().flatMap(userRepository::findOneWithAuthoritiesByEmail);
    }
}
