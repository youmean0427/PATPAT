package com.ssafy.patpat.shelter.dto;

import com.ssafy.patpat.shelter.entity.Shelter;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ShelterLocationDto {
    private Long shelterId;
    private String name;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public ShelterLocationDto(Shelter shelter){
        this.shelterId = shelter.getShelterId();
        this.name = shelter.getName();
        this.latitude = shelter.getLatitude();
        this.longitude = shelter.getLongitude();
    }
}
