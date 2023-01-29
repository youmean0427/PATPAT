package com.ssafy.patpat.protect.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestProtectDto {
    private int code;
    private int shelterId;
    private int offSet;
    private int limit;
}
