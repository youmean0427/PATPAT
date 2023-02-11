package com.ssafy.patpat.consulting.dto;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestConsultingDto {
    private Long userId;
    private Integer offSet;
    private Integer limit;
    private Long shelterId;
    private Integer stateCode;
    private Integer code;
}
