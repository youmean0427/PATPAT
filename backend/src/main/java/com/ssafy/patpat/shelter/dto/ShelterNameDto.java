package com.ssafy.patpat.shelter.dto;

import lombok.*;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShelterNameDto {
    private String name;
    private Long idx;
}
