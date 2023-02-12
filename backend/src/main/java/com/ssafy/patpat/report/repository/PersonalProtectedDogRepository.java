package com.ssafy.patpat.report.repository;

import com.ssafy.patpat.common.code.category.Gender;
import com.ssafy.patpat.report.entity.PersonalProtectedDog;
//import com.ssafy.patpat.report.entity.PersonalProtectedDogImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalProtectedDogRepository extends JpaRepository<PersonalProtectedDog,Long> {
    Page<PersonalProtectedDog> findByGenderAndBreedBreedId(Gender gender, Long breedId , PageRequest pageRequest);
    Page<PersonalProtectedDog> findByGender(Gender gender, PageRequest pageRequest);
    Page<PersonalProtectedDog> findByBreedBreedId(Long breedId, PageRequest pageRequest);
    PersonalProtectedDog findByPpDogId(Long ppDogId);
}
