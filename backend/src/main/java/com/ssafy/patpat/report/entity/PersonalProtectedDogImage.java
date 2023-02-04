package com.ssafy.patpat.report.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class PersonalProtectedDogImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pdImageId;
    private int ppDogId;
    private int imageId;
}
