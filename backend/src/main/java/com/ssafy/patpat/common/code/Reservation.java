package com.ssafy.patpat.common.code;

import java.util.Arrays;

public enum Reservation {
    대기중(0),수락(1),거절(2),미완료(3),불참(4),완료(5), 취소(6);

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
