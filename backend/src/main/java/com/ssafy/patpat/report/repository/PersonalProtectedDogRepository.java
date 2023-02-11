package com.ssafy.patpat.report.repository;

import com.ssafy.patpat.common.code.category.Gender;
import com.ssafy.patpat.protect.entity.ShelterProtectedDog;
import com.ssafy.patpat.report.entity.PersonalProtectedDog;
//import com.ssafy.patpat.report.entity.PersonalProtectedDogImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PersonalProtectedDogRepository extends JpaRepository<PersonalProtectedDog,Long> {
    Page<PersonalProtectedDog> findByGenderAndBreedBreedId(Gender gender, Long breedId , PageRequest pageRequest);
    Page<PersonalProtectedDog> findByGender(Gender gender, PageRequest pageRequest);
    Page<PersonalProtectedDog> findByBreedBreedId(Long breedId, PageRequest pageRequest);
    PersonalProtectedDog findByPpDogId(Long ppDogId);
    @Query(value = "select * , (6371 * acos ( cos ( radians(:a) )* cos( radians( lat ) )* cos( radians( log) - radians(:b) )+ sin ( radians(:c) ) * sin( radians( lat )))) as distance from test where regist_date >= :localDate group by id having distance < 15",nativeQuery = true)
    List<PersonalProtectedDog> selectBydistance(BigDecimal a, BigDecimal b , BigDecimal c, LocalDate localDate);
}
