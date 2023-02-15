package com.ssafy.patpat.shelter.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Count {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long countId;
    private Long count;
}
