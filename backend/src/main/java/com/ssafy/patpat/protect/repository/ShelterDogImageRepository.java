package com.ssafy.patpat.protect.repository;

import com.ssafy.patpat.protect.entity.ShelterDogImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShelterDogImageRepository extends JpaRepository<ShelterDogImage,String> {
   List<ShelterDogImage> findByspDogId(int spDogId);

}
