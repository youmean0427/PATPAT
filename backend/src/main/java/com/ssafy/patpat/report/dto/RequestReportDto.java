package com.ssafy.patpat.report.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestReportDto {
    private int code;
    private int offset;
    private int limit;
    private int breedId;
    private int gender;
    private int missingId;
}
