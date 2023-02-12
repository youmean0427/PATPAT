package com.ssafy.patpat.common.code;

import java.util.Arrays;

public enum Reservation {
    대기중(0),승인(1),거절(2),불참(3),완료(4);

    private int code;

    private Reservation(int code){
        this.code = code;
    }
    public int getCode(){
        return code;
    }

    public static Reservation of(int code){
        return Arrays.stream(values())
                .filter(value -> value.getCode()==code)
                .findFirst()
                .orElse(null);
    }
}
