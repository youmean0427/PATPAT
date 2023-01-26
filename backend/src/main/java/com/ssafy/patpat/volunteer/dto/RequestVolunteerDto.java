package com.ssafy.patpat.volunteer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestVolunteerDto {
    private int shelterId;
    private int offset;
    private int limit;
    private String keyword;
}
