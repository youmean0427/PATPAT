package com.ssafy.patpat.volunteer.dto;

import com.ssafy.patpat.common.code.Reservation;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VolunteerNoticeDto {
    private String name;
    private Long noticeId;
    private Long shelterId;
    private String volunteerDate;
    private String title;
    private List<Long> scheduleId;
    private String state;
    private int stateCode;
}
