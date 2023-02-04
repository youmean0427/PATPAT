package com.ssafy.patpat.report.repository;

import com.ssafy.patpat.report.entity.PersonalProtectedDog;
import com.ssafy.patpat.report.entity.PersonalProtectedDogImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalProtectedDogImageRepository extends JpaRepository<PersonalProtectedDogImage,String> {
    List<PersonalProtectedDogImage> findByPpDogId(int ppDogId);
}
