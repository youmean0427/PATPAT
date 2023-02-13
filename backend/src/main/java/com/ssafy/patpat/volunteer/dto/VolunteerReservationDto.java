package com.ssafy.patpat.volunteer.dto;

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

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String reservationState;

    private Integer reservationStateCode;

}
