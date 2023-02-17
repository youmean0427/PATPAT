package com.ssafy.patpat.alarm.entity;

import com.ssafy.patpat.common.code.MsgCode;
import com.ssafy.patpat.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alarmId;

    private MsgCode msgCode;

    private Boolean checkRead;

    private LocalDateTime registDate;

    private Long missingId;

    private Long shelterId;

    private Long spDogId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    public void prePersist() {
        this.registDate = this.registDate == null ? LocalDateTime.now() : this.registDate;
    }
}
