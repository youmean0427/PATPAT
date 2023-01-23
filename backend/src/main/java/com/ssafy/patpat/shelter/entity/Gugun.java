package com.ssafy.patpat.shelter.entity;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gugun_Code")
public class Gugun{
    @Id
    @Column(name = "code")
    String code;
    String sidoCode;
    String name;
}
