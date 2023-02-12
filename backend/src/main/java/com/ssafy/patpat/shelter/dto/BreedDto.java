package com.ssafy.patpat.shelter.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ssafy.patpat.common.dto.FileDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BreedDto {
    @Schema(example = "견종id")
    private Long breedId;
    @Schema(example = "견종")
    private String breedName;
    @Schema(example = "fileUrl:dd.png")
    private String thumbnail;
    @Schema(example = "분양횟수")
    private Integer adoptCount;
    @Schema(example = "제목")
    private String title;
    @Schema(example = "내용")
    private String description;
}
