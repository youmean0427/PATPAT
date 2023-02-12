package com.ssafy.patpat.common.code;

import java.util.Arrays;

public enum DogType {
    실종견(0),개인보호견(1),보호소보호견(2);
    private int code;

    private DogType(int code){
        this.code = code;
    }
    public int getCode(){
        return code;
    }

    public static DogType of(int code){
        return Arrays.stream(values())
                .filter(value -> value.getCode()==code)
                .findFirst()
                .orElse(null);
    }
}
