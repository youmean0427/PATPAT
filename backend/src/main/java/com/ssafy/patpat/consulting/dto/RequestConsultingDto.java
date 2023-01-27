package com.ssafy.patpat.consulting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestConsultingDto {
    private int offset;
    private int limit;
    private int shelterId;
}
