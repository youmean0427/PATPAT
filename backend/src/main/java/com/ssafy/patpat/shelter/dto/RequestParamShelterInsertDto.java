package com.ssafy.patpat.shelter.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Builder
public class RequestParamShelterInsertDto {
    private String shelterCode;
    private String shelterName;
}
