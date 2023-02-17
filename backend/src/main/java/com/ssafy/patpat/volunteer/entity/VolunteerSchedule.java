package com.ssafy.patpat.volunteer.entity;

import com.ssafy.patpat.common.code.Reservation;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class VolunteerSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long scheduleId;

    @NotNull
    @Column(name = "start_time")
    private String startTime;

    @NotNull
    @Column(name = "end_time")
    private String endTime;

    @Column(name = "totalCapacity")
    private Integer totaclCapacity;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "guide_line")
    private String guideLine;

    @Column(name = "reservation_state_code")
    private Reservation reservationStateCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id")
    private VolunteerNotice volunteerNotice;

    @OneToMany(mappedBy = "volunteerSchedule", cascade = CascadeType.ALL)
    private List<VolunteerReservation> volunteerReservations;

    @PrePersist
    public void prePersist() {
        this.reservationStateCode = this.reservationStateCode == null ? Reservation.대기중 : this.reservationStateCode;
        this.capacity = this.capacity == null ? 0 : this.capacity;
    }
}
