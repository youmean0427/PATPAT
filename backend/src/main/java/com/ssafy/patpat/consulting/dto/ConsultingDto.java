package com.ssafy.patpat.consulting.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(example = "상담id")
    private int consultingId;
    @Schema(example = "보호소id")
    private int shelterId;
    @Schema(example = "보호소 이름")
    private String shelterName;
    @Schema(example = "유저 이름")
    private String userName;
    @Schema(example = "주소")
    private String address;
    @Schema(example = "시작 시간")
    private LocalDateTime startTime;
    @Schema(example = "종료 시간")
    private LocalDateTime endTime;
    @Schema(example = "대기,승인,거절,완료")
    private String state;
}
