package com.ssafy.patpat.report.repository;

import com.ssafy.patpat.common.code.category.Gender;
import com.ssafy.patpat.protect.entity.ShelterProtectedDog;
import com.ssafy.patpat.report.entity.MissingDog;
import com.ssafy.patpat.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface MissingDogRepository extends JpaRepository<MissingDog,Long> {
    Page<MissingDog> findByGenderAndBreedBreedId(Gender gender, Long breedId , PageRequest pageRequest);
    Page<MissingDog> findByGender(Gender gender, PageRequest pageRequest);
    Page<MissingDog> findByBreedBreedId(Long breedId, PageRequest pageRequest);
    Page<MissingDog> findByUserUserId(Long userId, PageRequest pageRequest);
    MissingDog findByMissingId(Long missingId);
    MissingDog findByUser(User user);

    @Query(nativeQuery = true,
            value = "select * , (6371 * acos ( cos ( radians(:a) )* cos( radians( latitude ) )* cos( radians( longitude )" +
                    " - radians(:b) )+ sin ( radians(:c) ) * sin( radians( latitude ))))" +
                    " as distance from MissingDog group by missing_id having distance < 15"
    )
    List<MissingDog> selectBydistance(BigDecimal a, BigDecimal b , BigDecimal c);
}
