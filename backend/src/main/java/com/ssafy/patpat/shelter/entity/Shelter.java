package com.ssafy.patpat.shelter.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonValueInstantiator;
import com.ssafy.patpat.common.entity.Image;
import com.ssafy.patpat.consulting.entity.Consulting;
import com.ssafy.patpat.consulting.entity.Time;
import com.ssafy.patpat.protect.entity.ShelterProtectedDog;
import com.ssafy.patpat.user.entity.Owner;
import com.ssafy.patpat.user.entity.User;
import com.ssafy.patpat.volunteer.dto.VolunteerNoticeDto;
import com.ssafy.patpat.volunteer.entity.VolunteerNotice;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    private Long shelterId;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String name;
    private String regNumber;
    private String sidoCode;
    private String gugunCode;
    private String authCode;

    @ColumnDefault("소개글을 작성해 주세요.")
    private String info;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL)
    private List<VolunteerNotice> volunteerNotices;

    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL)
    private List<Consulting> consultings;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "shelter_image",
            joinColumns = {@JoinColumn(name = "shelter_id")},
            inverseJoinColumns = {@JoinColumn(name = "image_id")})
    private List<Image> images;

    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL)
    private List<Time> times;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_shelter",
            joinColumns = {@JoinColumn(name = "shelter_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<User> users;

    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL)
    private List<ShelterProtectedDog> shelterProtectedDogs;

}
