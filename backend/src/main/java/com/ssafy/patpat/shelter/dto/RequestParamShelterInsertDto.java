package com.ssafy.patpat.shelter.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestParamShelterInsertDto {
    String shelterName;
    String shelterCode;
}
