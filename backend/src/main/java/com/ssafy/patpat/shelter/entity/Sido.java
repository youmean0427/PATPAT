package com.ssafy.patpat.shelter.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sido_code")
public class Sido {
    @Id
    @Column(name = "code")
    String code;
    String name;
}
