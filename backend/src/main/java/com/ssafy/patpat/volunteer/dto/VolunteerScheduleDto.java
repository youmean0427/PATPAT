package com.ssafy.patpat.volunteer.dto;

import com.ssafy.patpat.common.code.Reservation;
import com.ssafy.patpat.common.dto.ResponseListDto;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.volunteer.entity.VolunteerNotice;
import com.ssafy.patpat.volunteer.entity.VolunteerReservation;
import com.ssafy.patpat.volunteer.entity.VolunteerSchedule;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VolunteerScheduleDto {

    private Long scheduleId;

    private Long noticeId;

    private String startTime;

    private String endTime;

    private Integer totalCapacity;

    private Integer capacity;

    private String guideLine;

    private Integer reservationStateCode;

    private String reservationState;

    private ResponseListDto reservations;

    public VolunteerScheduleDto(VolunteerSchedule vs){
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
