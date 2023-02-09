package com.ssafy.patpat.consulting.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consulting {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long consultingId;
    private Long shelterId;
    private Long userId;
    private Integer stateCode;
    private LocalDate registDate;
    private Integer timeCode;
    private Long spDogId;



    public void updateConsulting(int stateCode){
        this.stateCode = stateCode;
    }
}
