package com.ssafy.patpat.user.service;

import java.util.Collections;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import com.ssafy.patpat.user.dto.KakaoUserDto;
import com.ssafy.patpat.user.dto.Oauth2UserInfo;
import com.ssafy.patpat.user.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        Oauth2UserInfo oAuth2UserInfo = null;
        String provider = userRequest.getClientRegistration().getRegistrationId(); // 소셜 정보 가져옴
        oAuth2UserInfo = new KakaoUserDto(oAuth2User.getAttributes());

        String providerId = oAuth2UserInfo.getProviderId();
        String username = provider+"_"+providerId;

        String uuid = UUID.randomUUID().toString().substring(0, 6);
        String password = bCryptPasswordEncoder.encode("패스워드"+uuid);

        String email = oAuth2UserInfo.getEmail();
    }

    private User saveOrUpdate(OAuthAttributes attributes){
        User user =  userRepository.findOneByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }

}