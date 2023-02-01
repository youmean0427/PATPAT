package com.ssafy.patpat.common.code.error;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum ErrorCode {
    UNKNOWN_ERROR("001","알 수 없음"),
    WRONG_TYPE_TOKEN("002", "잘못된 타입의 토큰입니다."),
    EXPIRED_TOKEN("003","만료된 토큰입니다."),
    UNSUPPORTED_TOKEN("004","지원하지않는 토큰입니다."),
    ACCESS_DENIED("005", "접근이 거부된 토큰입니다."),
    WRONG_TOKEN("006", "잘못된 토큰입니다.");


    private final String code;
    private final String message;

    ErrorCode(final String code, final String message){
        this.code = code;
        this.message = message;
    }
}
