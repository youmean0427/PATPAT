package com.ssafy.patpat.volunteer.dto;

import com.ssafy.patpat.common.code.Reservation;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VolunteerNoticeDto {
    private String name;
    private Long noticeId;
    private Integer shelterId;
    private String volunteerDate;
    private String title;
    private String state;
    private int stateCode;
}
