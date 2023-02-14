package com.ssafy.patpat.volunteer.dto;

import com.ssafy.patpat.volunteer.entity.VolunteerSchedule;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleDto {
    private Long scheduleId;

    private Long noticeId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer totalCapacity;

    private Integer capacity;

    private String guideLine;

    private Integer reservationStateCode;

    private String reservationState;

    public ScheduleDto(VolunteerSchedule vs){
        this.scheduleId = vs.getScheduleId();
        this.noticeId = vs.getVolunteerNotice().getNoticeId();
        this.startTime = vs.getStartTime();
        this.endTime = vs.getEndTime();
        this.totalCapacity = vs.getTotaclCapacity();
        this.capacity = vs.getCapacity();
        this.guideLine = vs.getGuideLine();
        this.reservationStateCode = vs.getReservationStateCode().getCode();
        this.reservationState = vs.getReservationStateCode().name();
    }
}
