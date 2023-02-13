package com.ssafy.patpat.volunteer.entity;

import com.ssafy.patpat.common.code.Reservation;
import com.ssafy.patpat.user.entity.User;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class VolunteerReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long reservationId;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "reservation_state_code")
    private Reservation reservationStateCode;

    @Column(name = "volunteer_date")
    private String volunteerDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private VolunteerSchedule volunteerSchedule;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    public void prePersist() {
        this.reservationStateCode = this.reservationStateCode == null ? Reservation.대기중 : this.reservationStateCode;
    }
}
