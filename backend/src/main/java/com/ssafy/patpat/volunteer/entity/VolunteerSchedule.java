package com.ssafy.patpat.volunteer.entity;

import com.ssafy.patpat.common.code.Reservation;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VolunteerSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "volunteer_id")
    private Long volunteerId;

    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "volunteer_date")
    private String volunteerDate;

    @NotNull
    @Column(name = "start_time")
    private LocalDateTime startTime;

    @NotNull
    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "total_capacity")
    private Integer totalCapacity;

    @Column(name = "guide_line")
    private String guideLine;

    @Column(name = "reservation_state_code")
    private Reservation reservationStateCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;

    @PrePersist
    public void prePersist() {
        this.reservationStateCode = this.reservationStateCode == null ? Reservation.대기중 : this.reservationStateCode;
    }
}
