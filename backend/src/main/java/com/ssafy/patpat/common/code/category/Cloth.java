package com.ssafy.patpat.common.code.category;

import java.util.Arrays;

public enum Cloth {
    옷입음(0),안입음(1);
    private int code ;
    private Cloth(int code){
        this.code = code;
    }
    public int getCode(){
        return code;
    }

    public static Cloth of(int code){
        return Arrays.stream(values())
                .filter(value -> value.getCode()==code)
                .findFirst()
                .orElse(null);
    }
}
