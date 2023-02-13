package com.ssafy.patpat.volunteer.dto;

import lombok.*;

@Data
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerMonthDto {

    private Integer year;
    private Integer month;
    private Long shelterId;
}
