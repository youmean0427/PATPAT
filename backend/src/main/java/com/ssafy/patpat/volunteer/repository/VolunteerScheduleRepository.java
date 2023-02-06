package com.ssafy.patpat.volunteer.repository;

import com.ssafy.patpat.common.code.Reservation;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.volunteer.dto.VolunteerShelterDto;
import com.ssafy.patpat.volunteer.entity.VolunteerSchedule;
import com.ssafy.patpat.volunteer.mapping.VolunteerShelterIdMapping;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerScheduleRepository extends JpaRepository<VolunteerSchedule, Long> {

//    @Query(nativeQuery = true, value = "select vs.shelter_id as shelterId, vs.volunteer_date as volunteerDate, s.name as name" +
//            " from volunteer_schedule vs, shelter s" +
//            " where vs.shelter_id = s.shelter_id" +
//            " and vs.volunteer_date > now()" +
//            " and vs.reservation_state_code = :reservationStateCode" +
//            " and s.gugun_code=:gugunCode" +
//            " group by vs.volunteer_date, vs.shelter_id" +
//            " order by vs.volunteer_date asc")
//    List<VolunteerShelterIdMapping> findShelter(int reservationStateCode, String gugunCode);

    List<VolunteerSchedule> findWithVolunteerNoticeByVolunteerNoticeNoticeIdAndReservationStateCode(Long notice, Reservation reservation);
}
