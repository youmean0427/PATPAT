package com.ssafy.patpat.common.code;

import java.util.Arrays;

public enum ConsultingState {
    대기(0), 승인(1), 거절(2), 미완료(3), 생성(4), 완료(5), 방참가(6);

    private int code;

    private ConsultingState(int code){
        this.code = code;
    }
    public int getCode(){
        return code;
    }

    public static ConsultingState of(int code){
        return Arrays.stream(values())
                .filter(value -> value.getCode()==code)
                .findFirst()
                .orElse(null);
    }
}
