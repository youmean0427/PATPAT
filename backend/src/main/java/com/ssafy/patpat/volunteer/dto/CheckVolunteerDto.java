package com.ssafy.patpat.volunteer.dto;

import com.ssafy.patpat.volunteer.entity.VolunteerSchedule;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckVolunteerDto {
    private Long scheduleId;
    private Boolean isOk;
}
