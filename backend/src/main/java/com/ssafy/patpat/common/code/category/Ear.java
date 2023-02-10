package com.ssafy.patpat.common.code.category;

import java.util.Arrays;

public enum Ear {
    모름(0),직립귀(1),박쥐귀(2),반직립귀(3),버튼귀(4),장미귀(5),쳐진귀(6),접힌귀(7),V자귀(8);
    private int code;
    private Ear(int code){
        this.code = code;
    }
    public int getCode(){
        return code;
    }

    public static Ear of(int code){
        return Arrays.stream(values())
                .filter(value -> value.getCode()==code)
                .findFirst()
                .orElse(null);
    }
}
