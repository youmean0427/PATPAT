package com.ssafy.patpat.report.dto;

import com.ssafy.patpat.common.dto.FileDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecommendDto {
    private Long personalProtectionId;
    private Long protectId;
    private FileDto thumbnail;
    private String title;
    private String content;
    private Long breedId;
    private String breedName;
    private String name;
    private Double kg;
    private Integer neuteredCode;
    private String neutered;
    private Integer age;
}
