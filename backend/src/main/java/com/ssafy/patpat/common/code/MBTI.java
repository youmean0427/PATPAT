package com.ssafy.patpat.common.code;

import java.util.Arrays;

public enum MBTI {
    infj(1),intj(2),istp(3),istj(4),intp(5),isfj(6),isfp(7),infp(8),entp(9),estp(10),estj(11),entj(12),enfp(13),esfj(14),enfj(15),esfp(16);
    private int code ;
    private MBTI(int code){
        this.code = code;
    }
    public int getCode(){
        return code;
    }

    public static MBTI of(int code){
        return Arrays.stream(values())
                .filter(value -> value.getCode()==code)
                .findFirst()
                .orElse(null);
    }
}
