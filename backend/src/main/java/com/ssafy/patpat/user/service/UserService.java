package com.ssafy.patpat.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.common.entity.Image;
import com.ssafy.patpat.common.redis.RedisService;
import com.ssafy.patpat.common.redis.RefreshRedis;
import com.ssafy.patpat.common.redis.RefreshRedisRepository;
import com.ssafy.patpat.common.security.filter.JwtFilter;
import com.ssafy.patpat.common.security.jwt.TokenProvider;
import com.ssafy.patpat.common.service.FileService;
import com.ssafy.patpat.common.util.SecurityUtil;
import com.ssafy.patpat.protect.entity.ShelterProtectedDog;
import com.ssafy.patpat.protect.repository.ShelterProtectedDogRepository;
import com.ssafy.patpat.user.dto.*;
import com.ssafy.patpat.user.entity.Authority;
import com.ssafy.patpat.user.entity.User;
import com.ssafy.patpat.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final ShelterProtectedDogRepository shelterProtectedDogRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    private final FileService fileService;

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
        userDto.setUsername(user.getNickname());
        userDto.setProfileImageUrl(fileService.getFileUrl(user.getImage()));
        userResponseDto.setUserDto(userDto);

        return userResponseDto;
    }

    @Transactional
    public User signup(UserDto userDto){

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        List<Authority> list = new ArrayList<>();
        list.add(authority);

        Set<ShelterProtectedDog> favoriteDogs = new HashSet<>();

        String password = passwordEncoder.encode(userDto.getProvider() + userDto.getProviderId());

        Image image = fileService.insertFileUrl(userDto.getProfileImageUrl(), userDto.getProvider());

        User user = User.builder()
                .email(userDto.getEmail())
                .ageRange(userDto.getAgeRange())
                .provider(userDto.getProvider())
                .providerId(userDto.getProviderId())
                .password(password)
                .nickname(userDto.getUsername())
                .image(image)
                .authorities(list)
                .favoriteDogs(favoriteDogs)
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

    /**
     * 로그아웃
     *  */
    @Transactional
    public ResponseMessage logout(TokenDto tokenDto) throws Exception{
        ResponseMessage responseMessage = new ResponseMessage();
        String accessToken = tokenProvider.resolveToken(tokenDto.getAccessToken());
        String refreshToken = tokenProvider.resolveToken(tokenDto.getRefreshToken());

        // 토큰 유효성 검사
        if(!tokenProvider.validateToken(accessToken)){
            responseMessage.setMessage("FAIL");
        }else{
            // 토큰이 유효하다면 해당 토큰의 남은 기간과 함께 redis에 logout으로 저장
            long validExpiration = tokenProvider.getExpiration(accessToken);
            redisService.setLogoutValues(accessToken, validExpiration);

            // redis에 저장된 refresh 토큰 삭제
            if(redisService.getValues(refreshToken) != null){
                redisService.delValues(refreshToken);
            }
            responseMessage.setMessage("SUCCESS");
        }
        return responseMessage;
    }

    /**
     * 회원 정보 수정
     *  */
    @Transactional
    public ResponseMessage updateUser(UserDto userDto, MultipartFile profileFile) throws Exception{
        ResponseMessage responseMessage = new ResponseMessage();
        log.info("userId : " + userDto.getUserId() );
        System.out.println(userDto.getUserId());
        // userId가 넘어오질 않아 null이 될수도 있는 경우 예외처리해야함
        User user = userRepository.findOneWithAuthoritiesByUserId(userDto.getUserId()).get();

        if(userDto.getUsername() != null){
            user.setNickname(userDto.getUsername());
        }
        if(profileFile != null){
            Image userImage = user.getImage();
            fileService.deleteFile(userImage);
            Image image = fileService.insertFile(profileFile);
            user.setImage(image);
        }

        userRepository.save(user);

        responseMessage.setMessage("SUCCESS");

        return responseMessage;
    }

    /**
     * 회원 탈퇴
     *  */
    @Transactional
    public ResponseMessage deleteUser(TokenDto tokenDto, Long userId) throws Exception {
        ResponseMessage responseMessage = logout(tokenDto);
        User user = userRepository.findOneWithAuthoritiesByUserId(userId).get();
        Image userImage = user.getImage();
        fileService.deleteFile(userImage);
        userRepository.delete(user);

        responseMessage.setMessage("SUCCESS");

        return responseMessage;

    }

    @Transactional(readOnly = true)
    public UserDto getUserWithAuthorities() {
        Optional<User> user = SecurityUtil.getCurrentEmail().flatMap(userRepository::findOneWithAuthoritiesByEmail);
        if(user.orElse(null) == null) {

            return null;

        }else{
            UserDto userDto = UserDto.builder()
                    .userId(user.get().getUserId())
                    .provider(user.get().getProvider())
                    .email(user.get().getEmail())
                    .username(user.get().getNickname())
                    .ageRange(user.get().getAgeRange())
                    .profileImageUrl(fileService.getFileUrl(user.get().getImage()))
                    .providerId(user.get().getProviderId())
                    .build();
            return userDto;
        }
    }

    /**
     * 찜 목록
     * */
    @Transactional(readOnly = true)
    public List<FavoriteDto> getFavoriteDogs(UserDto userDto){
        Optional<User> user = userRepository.findWithFavoriteDogsByUserId(userDto.getUserId());
        if(!user.isPresent()){
            return null;
        }

        List<FavoriteDto> list = new ArrayList<>();
        Set<ShelterProtectedDog> dogs = user.get().getFavoriteDogs();
        for (ShelterProtectedDog dog:
             dogs) {
            list.add(FavoriteDto.builder()
                    .spDogId(dog.getSpDogId())
                    .userId(user.get().getUserId())
                    .name(dog.getName())
                    .imageUrl(fileService.getFileUrl(dog.getImages().get(0)))
                    .stateCode(dog.getStateCode().getCode())
                    .state(dog.getStateCode().name())
                    .weight(dog.getWeight())
                    //.neutered(dog.isNeutered())
                    .gender(dog.getGender().name())
                    .genderCode(dog.getGender().getCode())
                    .age(dog.getAge())
                    .build());
        }
        return list;
    }

    /**
     * 찜 등록
     * */
    @Transactional
    public boolean insertFavoriteDogs(Long userId, int protectId){
        LOGGER.info("user {}", userId);
        Optional<User> user = userRepository.findWithFavoriteDogsByUserId(userId);
        if(!user.isPresent()){
            return false;
        }

        LOGGER.info("userEmail {} : ", user.get().getEmail());
        Set<ShelterProtectedDog> dogs = user.get().getFavoriteDogs();
        if(dogs.isEmpty()){
            LOGGER.info("최초 관심 동물 등록");
        }
        dogs.add(shelterProtectedDogRepository.findById(protectId).get());

        user.get().setFavoriteDogs(dogs);

        userRepository.save(user.get());
        return true;
    }

    /**
     * 찜 해제
     * */
    @Transactional
    public boolean deleteFavoriteDogs(Long userId, int protectId){
        LOGGER.info("user {}", userId);
        Optional<User> user = userRepository.findWithFavoriteDogsByUserId(userId);
        if(!user.isPresent()){
            return false;
        }

        LOGGER.info("userEmail {} : ", user.get().getEmail());
        Set<ShelterProtectedDog> dogs = user.get().getFavoriteDogs();
        if(dogs.isEmpty()){
            LOGGER.info("삭제할 것이 없습니다.");
            return false;
        }else{
            dogs.remove(shelterProtectedDogRepository.findById(protectId).get());
        }


        user.get().setFavoriteDogs(dogs);

        userRepository.save(user.get());
        return true;
    }

    /**
     * 사진 넣기 용도 입니다.
      */
    @Transactional
    public boolean insertImage(List<MultipartFile> profileFile) throws Exception{

        for (MultipartFile m:
             profileFile) {
            fileService.insertFile(m);
        }

        return true;
    }

}
