package com.ssafy.patpat.common.dto;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ResponseListDto {
    private Long totalCount;
    private Integer totalPage;
    private List<?> list;
}
