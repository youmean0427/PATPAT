package com.ssafy.patpat.consulting.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ssafy.patpat.common.code.TimeCode;
import com.ssafy.patpat.common.code.TimeState;
import com.ssafy.patpat.shelter.entity.Shelter;
import io.swagger.models.auth.In;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long timeId;
    private TimeCode timeCode;

    // 0 대기 1 승인 2 취소 3 거절
    private TimeState timeState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;

}
