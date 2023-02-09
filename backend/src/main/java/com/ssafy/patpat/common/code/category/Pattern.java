package com.ssafy.patpat.common.code.category;

import java.util.Arrays;

public enum Pattern {
    모름(0),솔리드(1),바이컬러(2),트라이컬러(3),탄(4),턱시도(5),할리퀸(6),브린들(7),새들(8),세이블(9),멀(10);
    private int code;
    private Pattern(int code){
        this.code = code;
    }
    public int getCode(){
        return code;
    }

    public static Pattern of(int code){
        return Arrays.stream(values())
                .filter(value -> value.getCode()==code)
                .findFirst()
                .orElse(null);
    }
}
