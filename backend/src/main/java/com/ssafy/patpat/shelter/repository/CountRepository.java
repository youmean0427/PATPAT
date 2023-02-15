package com.ssafy.patpat.shelter.repository;

import com.ssafy.patpat.shelter.entity.Count;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CountRepository extends JpaRepository<Count, Long> {
    Count findByCountId(Long countId);
}
