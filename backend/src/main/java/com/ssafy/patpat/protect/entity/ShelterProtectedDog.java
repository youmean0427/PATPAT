package com.ssafy.patpat.protect.entity;

import com.ssafy.patpat.common.code.ProtectState;
import com.ssafy.patpat.common.code.category.Gender;
import com.ssafy.patpat.common.code.category.Neutered;
import com.ssafy.patpat.common.entity.Image;
import com.ssafy.patpat.shelter.entity.Breed;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@ToString
public class ShelterProtectedDog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spDogId;

    private LocalDate findingDate;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Double weight;//
    private Gender gender;//
    private Neutered neutered;//
    private LocalDate registDate;//
    private String feature;//
    private Integer categoryEar;//
    private Integer categoryTail;//
    private Integer categoryColor;//
    private Integer categoryPattern;//
    private Integer categoryCloth;//
    private Integer categoryClothColor;//
    private String name;//
    private Integer age;//
    private String sidoCode;//
    private String gugunCode;//

    private ProtectState stateCode;//

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "breed_id")
    private Breed breed;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "shelter_dog_image",
            joinColumns = {@JoinColumn(name = "sp_dog_id")},
            inverseJoinColumns = {@JoinColumn(name = "image_id")})
    private List<Image> images;

    @ManyToMany(mappedBy = "favoriteDogs")
    private List<User> users;

    @PrePersist
    public void prePersist() {
        this.stateCode = this.stateCode == null ? ProtectState.공고중 : this.stateCode;
    }

}
