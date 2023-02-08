package com.ssafy.patpat.report.repository;

import com.ssafy.patpat.report.entity.MissingDog;
import com.ssafy.patpat.report.entity.MissingDogImage;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissingDogImageRepository extends JpaRepository<MissingDogImage,String> {
    List<MissingDogImage> findByMissingId(int missingId);
    void deleteByMissingId(int missingId);
}
