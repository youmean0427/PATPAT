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
    private int shelterId;
    private int offset;
    private int limit;
    private String keyword;
    private String volunteerDate;
    private String gugunCode;

}
