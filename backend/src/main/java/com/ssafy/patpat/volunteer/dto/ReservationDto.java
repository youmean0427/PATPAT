package com.ssafy.patpat.volunteer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Getter
@ToString
public class ReservationDto {
    private int noticeId;
    private int shelterId;
    private int reservationId;
    private int userId;
    private String userName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int capacity;
    private String stateCode;
}
