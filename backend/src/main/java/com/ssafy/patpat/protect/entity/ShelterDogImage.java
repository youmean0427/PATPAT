package com.ssafy.patpat.protect.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShelterDogImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shelterDogImageId;
    private int imageId;
    private int spDogId;
}
