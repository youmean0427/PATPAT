package com.ssafy.patpat.consulting.repository;

import com.ssafy.patpat.consulting.entity.Consulting;
import com.ssafy.patpat.consulting.entity.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeRepository extends JpaRepository<Time, String> {
    //List<Time> findbyShelterId(int findbyShelterId);
}
