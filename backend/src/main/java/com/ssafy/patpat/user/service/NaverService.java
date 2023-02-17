package com.ssafy.patpat.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.patpat.common.security.jwt.TokenProvider;
import com.ssafy.patpat.user.dto.TokenDto;
import com.ssafy.patpat.user.dto.UserDto;
import com.ssafy.patpat.user.dto.UserResponseDto;
import com.ssafy.patpat.user.entity.Authority;
import com.ssafy.patpat.user.entity.User;
import com.ssafy.patpat.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NaverService {

    @Value("${spring.security.oauth2.client.registration.naver.redirect-uri}")
    String redirectUri;

    @Value("${spring.security.oauth2.client.registration.naver.client-id}")
    String clientId;

    @Value("${spring.security.oauth2.client.registration.naver.client-secret}")
    String clientSecret;

    @Value("${spring.security.oauth2.client.provider.naver.token-uri}")
    String tokenUri;

    @Value("${spring.security.oauth2.client.provider.naver.user-info-uri}")
    String userInfoUri;

    @Value("${spring.security.oauth2.client.registration.naver.authorization-grant-type}")
    String grantType;


    // 1. "인가 코드"로 "액세스 토큰" 요청
    @Transactional
    public String getAccessToken(String code) throws JsonProcessingException {
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP params 생성
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(tokenUri)
                .queryParam("grant_type", grantType)
                .queryParam("client_id", clientId)
                .queryParam("client_secret", clientSecret)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("code", code);
//                .queryParam("state", state);

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(headers);

        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                tokenRequest,
                String.class
        );

        // HTTP 응답 (JSON) -> 액세스 토큰 파싱
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        return jsonNode.get("access_token").asText();
    }

    // 2. 토큰으로 카카오 API 호출
    @Transactional
    public UserDto getUserInfo(String accessToken) throws JsonProcessingException {
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP 요청 보내기
        HttpEntity<MultiValueMap<String, String>> userInfoRequest = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                userInfoUri,
                HttpMethod.GET,
                userInfoRequest,
                String.class
        );

        // responseBody에 있는 정보를 꺼냄
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        UserDto user = new UserDto();

        JsonNode infoResponse = jsonNode.get("response");

        System.out.println(infoResponse);
        String id = infoResponse.get("id").asText();

        String email = infoResponse.get("email").asText();
        String nickname = infoResponse.get("nickname").asText();
        String profileImageUrl = infoResponse.get("profile_image").asText();
        String ageRange = infoResponse.get("age").asText();

        user.setEmail(email);
        user.setUsername(nickname);
        user.setProvider("naver");
        user.setProviderId(id);
        user.setAgeRange(ageRange);
        user.setProfileImageUrl(profileImageUrl);

        return user;
    }
}
