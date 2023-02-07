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
    private Integer shelterId;
    private Integer offset;
    private Integer limit;
    private String keyword;
    private String volunteerDate;
    private String gugunCode;
    private Long userId;

}
