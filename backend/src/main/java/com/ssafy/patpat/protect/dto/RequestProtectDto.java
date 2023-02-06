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
    private int shelterId;
    private int code;
    private int offSet;
    private int limit;
}
