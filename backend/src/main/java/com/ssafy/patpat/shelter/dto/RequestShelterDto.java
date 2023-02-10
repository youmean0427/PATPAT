package com.ssafy.patpat.shelter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestShelterDto {
    private Long breedId;
    private String sidoCode;
    private String gugunCode;
    private Integer offSet;
    private Integer limit;
}
