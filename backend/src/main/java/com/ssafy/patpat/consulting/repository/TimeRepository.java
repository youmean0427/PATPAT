package com.ssafy.patpat.consulting.repository;

import com.ssafy.patpat.consulting.entity.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long> {
    List<Time> findByShelterShelterIdAndActiveTrue(Long shelterId);
}
