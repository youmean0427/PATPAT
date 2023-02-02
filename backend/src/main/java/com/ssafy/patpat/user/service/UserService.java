package com.ssafy.patpat.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.patpat.common.security.jwt.TokenProvider;
import com.ssafy.patpat.common.util.SecurityUtil;
import com.ssafy.patpat.user.dto.TokenDto;
import com.ssafy.patpat.user.dto.UserDto;
import com.ssafy.patpat.user.dto.UserResponseDto;
import com.ssafy.patpat.user.entity.Authority;
import com.ssafy.patpat.user.entity.User;
import com.ssafy.patpat.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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

        String accessToken = tokenProvider.createAccessToken(authentication);
        String refreshToken = tokenProvider.createRefreshToken();

        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);

        user.setRefreshToken(refreshToken);
        user.setActivated(true);
        userRepository.save(user);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setTokenDto(token);
        userResponseDto.setUserDto(user);
        System.out.println(user.getEmail());

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
                .activated(true)
                .build();

        return userRepository.save(user);
    }

    @Transactional
    public TokenDto refresh(String refreshToken){
        TokenDto token = new TokenDto();

        User user = tokenProvider.checkRefreshToken(refreshToken);
        if(user != null){
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getProvider() + user.getProviderId());

            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String accessToken = tokenProvider.createAccessToken(authentication);

            token.setAccessToken(accessToken);
            token.setRefreshToken(refreshToken.substring(7));
        }
        
        return token;
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
