package com.ssafy.patpat.shelter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestParamMbtiDto {
    String breedName;
    String sidoCode;
    String gugunCode;
}
