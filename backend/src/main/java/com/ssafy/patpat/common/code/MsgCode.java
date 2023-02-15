package com.ssafy.patpat.common.code;

import java.util.Arrays;

public enum MsgCode {
    MSG_NEW_MISSING("근처에 새로운 실종견이 등록되었습니다.",0), //missingId
    MSG_NEW_CONSULTING("새로운 상담이 신청되었습니다.",1), //shelterId
    MSG_NEW_VOLUNTEER("새로운 봉사가 신청되었습니다.",2), //shelterId
    MSG_NEW_RESEMBLE_DOG("비슷한 강아지가 등록되었습니다.",3),
    MSG_ACCESS_CONSULTING("상담 신청이 승인되었습니다.", 4),
    MSG_DENY_CONSULTING("상담 신청이 거부되었습니다.",5),
    MSG_ACCESS_VOLUNTEER("봉사 신청이 승인되었습니다.",6),
    MSG_DENY_VOLUNTEER("봉사 신청이 거부되었습니다.",7),
    MSG_CREATE_ROOM("상담 방이 생성되었습니다.",8);

    private String msg;
    private Integer code;

    MsgCode(String msg, Integer code){
        this.msg = msg;
        this.code = code;
    }

    public Integer getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }

    public static MsgCode of(Integer code){
        return Arrays.stream(values())
                .filter(value -> value.getCode()==code)
                .findFirst()
                .orElse(null);
    }
}
