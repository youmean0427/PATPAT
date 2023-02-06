package com.ssafy.patpat.volunteer.repository;

import com.ssafy.patpat.common.code.Reservation;
import com.ssafy.patpat.volunteer.entity.VolunteerReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerReservationRepository extends JpaRepository<VolunteerReservation,Long> {
    List<VolunteerReservation> findWithVolunteerScheduleByVolunteerScheduleScheduleIdAndReservationStateCode(Long scheduleId, Reservation reservation);
}
