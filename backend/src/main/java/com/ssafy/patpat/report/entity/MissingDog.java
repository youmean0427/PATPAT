package com.ssafy.patpat.report.entity;

import com.ssafy.patpat.common.code.MissingState;
import com.ssafy.patpat.common.code.ProtectState;
import com.ssafy.patpat.common.code.category.*;
import com.ssafy.patpat.common.entity.DogColor;
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
    private Gender gender;
    private Neutered neutered;
    private LocalDate registDate;
    private String feature;
    private MissingState stateCode;
    private Ear categoryEar;
    private Tail categoryTail;
    private Color categoryColor;
    private Pattern categoryPattern;
    private Cloth categoryCloth;
    private String name;
    private int age;

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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "missing_dog_color",
            joinColumns = {@JoinColumn(name = "missing_id")},
            inverseJoinColumns = {@JoinColumn(name = "color_id")}
    )
    private List<DogColor> colors;

    @PrePersist
    public void prePersist() {
        this.stateCode = this.stateCode == null ? MissingState.실종 : this.stateCode;
    }

//    public void update(int stateCode, String feature, int gender, Breed breed, double weight, int neutered, int categoryEar, int categoryTail ,int categoryColor,
//                       int categoryPattern,int categoryCloth){
//        this.stateCode = stateCode;
//        this.feature =feature;
//        this.gender = gender;
//        this.weight =weight;
//        this.breed = breed;
//        this.neutered =neutered;
//        this.categoryCloth = categoryCloth;
//        this.categoryClothColor = categoryClothColor;
//        this.categoryColor = categoryColor;
//        this.categoryEar =categoryEar;
//        this.categoryTail = categoryTail;
//        this.categoryPattern = categoryPattern;
//    }
}
