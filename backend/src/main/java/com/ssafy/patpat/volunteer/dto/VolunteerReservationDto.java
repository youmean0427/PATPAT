package com.ssafy.patpat.volunteer.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VolunteerReservationDto {
    private Long reservationId;

    private Long scheduleId;

    private Integer capacity;

    private String shelterName;
    private String volunteerDate;

    private String reservationState;

    private Integer reservationStateCode;

}
