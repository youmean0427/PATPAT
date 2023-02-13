package com.ssafy.patpat.volunteer.dto;

import com.ssafy.patpat.common.code.Reservation;
import com.ssafy.patpat.common.dto.ResponseListDto;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.volunteer.entity.VolunteerReservation;
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

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer totalCapacity;

    private Integer capacity;

    private String guideLine;

    private Integer reservationStateCode;

    private String reservationState;

    private ResponseListDto responseListDto;

}
