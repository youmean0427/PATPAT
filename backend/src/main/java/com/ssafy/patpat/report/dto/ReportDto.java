package com.ssafy.patpat.report.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ssafy.patpat.common.dto.FileDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Getter
@ToString
@Builder
public class ReportDto {
    private int missingId;
    private int personalProtectionId;
    private int userId;
    private int gender;
    @Schema(example = "위도")
    private String latitude;
    @Schema(example = "경도")
    private String longitude;
    @Schema(example = "제목")
    private String title;
    @Schema(example = "본문")
    private String content;
    @Schema(example = "견종")
    private String breedName;
    @Schema(example = "이름")
    private String name;
    @Schema(example = "무게")
    private String weight;
    private int neutered;
    @Schema(example = "귀모양")
    private String categoryEar;
    @Schema(example = "꼬리모양")
    private String categoryTail;
    @Schema(example = "털색")
    private String categoryColor;
    @Schema(example = "패턴")
    private String categoryPattern;
    @Schema(example = "옷여부")
    private String categoryCloth;
    @Schema(example = "옷색깔")
    private String categoryClothColor;
    @Schema(example = "fileurl:.png")
    private List<FileDto> fileUrlList;
}
