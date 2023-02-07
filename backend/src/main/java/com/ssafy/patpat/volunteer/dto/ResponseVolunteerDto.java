package com.ssafy.patpat.volunteer.dto;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVolunteerDto {
    private Long totalCount;
    private Integer totalPage;
    private List<Object> list;
}
