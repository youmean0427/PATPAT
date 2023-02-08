package com.ssafy.patpat.shelter.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonValueInstantiator;
import com.ssafy.patpat.common.entity.Image;
import com.ssafy.patpat.consulting.entity.Time;
import com.ssafy.patpat.user.entity.Owner;
import com.ssafy.patpat.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@Builder
public class Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shelter_id")
    private Integer shelterId;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String name;
    private String regNumber;
    private String sidoCode;
    private String gugunCode;
    private String info;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "shelter_image",
            joinColumns = {@JoinColumn(name = "shelter_id")},
            inverseJoinColumns = {@JoinColumn(name = "image_id")})
    @JsonIgnore
    private List<Image> images;

}
