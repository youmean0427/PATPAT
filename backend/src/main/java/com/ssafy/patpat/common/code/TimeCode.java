package com.ssafy.patpat.common.code;

import java.util.Arrays;

public enum TimeCode {
    십시(10),십사시(14),십오시(15),십육시(16), 십칠시(17);

    private int code;

    private TimeCode(int code){
        this.code = code;
    }
    public int getCode(){
        return code;
    }

    public static TimeCode of(int code){
        return Arrays.stream(values())
                .filter(value -> value.getCode()==code)
                .findFirst()
                .orElse(null);
    }
}
