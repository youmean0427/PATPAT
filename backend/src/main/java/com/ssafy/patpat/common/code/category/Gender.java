package com.ssafy.patpat.common.code.category;

import com.ssafy.patpat.common.code.ProtectState;

import java.util.Arrays;

public enum Gender {
    수컷(0), 암컷(1);

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
