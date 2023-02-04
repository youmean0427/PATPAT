package com.ssafy.patpat.report.repository;

import com.ssafy.patpat.report.entity.MissingDog;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissingDogRepository extends JpaRepository<MissingDog,String> {
    List<MissingDog> findByGenderAndBreedId(int gender, int breedId , PageRequest pageRequest);
    List<MissingDog> findByGender(int gender, PageRequest pageRequest);
    List<MissingDog> findByBreedId(int breedId, PageRequest pageRequest);
    List<MissingDog> findByUserId(int userId, PageRequest pageRequest);
    MissingDog findByMissingId(int missingId);
}
