package com.ssafy.patpat.volunteer.repository;

import com.ssafy.patpat.common.code.Reservation;
import com.ssafy.patpat.volunteer.entity.VolunteerNotice;
import com.ssafy.patpat.volunteer.mapping.VolunteerShelterIdMapping;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerNoticeRepository extends JpaRepository<VolunteerNotice, Long> {

    List<VolunteerNotice> findWithShelterByShelterGugunCodeAndReservationStateCodeAndVolunteerDateGreaterThan(String gugunCode, Reservation reservation, String volunteerDate, PageRequest pageRequest);
    List<VolunteerNotice> findWithShelterByShelterShelterIdAndReservationStateCodeAndVolunteerDateGreaterThan(Integer shelterId, Reservation reservation, String volunteerDate, PageRequest pageRequest);
}
