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
    private Integer totalCount;
    private List<Object> list;
}
