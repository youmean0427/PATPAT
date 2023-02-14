package com.ssafy.patpat.alarm.dto;

import io.swagger.models.auth.In;
import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AlarmListDto {
    private List<?> list;
    private Integer cntNoRead;
}
