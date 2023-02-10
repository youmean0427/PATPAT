package com.ssafy.patpat.report.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestReportDto {
    private Integer offSet;
    private Integer limit;
    private Long breedId;
    private Integer gender;
    private Long missingId;
}
