package com.ssafy.patpat.common.code;

import java.util.Arrays;

public enum ConsultingState {
    대기(0), 승인(1), 거절(2), 방생성(3), 진행중(4), 완료(5), 불참(6);

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
