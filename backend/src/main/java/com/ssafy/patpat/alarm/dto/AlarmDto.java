package com.ssafy.patpat.alarm.dto;

import com.ssafy.patpat.alarm.entity.Alarm;
import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlarmDto {
    private Long alarmId;
    private Boolean checkRead;
    private String msg;
    private Integer msgCode;
    private Long missingId;
    private Long shelterId;
    private Long spDogId;

    public AlarmDto(Alarm alarm){
        this.alarmId = alarm.getAlarmId();
        this.checkRead = alarm.getCheckRead();
        this.msg = alarm.getMsgCode().getMsg();
        this.msgCode = alarm.getMsgCode().getCode();
    }

}
