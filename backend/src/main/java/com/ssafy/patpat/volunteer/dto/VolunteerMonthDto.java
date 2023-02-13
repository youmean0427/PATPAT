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

    private String year;
    private String month;
    private Long shelterId;
}
