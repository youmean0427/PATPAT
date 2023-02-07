package com.ssafy.patpat.volunteer.repository;

import com.ssafy.patpat.common.code.Reservation;
import com.ssafy.patpat.volunteer.entity.VolunteerReservation;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VolunteerReservationRepository extends JpaRepository<VolunteerReservation,Long> {
    List<VolunteerReservation> findWithVolunteerScheduleByVolunteerScheduleScheduleIdAndReservationStateCode(Long scheduleId, Reservation reservation);

    @Query(nativeQuery = true, value = "select * from volunteer_reservation" +
            " where user_id=:userId" +
            " order by field(reservation_state_code, 3, 0, 1, 5)," +
            " volunteer_date asc limit :offset, :limit")
    List<VolunteerReservation> findWithUserByUserUserIdAndReservationStateCode(Long userId, Integer offset, Integer limit);

    /**
     * 3, 0, 1, 5 중에서만
     * */
    Optional<Integer> countWithUserByUserUserIdAndReservationStateCodeIn(Long userId, List<Reservation> reservations);
}
