package com.ssafy.patpat.common.code;

import java.util.Arrays;

public enum ProtectState {
    공고중(0),보호중(1),입양예정(2),입양(3),자연사(4),안락사(5);
    private int code;

    private ProtectState(int code){
        this.code = code;
    }
    public int getCode(){
        return code;
    }

    public static ProtectState of(int code){
        return Arrays.stream(values())
                .filter(value -> value.getCode()==code)
                .findFirst()
                .orElse(null);
    }
}
