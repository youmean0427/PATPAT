package com.ssafy.patpat.shelter.repository;

import com.ssafy.patpat.shelter.entity.ShelterImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShelterImageRepository extends JpaRepository<ShelterImage,String> {
    List<ShelterImage> findByShelterId(int shelterId);
}
