package com.ssafy.patpat.consulting.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ConsultingDto {
    @Schema(example = "상담id")
    private Long consultingId;
    @Schema(example = "보호소id")
    private Long shelterId;
    @Schema(example = "보호소 이름")
    private String shelterName;
    @Schema(example = "유저 이름")
    private String userName;
    @Schema(example = "유저 id")
    private Long userId;
    @Schema(example = "주소")
    private String address;
    @Schema(example = "시간코드")
    private Integer timeCode;
    @Schema(example = "시간")
    private String time;
    @Schema(example = "날짜")
    private LocalDate consultingDate;
    @Schema(example = "대기중,수락,거절")
    private Integer stateCode;
    @Schema(example = "대기중,수락,거절")
    private String state;
    @Schema(example = "강아지id")
    private Long shelterDogId;
    @Schema(example = "강아지id")
    private String shelterDogName;
    @Schema(example = "강아지id")
    private Boolean isOpen;

}
