package com.ssafy.patpat.consulting.entity;

import com.ssafy.patpat.common.code.ConsultingState;
import com.ssafy.patpat.common.code.ProtectState;
import com.ssafy.patpat.common.code.TimeCode;
import com.ssafy.patpat.protect.entity.ShelterProtectedDog;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.user.entity.User;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consulting {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long consultingId;

    private ConsultingState consultingState;
    private LocalDate consultingDate;
    private LocalDateTime registDate;
    private TimeCode timeCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sp_dog_id")
    private ShelterProtectedDog shelterProtectedDog;



    public void updateConsulting(ConsultingState consultingState){
        this.consultingState = consultingState;
    }

    @PrePersist
    public void prePersist() {
        this.registDate = this.registDate == null ? LocalDateTime.now() : this.registDate;
    }
}
