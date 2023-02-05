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
public class ProtectDto {
    @Schema(example = "보호소id")
    private int shelterId;
    @Schema(example = "보호동물id")
    private int protectId;
    @Schema(example = "보호동물이름")
    private String protectName;
    @Schema(description = "수컷=0,암컷=1")
    private int gender;
    @Schema(example = "견종")
    private String breedId;
    @Schema(example = "무게")
    private String weight;
    @Schema(description = "중성화=0,아니면=1")
    private int neutered;
    @Schema(example = "귀모양")
    private String categoryEar;
    @Schema(example = "꼬리모양")
    private String categoryTail;
    @Schema(example = "털색")
    private String categoryColor;
    @Schema(example = "패턴")
    private String categoryPattern;
    @Schema(example = "옷입은여부")
    private String categoryCloth;
    @Schema(example = "옷색")
    private String categoryClothColor;
    @Schema(description = "입양대기,보호중, 입양예정")
    private String stateCode;
    @Schema(example = "내용")
    private String infoContent;
    @Schema(example = "파일 url 리스트")
    private List<FileDto> fileUrlList;
}
