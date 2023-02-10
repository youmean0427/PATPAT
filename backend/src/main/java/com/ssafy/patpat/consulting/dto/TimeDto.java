package com.ssafy.patpat.consulting.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.models.auth.In;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class TimeDto {
    private Integer timeCode;
    private String time;
}
