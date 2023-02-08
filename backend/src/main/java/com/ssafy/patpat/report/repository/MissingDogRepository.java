package com.ssafy.patpat.report.repository;

import com.ssafy.patpat.report.entity.MissingDog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissingDogRepository extends JpaRepository<MissingDog,Long> {
    Page<MissingDog> findByGenderAndBreedId(int gender, Long breedId , PageRequest pageRequest);
    Page<MissingDog> findByGender(int gender, PageRequest pageRequest);
    Page<MissingDog> findByBreedId(Long breedId, PageRequest pageRequest);
    Page<MissingDog> findByUserId(Long userId, PageRequest pageRequest);
    MissingDog findByMissingId(Long missingId);
}
