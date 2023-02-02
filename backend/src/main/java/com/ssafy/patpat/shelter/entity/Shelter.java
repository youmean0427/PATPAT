package com.ssafy.patpat.shelter.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import com.ssafy.patpat.consulting.entity.Time;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
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

    @OneToMany
    @JoinColumn(name = "shelter_id")
    private List<Time> timeList = new ArrayList<Time>();
}
