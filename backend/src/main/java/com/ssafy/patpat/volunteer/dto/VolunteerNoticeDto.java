package com.ssafy.patpat.volunteer.dto;

import com.ssafy.patpat.common.code.Reservation;
import com.ssafy.patpat.volunteer.entity.VolunteerNotice;
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
    private Integer stateCode;
    private String distance;

    private String latitude;
    private String longitude;

    private List<VolunteerScheduleDto> volunteerScheduleDtos;

    public VolunteerNoticeDto(VolunteerNotice volunteerNotice){
        this.noticeId = volunteerNotice.getNoticeId();
        this.name = volunteerNotice.getShelter().getName();
        this.shelterId = volunteerNotice.getShelter().getShelterId();
        this.volunteerDate = volunteerNotice.getVolunteerDate();
        this.title = volunteerNotice.getTitle();
        this.state = volunteerNotice.getReservationStateCode().name();
        this.stateCode = volunteerNotice.getReservationStateCode().getCode();
    }
}
