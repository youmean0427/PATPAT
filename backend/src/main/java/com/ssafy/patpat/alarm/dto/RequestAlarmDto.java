package com.ssafy.patpat.alarm.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestAlarmDto {

    private Integer offSet;
    private Integer limit;
    private Long userId;

}
