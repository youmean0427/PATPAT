package com.ssafy.patpat.report.entity;

import com.ssafy.patpat.common.entity.Image;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class MissingDog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long missingId;

    private Long userId;
    private Long breedId;
    private LocalDate missingDate;
    private String title;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Double weight;
    private Integer gender;
    private Integer neutered;
    private LocalDate registDate;
    private String feature;
    private Integer stateCode;
    private Integer categoryEar;
    private Integer categoryTail;
    private Integer categoryColor;
    private Integer categoryPattern;
    private Integer categoryCloth;
    private Integer categoryClothColor;
    private String name;
    private int age;
    private String sidoCode;
    private String gugunCode;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "missing_dog_image",
            joinColumns = {@JoinColumn(name = "missing_id")},
            inverseJoinColumns = {@JoinColumn(name = "image_id")})
    private List<Image> images;

    public void update(int stateCode, String feature, int gender, long breedId, double weight, int neutered, int categoryEar, int categoryTail ,int categoryColor,
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
