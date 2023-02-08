package com.ssafy.patpat.report.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestReportDto {
    private int offSet;
    private int limit;
    private int breedId;
    private int gender;
    private int missingId; 
}
