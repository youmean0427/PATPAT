package com.ssafy.patpat.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userid;

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

    @Column(name = "method")
    @NotNull
    private String method;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "refresh_token")
    private String refreshToken;

    @JsonIgnore
    @Column(name = "activated")
    private boolean activated;

    @Column(name = "password")
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

    public void setPassword(String password){
        this.password = password;
    }
}
