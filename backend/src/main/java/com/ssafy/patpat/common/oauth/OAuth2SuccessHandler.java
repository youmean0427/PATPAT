package com.ssafy.patpat.common.oauth;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ssafy.patpat.common.security.filter.JwtFilter;
import com.ssafy.patpat.common.security.jwt.TokenProvider;
import com.ssafy.patpat.user.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(OAuth2SuccessHandler.class);
    private final TokenProvider tokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException{

        System.out.println(authentication);
        TokenDto token = new TokenDto();
        LOGGER.info("토큰 발행 시작");
        String accessToken = tokenProvider.createAccessToken(authentication);
        String refreshToken = tokenProvider.createRefreshToken();

        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.ACCESSTOKEN_HEADER, "Bearer " + token.getAccessToken());
        httpHeaders.add(JwtFilter.REFRESHTOKEN_HEADER, "Bearer " + token.getRefreshToken());

        LOGGER.info("access : {}", accessToken);
        LOGGER.info("refresh : {}", refreshToken);

        response.setHeader(JwtFilter.ACCESSTOKEN_HEADER, "Bearer " + token.getAccessToken());
        response.setHeader(JwtFilter.REFRESHTOKEN_HEADER, "Bearer " + token.getRefreshToken());

        JsonObject responseJson = new JsonObject();
        Gson gson = new Gson();
        String tokenJson = gson.toJson(token);

        response.getWriter().print(tokenJson);

        response.sendRedirect("http://localhost:3000/login/token");
    }
}