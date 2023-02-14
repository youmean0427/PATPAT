package com.ssafy.patpat.volunteer.repository;

import com.ssafy.patpat.common.code.Reservation;
import com.ssafy.patpat.user.entity.User;
import com.ssafy.patpat.volunteer.entity.VolunteerReservation;
import com.ssafy.patpat.volunteer.entity.VolunteerSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VolunteerReservationRepository extends JpaRepository<VolunteerReservation,Long> {
    Page<VolunteerReservation> findWithVolunteerScheduleByVolunteerScheduleScheduleIdAndReservationStateCodeNot(Long scheduleId, Reservation reservation, PageRequest pageRequest);

//    @Query(nativeQuery = true, value = "select * from volunteer_reservation" +
//            " where user_id=:userId" +
//            " order by field(reservation_state_code, 3, 0, 1, 2, 5, 4)," +
//            " volunteer_date asc limit :offset, :limit")
//    List<VolunteerReservation> findWithUserByUserUserIdAndReservationStateCode(Long userId, Integer offset, Integer limit);

    Page<VolunteerReservation> findByUserAndVolunteerDateGreaterThanEqual(User user, String volunteerDate, PageRequest pageRequest);

    /**
     * 3, 0, 1, 5 중에서만
     * */
    Optional<Long> countWithUserByUserUserIdAndReservationStateCodeIn(Long userId, List<Reservation> reservations);
<<<<<<< HEAD
    VolunteerReservation findByReservationId(Long reservationId);
=======

    List<VolunteerReservation> findByUserAndVolunteerSchedule(User user, VolunteerSchedule volunteerSchedule);

>>>>>>> b4cd8d64505effd281f5381cdc94a95b3a3e6f7d
}
