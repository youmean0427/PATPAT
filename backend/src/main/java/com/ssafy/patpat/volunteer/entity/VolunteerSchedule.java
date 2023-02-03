package com.ssafy.patpat.volunteer.entity;

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

    @NotNull
    @Column(name = "shelter_id")
    private Long shelterId;

    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "volunteer_date")
    private LocalDate volunteerDate;

    @NotNull
    @Column(name = "start_time")
    private LocalDateTime startTime;

    @NotNull
    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "guide_line")
    private String guideLine;

    @NotNull
    @Column(name = "reservation_state_code")
    private Integer reservationStateCode;

    @PrePersist
    public void prePersist() {
        this.reservationStateCode = this.reservationStateCode == null ? 0 : this.reservationStateCode;
    }
}
