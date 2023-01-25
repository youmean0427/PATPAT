package com.ssafy.patpat.shelter.dto;

import com.ssafy.patpat.shelter.entity.Shelter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDetailShelterDto {
    private Shelter shelter;
    private int result;
}
