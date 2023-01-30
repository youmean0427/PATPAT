package com.ssafy.patpat.consulting.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ConsultingDto {
    private int consultingId;
    private int shelterId;
    private String shelterName;
    private String userName;
    private String address;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int state;
    private int offSet;
    private int limit;
}
