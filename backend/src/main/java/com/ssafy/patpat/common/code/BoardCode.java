package com.ssafy.patpat.common.code;

import java.util.Arrays;

public enum BoardCode {
    입양후기(0),정보게시판(1),무료나눔(2);

    private int code;

    private BoardCode(int code){
        this.code = code;
    }
    public int getCode(){
        return code;
    }

    public static BoardCode of(int code){
        return Arrays.stream(values())
                .filter(value -> value.getCode()==code)
                .findFirst()
                .orElse(null);
    }
}
