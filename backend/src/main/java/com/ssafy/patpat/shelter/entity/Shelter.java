package com.ssafy.patpat.shelter.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shelterId;
    private String address;
    private String latitude;
    private String longitude;
    private String phoneNum;
    private String name;
    private String regNumber;
    private String sidoCode;
    private String gugunCode;
}
