package com.ssafy.patpat.user.service;

import java.util.ArrayList;
import java.util.List;
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

        System.out.println("여기옴?");
        OAuth2User oAuth2User = super.loadUser(userRequest);

        Oauth2UserInfo oAuth2UserInfo = null;
        String provider = userRequest.getClientRegistration().getRegistrationId(); // 소셜 정보 가져옴
        oAuth2UserInfo = new KakaoUserDto(oAuth2User.getAttributes());

        String providerId = oAuth2UserInfo.getProviderId();
        String username = provider+"_"+providerId;

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        List<Authority> list = new ArrayList<>();
        list.add(authority);

        String uuid = UUID.randomUUID().toString().substring(0, 6);
        String password = bCryptPasswordEncoder.encode("패스워드"+uuid);

        String email = oAuth2UserInfo.getEmail();

        User user = userRepository.findByEmail(email).get();

        if(user == null){

            user = User.builder()
                    .email(email)
                    .provider(provider)
                    .providerId(providerId)
                    .password(password)
                    .nickname(username)
                    .authorities(list)
                    .activated(true)
                    .build();

            userRepository.save(user);
        }

        return new CustomDetails(user, oAuth2UserInfo);
    }

}