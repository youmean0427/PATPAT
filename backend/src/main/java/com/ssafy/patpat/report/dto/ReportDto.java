package com.ssafy.patpat.report.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ssafy.patpat.common.dto.FileDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
@Setter
@Getter
@Builder
public class ReportDto {
    @Schema(example = "타입")
    private Integer typeCode;
    private Long missingId;
    private Long personalProtectionId;
    private Long userId;
    private Integer genderCode;
    private String gender;
    @Schema(example = "위도")
    private String latitude;
    @Schema(example = "경도")
    private String longitude;
    @Schema(example = "제목")
    private String title;
    @Schema(example = "본문")
    private String content;
    @Schema(example = "견종아이디")
    private Long breedId;
    @Schema(example = "견종")
    private String breedName;
    @Schema(example = "이름")
    private String name;
    @Schema(example = "무게")
    private Double kg;
    @Schema(example = "중성화 여부")
    private Integer neuteredCode;
    @Schema(example = "중성화 여부 스트링")
    private String neutered;
    @Schema(example = "나이")
    private Integer age;
    @Schema(example = "귀모양")
    private Integer categoryEar;
    @Schema(example = "꼬리모양")
    private Integer categoryTail;
    @Schema(example = "털색")
    private Integer categoryColor;
    @Schema(example = "패턴")
    private Integer categoryPattern;
    @Schema(example = "옷여부")
    private Integer categoryCloth;
    @Schema(example = "옷색깔")
    private Integer categoryClothColor;
    @Schema(example = "fileurl:.png")
    private List<FileDto> fileUrlList;
    @Schema(example = "filePath: ")
    private FileDto thumbnail;
    @Schema(example = "발견날짜")
    private LocalDate findDate;
    @Schema(example = "상태코드")
    private Integer stateCode;
}
