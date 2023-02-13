package com.ssafy.patpat.alarm.dto;

import com.ssafy.patpat.alarm.entity.Alarm;
import lombok.*;

@Data
@Setter
@Getter
public class AlarmDto {
    private Long alarmId;
    private Boolean read;
    private String msg;
    private Integer msgCode;
    private Long missingId;
    private Long shelterId;

    public AlarmDto(Alarm alarm){
        this.alarmId = alarm.getAlarmId();
        this.read = alarm.getRead();
        this.msg = alarm.getMsgCode().getMsg();
        this.msgCode = alarm.getMsgCode().getCode();
    }

}
