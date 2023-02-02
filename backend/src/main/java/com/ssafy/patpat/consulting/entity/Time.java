package com.ssafy.patpat.consulting.entity;

import com.ssafy.patpat.shelter.entity.Shelter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Time {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int timeId;
    public int timeCode;
    public int state;
}
