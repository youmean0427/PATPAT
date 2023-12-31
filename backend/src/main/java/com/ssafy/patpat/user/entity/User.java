package com.ssafy.patpat.user.entity;

import com.ssafy.patpat.alarm.entity.Alarm;
import com.ssafy.patpat.common.entity.Image;
import com.ssafy.patpat.consulting.entity.Consulting;
import com.ssafy.patpat.protect.entity.ShelterProtectedDog;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "age_range")
    private String ageRange;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "exp")
    @ColumnDefault("0")
    private Integer exp;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "provider")
    private String provider;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "password")
    private String password;

    public void updateExp(Integer exp){
        this.exp = exp;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Consulting> consultings;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private List<Authority> authorities;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_favorite",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "sp_dog_id")}
    )
    private List<ShelterProtectedDog> favoriteDogs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_shelter",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "shelter_id")}
    )
    private Shelter shelter;

    @OneToOne(mappedBy = "user")
    private Owner owner;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Alarm> alarms;

    @PrePersist
    public void prePersist() {
        this.exp = this.exp == null ? 0 : this.exp;
    }
}
