package com.ssafy.patpat.common.code.category;

import java.util.Arrays;

public enum Tail {
    모름(0),말린꼬리(1),수달꼬리(2),당근꼬리(3),단발꼬리(4);

    private int code;

    private Tail(int code){
        this.code = code;
    }
    public int getCode(){
        return code;
    }

    public static Tail of(int code){
        return Arrays.stream(values())
                .filter(value -> value.getCode()==code)
                .findFirst()
                .orElse(null);
    }
}
