package com.ssafy.patpat.report.entity;

import com.ssafy.patpat.common.entity.Image;
import com.ssafy.patpat.shelter.entity.Breed;
import com.ssafy.patpat.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@DynamicInsert
@DynamicUpdate
@Entity
public class MissingDog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long missingId;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "breed_id")
    private Breed breed;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "missing_dog_image",
            joinColumns = {@JoinColumn(name = "missing_id")},
            inverseJoinColumns = {@JoinColumn(name = "image_id")})
    private List<Image> images;

    public void update(int stateCode, String feature, int gender, Breed breed, double weight, int neutered, int categoryEar, int categoryTail ,int categoryColor,
                       int categoryPattern,int categoryCloth){
        this.stateCode = stateCode;
        this.feature =feature;
        this.gender = gender;
        this.weight =weight;
        this.breed = breed;
        this.neutered =neutered;
        this.categoryCloth = categoryCloth;
        this.categoryClothColor = categoryClothColor;
        this.categoryColor = categoryColor;
        this.categoryEar =categoryEar;
        this.categoryTail = categoryTail;
        this.categoryPattern = categoryPattern;
    }
}
