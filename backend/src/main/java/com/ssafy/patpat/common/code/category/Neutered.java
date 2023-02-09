package com.ssafy.patpat.common.code.category;

import java.util.Arrays;

public enum Neutered {
    빈칸(0),O(1),X(2),M(3);
    private int code ;
    private Neutered(int code){
        this.code = code;
    }
    public int getCode(){
        return code;
    }

    public static Neutered of(int code){
        return Arrays.stream(values())
                .filter(value -> value.getCode()==code)
                .findFirst()
                .orElse(null);
    }
}
