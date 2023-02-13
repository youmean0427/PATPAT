package com.ssafy.patpat.common.code.category;

import com.ssafy.patpat.common.code.ProtectState;

import java.util.Arrays;

public enum Gender {
    전체(0),수컷(1), 암컷(2),모름(3);

    private int code;

    private Gender(int code){
        this.code = code;
    }
    public int getCode(){
        return code;
    }

    public static Gender of(int code){
        return Arrays.stream(values())
                .filter(value -> value.getCode()==code)
                .findFirst()
                .orElse(null);
    }
}
