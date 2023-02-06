package com.ssafy.patpat.protect.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ssafy.patpat.common.code.ProtectState;
import com.ssafy.patpat.common.dto.FileDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Getter
@Setter
@ToString
@Builder
public class ProtectDto {
    @Schema(example = "보호소id")
    private int shelterId;
    @Schema(example = "보호동물id")
    private int protectId;
    @Schema(example = "보호동물이름")
    private String protectName;
    @Schema(description = "발견날짜")
    private LocalDate findingDate;
    @Schema(description = "수컷,암컷")
    private String gender;
    @Schema(description = "성별 코드")
    private Integer genderCode;
    @Schema(example = "견종")
    private int breedId;
    @Schema(example = "견종이름")
    private String breedName;
    @Schema(example = "나이")
    private int age;
    @Schema(example = "무게")
    private double kg;
    @Schema(description = "중성화=0,아니면=1")
    private boolean neutered;
    @Schema(example = "귀모양")
    private int categoryEar;
    @Schema(example = "꼬리모양")
    private int categoryTail;
    @Schema(example = "털색")
    private int categoryColor;
    @Schema(example = "패턴")
    private int categoryPattern;
    @Schema(example = "옷입은여부")
    private int categoryCloth;
    @Schema(example = "옷색")
    private int categoryClothColor;
    @Schema(example = "상태코드")
    private int stateCode;
    @Schema(description = "입양대기,보호중, 입양예정")
    private String state;
    @Schema(example = "내용")
    private String infoContent;
    @Schema(example = "파일 url 리스트")
    private List<FileDto> fileUrlList;
    @Schema(example = "파일 url 리스트")
    private String thumbnail;
}
