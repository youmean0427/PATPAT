package com.ssafy.patpat.report.entity;

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
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@Entity
public class PersonalProtectedDog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ppDogId;

    private LocalDate findDate;
    private String title;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Double weight;
    private Gender gender;
    private Neutered neutered;
    private LocalDateTime registDate;
    private String feature;
    private ProtectState stateCode;
    private Ear categoryEar;
    private Tail categoryTail;
    private Color categoryColor;
    private Pattern categoryPattern;
    private Cloth categoryCloth;
    private Integer age;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "breed_id")
    private Breed breed;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "personal_dog_image",
            joinColumns = {@JoinColumn(name = "pp_dog_id")},
            inverseJoinColumns = {@JoinColumn(name = "image_id")})
    private List<Image> images;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "personal_dog_color",
            joinColumns = {@JoinColumn(name = "pp_dog_id")},
            inverseJoinColumns = {@JoinColumn(name = "color_id")}
    )
    private List<DogColor> colors;


    public void update(ProtectState stateCode, String feature, Gender gender, Breed breed, double weight, Neutered neutered, Ear categoryEar, Tail categoryTail
            ,Color categoryColor, Pattern categoryPattern,Cloth categoryCloth){
        this.stateCode = stateCode;
        this.feature =feature;
        this.gender = gender;
        this.weight =weight;
        this.breed = breed;
        this.neutered =neutered;
        this.categoryCloth = categoryCloth;
        this.categoryColor = categoryColor;
        this.categoryEar =categoryEar;
        this.categoryTail = categoryTail;
        this.categoryPattern = categoryPattern;
    }


    @PrePersist
    public void prePersist() {
        this.stateCode = this.stateCode == null ? ProtectState.보호중 : this.stateCode;
    }

}
