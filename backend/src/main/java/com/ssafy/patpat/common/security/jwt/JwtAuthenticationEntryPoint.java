package com.ssafy.patpat.common.security.jwt;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.ssafy.patpat.common.error.ErrorCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

// 401 에러
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        String exception = (String) request.getAttribute("exception");
        if(exception == null) {
            setResponse(response, ErrorCode.UNKNOWN_ERROR);
        }
        //잘못된 타입의 토큰인 경우
        else if(exception.equals(ErrorCode.WRONG_TYPE_TOKEN.getCode())) {
            setResponse(response, ErrorCode.WRONG_TYPE_TOKEN);
        }
        //토큰 만료된 경우
        else if(exception.equals(ErrorCode.EXPIRED_TOKEN.getCode())) {
            setResponse(response, ErrorCode.EXPIRED_TOKEN);
        }
        //지원되지 않는 토큰인 경우
        else if(exception.equals(ErrorCode.UNSUPPORTED_TOKEN.getCode())) {
            setResponse(response, ErrorCode.UNSUPPORTED_TOKEN);
        }
        else {
            setResponse(response, ErrorCode.ACCESS_DENIED);
        }
    }
    //한글 출력을 위해 getWriter() 사용
    private void setResponse(HttpServletResponse response, ErrorCode exceptionCode) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        JsonObject responseJson = new JsonObject();
        responseJson.addProperty("message", exceptionCode.getMessage());
        responseJson.addProperty("code", exceptionCode.getCode());

        response.getWriter().print(responseJson);
    }
}