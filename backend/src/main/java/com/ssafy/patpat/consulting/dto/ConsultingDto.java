package com.ssafy.patpat.consulting.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ConsultingDto {
    @Schema(example = "상담id")
    private int consultingId;
    @Schema(example = "보호소id")
    private int shelterId;
    @Schema(example = "보호소 이름")
    private String shelterName;
    @Schema(example = "유저 이름")
    private String userName;
    @Schema(example = "유저 이름")
    private int userId;
    @Schema(example = "주소")
    private String address;
    @Schema(example = "시간코드")
    private int timeCode;
    @Schema(example = "날짜")
    private LocalDate registDate;
    @Schema(example = "대기중,수락,거절")
    private int stateCode;
    @Schema(example = "강아지id")
    private int shelterDogId;
}
