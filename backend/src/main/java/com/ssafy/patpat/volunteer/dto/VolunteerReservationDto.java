package com.ssafy.patpat.volunteer.dto;

import com.ssafy.patpat.volunteer.entity.VolunteerReservation;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VolunteerReservationDto {
    private Long reservationId;

    private Long scheduleId;

    private Long noticeId;

    private Integer capacity;

    private String shelterName;

    private String shelterAddress;

    private String volunteerDate;

    private String startTime;

    private String endTime;

    private String reservationState;

    private Integer reservationStateCode;

    public VolunteerReservationDto(VolunteerReservation vr){
        this.reservationId = vr.getReservationId();
        this.scheduleId = vr.getVolunteerSchedule().getScheduleId();
        this.capacity = vr.getCapacity();
        this.shelterName = vr.getVolunteerSchedule().getVolunteerNotice().getShelter().getName();
        this.shelterAddress = vr.getVolunteerSchedule().getVolunteerNotice().getShelter().getAddress();
        this.volunteerDate = vr.getVolunteerDate();
        this.startTime = vr.getVolunteerSchedule().getStartTime();
        this.endTime = vr.getVolunteerSchedule().getEndTime();
        this.reservationState = vr.getReservationStateCode().name();
        this.reservationStateCode = vr.getReservationStateCode().getCode();
    }
}
