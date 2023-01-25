package com.ssafy.patpat.shelter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultInsertShelterDto {
    private int result;
    private String authCode;
}
