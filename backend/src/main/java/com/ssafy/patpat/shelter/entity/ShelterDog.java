package com.ssafy.patpat.shelter.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shelter_protected_dog")
public class ShelterDog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int spDogId;
    private int shelterId;
    private LocalDate findingDate;
    private String findingSpot;
    private String weight;
    private String gender;
    private String neutered;
    private LocalDate registDate;
    private String feature;
    private String stateCode;
    private String categoryEar;
    private String categoryTail;
    private String categoryColor;
    private String categoryPattern;
    private String categoryCloth;
    private String categoryClothColor;
    private String name;
    private String age;
    private int breedId;
    private String sidoCode;
    private String gugunCode;
}
