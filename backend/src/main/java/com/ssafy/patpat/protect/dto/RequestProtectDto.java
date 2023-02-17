package com.ssafy.patpat.protect.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class RequestProtectDto {
    private Long shelterId;
    private Integer code;
    private Integer offSet;
    private Integer limit;
    private Long breedId;

    private Integer stateCode;
}
