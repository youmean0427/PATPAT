package com.ssafy.patpat.common.code;

import java.util.Arrays;

public enum MissingState {
    실종(0), 찾음(1);
    private int code;

    private MissingState(int code){
        this.code = code;
    }
    public int getCode(){
        return code;
    }

    public static MissingState of(int code){
        return Arrays.stream(values())
                .filter(value -> value.getCode()==code)
                .findFirst()
                .orElse(null);
    }
}
