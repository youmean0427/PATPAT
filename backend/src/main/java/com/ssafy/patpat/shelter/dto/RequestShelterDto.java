package com.ssafy.patpat.shelter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestShelterDto {
    private String breedName;
    private String sidoCode;
    private String gugunCode;
    private int offSet;
    private int limit;
}
