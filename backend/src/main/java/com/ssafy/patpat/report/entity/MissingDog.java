package com.ssafy.patpat.report.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class MissingDog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int missingId;
    private int userId;
    private int breedId;
    private LocalDate missingDate;
    private String title;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private double weight;
    private int gender;
    private int neutered;
    private LocalDate registDate;
    private String feature;
    private int stateCode;
    private int categoryEar;
    private int categoryTail;
    private int categoryColor;
    private int categoryPattern;
    private int categoryCloth;
    private int categoryClothColor;
    private String name;
    private int age;
    private String sidoCode;
    private String gugunCode;

    public void update(int stateCode, String feature, int gender, int breedId, double weight, int neutered, int categoryEar, int categoryTail ,int categoryColor,
                       int categoryPattern,int categoryCloth){
        this.stateCode = stateCode;
        this.feature =feature;
        this.gender = gender;
        this.weight =weight;
        this.breedId = breedId;
        this.neutered =neutered;
        this.categoryCloth = categoryCloth;
        this.categoryClothColor = categoryClothColor;
        this.categoryColor = categoryColor;
        this.categoryEar =categoryEar;
        this.categoryTail = categoryTail;
        this.categoryPattern = categoryPattern;
    }
}
