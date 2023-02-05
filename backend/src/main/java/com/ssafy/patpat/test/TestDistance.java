package com.ssafy.patpat.test;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "test")
public class TestDistance {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private BigDecimal lat;
    private BigDecimal log;
}
