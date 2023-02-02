package com.ssafy.patpat.protect.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShelterProtectedDog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int spDogId;
    private int shelterId;//
    private int breedId;//
    private LocalDate findingDate;
    private String latitude;//
    private String longitude;//
    private double weight;//
    private int gender;//
    private int neutered;//
    private LocalDate registDate;//
    private String feature;//
    private int stateCode;//
    private int categoryEar;//
    private int categoryTail;//
    private int categoryColor;//
    private int categoryPattern;//
    private int categoryCloth;//
    private int categoryClothColor;//
    private String name;//
    private int age;//
    private String sidoCode;//
    private String gugunCode;//

    public void updateShelterProtectedDog(double weight,String feature){
        this.weight = weight;
        this.feature =feature;
    }
}
