package com.ssafy.patpat.common.code;

public enum Reservation {
    대기중(0),수락(1),거절(2),미완료(3),불참(4),완료(5);

    private int code;

    private Reservation(int code){
        this.code = code;
    }
    public int getCode(){
        return code;
    }
}
