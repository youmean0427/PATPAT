package com.ssafy.patpat.volunteer.entity;

import com.ssafy.patpat.common.code.Reservation;
import com.ssafy.patpat.shelter.entity.Shelter;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class VolunteerNotice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long noticeId;

    @Column(name = "title")
    private String title;

    @Column(name = "volunteer_date")
    private String volunteerDate;

    @Column(name = "reservation_state_code")
    private Reservation reservationStateCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;

    @OneToMany(mappedBy = "volunteerNotice")
    private List<VolunteerSchedule> volunteerSchedules;

    @PrePersist
    public void prePersist() {
        this.reservationStateCode = this.reservationStateCode == null ? Reservation.대기중 : this.reservationStateCode;
    }

}
