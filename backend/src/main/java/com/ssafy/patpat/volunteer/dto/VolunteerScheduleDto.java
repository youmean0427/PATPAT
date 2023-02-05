package com.ssafy.patpat.volunteer.dto;

import com.ssafy.patpat.common.code.Reservation;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VolunteerScheduleDto {

    private Long volunteerId;

    private String title;

    private String volunteerDate;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer totalCapacity;

    private Integer capacity;

    private String guideLine;

    private Integer reservationStateCode;

    private String reservationState;

    private Integer shelterId;

    private String shelterName;
}
