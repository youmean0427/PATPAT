package com.ssafy.patpat.shelter.repository;

import com.ssafy.patpat.shelter.entity.ShelterImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelterImageRepository extends JpaRepository<ShelterImage,String> {
    ShelterImage findByShelterId(int shelterId);
}
