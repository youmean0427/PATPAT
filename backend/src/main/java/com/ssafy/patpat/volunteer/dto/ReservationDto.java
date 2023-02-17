package com.ssafy.patpat.volunteer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Getter
@Setter
@ToString
public class ReservationDto {
    private Long scheduleId;

    private String shelterName;
    private String volunteerDate;
    private Long reservationId;
    private Long userId;
    private String userName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer capacity;
    @Schema(example = "대기중,수락,거절,불참,완료")
    private Integer stateCode;

}
