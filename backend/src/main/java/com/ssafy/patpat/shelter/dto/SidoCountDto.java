package com.ssafy.patpat.shelter.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SidoCountDto {
    private String sidoCode;
    private String sidoName;
    private int count;
}
