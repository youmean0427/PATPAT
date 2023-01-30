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
public class ProtectDto {
    private int shelterId;
    private int protectId;
    private String protectName;
    private int gender;
    private String breedId;
    private String weight;
    private int neutered;
    private String categoryEar;
    private String categoryTail;
    private String categoryColor;
    private String categoryPattern;
    private String categoryCloth;
    private String categoryClothColor;
    private int stateCode;
    private String infoContent;
    private List<FileDto> fileUrlList;
}
