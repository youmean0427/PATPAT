package com.ssafy.patpat.volunteer.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VolunteerShelterDto {
    private String name;
    private Integer shelterId;
    private String volunteerDate;
}
