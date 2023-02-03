package com.ssafy.patpat.volunteer.repository;

import com.ssafy.patpat.volunteer.entity.VolunteerSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerScheduleRepository extends JpaRepository<VolunteerSchedule, Long> {
    List<VolunteerSchedule> findByShelterId(Long shelterId);
}
