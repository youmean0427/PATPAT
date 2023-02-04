package com.ssafy.patpat.report.repository;

import com.ssafy.patpat.report.entity.PersonalProtectedDog;
import com.ssafy.patpat.report.entity.PersonalProtectedDogImage;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalProtectedDogRepository extends JpaRepository<PersonalProtectedDog,String> {
    List<PersonalProtectedDog> findByGenderAndBreedId(int gender, int breedId , PageRequest pageRequest);
    List<PersonalProtectedDog> findByGender(int gender, PageRequest pageRequest);
    List<PersonalProtectedDog> findByBreedId(int breedId, PageRequest pageRequest);
    PersonalProtectedDog findByPpDogId(int ppDogId);
}
