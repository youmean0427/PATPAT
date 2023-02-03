package com.ssafy.patpat.report.repository;

import com.ssafy.patpat.consulting.entity.Time;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Time,String> {
//    List<Report> findAll(PageRequest pageRequest);
//    List<Report> findByGender(int gender , PageRequest pageRequest);
//    List<Report> findByBreedId(int breedId);
//    List<Report> findByGenderAndBreedIt(int gender, int breedId);
}
