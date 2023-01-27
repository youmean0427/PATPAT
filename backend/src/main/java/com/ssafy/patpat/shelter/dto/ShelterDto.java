package com.ssafy.patpat.shelter.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ssafy.patpat.common.dto.FileDto;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShelterDto {
    private int shelterId;
    private String name;
    private String latitude;
    private String longitude;
    private String address;
    private FileDto fileDto;
    private String infoContent;
    private String phoneNum;
}
