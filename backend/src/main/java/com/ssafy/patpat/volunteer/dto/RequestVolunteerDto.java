package com.ssafy.patpat.volunteer.dto;

import lombok.*;

@Data
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestVolunteerDto {
    private Long shelterId;
    private Integer offSet;
    private Integer limit;
    private String keyword;
    private String volunteerDate;

    private String extraDate;

    private String latitude;
    private String longitude;

    private Long userId;
    private Long scheduleId;

}
