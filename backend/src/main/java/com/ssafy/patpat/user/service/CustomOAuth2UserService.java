package com.ssafy.patpat.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import com.ssafy.patpat.common.details.CustomDetails;
import com.ssafy.patpat.user.dto.KakaoUserDto;
import com.ssafy.patpat.user.dto.Oauth2UserInfo;
import com.ssafy.patpat.user.dto.UserDto;
import com.ssafy.patpat.user.entity.Authority;
import com.ssafy.patpat.user.entity.User;
import com.ssafy.patpat.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService{

    private final UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        Oauth2UserInfo oAuth2UserInfo = null;
        String provider = userRequest.getClientRegistration().getRegistrationId(); // 소셜 정보 가져옴
        oAuth2UserInfo = new KakaoUserDto(oAuth2User.getAttributes());

        String providerId = oAuth2UserInfo.getProviderId();
        String username = oAuth2UserInfo.getName();

        String profileImage = oAuth2UserInfo.getProfileImageUrl();
        String ageRange = oAuth2UserInfo.getAgeRange();

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        List<Authority> list = new ArrayList<>();
        list.add(authority);

        String password = bCryptPasswordEncoder.encode(provider+providerId);

        String email = oAuth2UserInfo.getEmail();

        Optional<User> userOptional = userRepository.findOneWithAuthoritiesByEmail(email);
        User user;
        if(userOptional.orElse(null) == null){

            user = User.builder()
                    .email(email)
                    .ageRange(ageRange)
                    .profileImage(profileImage)
                    .provider(provider)
                    .providerId(providerId)
                    .password(password)
                    .nickname(username)
                    .authorities(list)
                    .build();

            userRepository.save(user);
        }else{
            user = userOptional.get();
        }

        return null;
    }

}