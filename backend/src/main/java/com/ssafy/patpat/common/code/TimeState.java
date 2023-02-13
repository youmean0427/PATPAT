package com.ssafy.patpat.common.code;

import java.util.Arrays;

public enum TimeState {
    대기(0), 승인(1), 거절(2);

    private int code;

    private TimeState(int code){
        this.code = code;
    }
    public int getCode(){
        return code;
    }

    public static TimeState of(int code){
        return Arrays.stream(values())
                .filter(value -> value.getCode()==code)
                .findFirst()
                .orElse(null);
    }

}
