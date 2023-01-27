package com.ssafy.patpat.report.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ssafy.patpat.common.dto.FileDto;
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
    private String latitude;
    private String longitude;
    private String title;
    private String content;
    private int breedId;
    private String name;
    private String weight;
    private int neutered;
    private String categoryEar;
    private String categoryTail;
    private String categoryColor;
    private String categoryPattern;
    private String categoryCloth;
    private String categoryClothColor;
    private List<FileDto> fileUrlList;
}
