package com.ssafy.patpat.protect.repository;

import com.ssafy.patpat.common.code.ProtectState;
import com.ssafy.patpat.protect.entity.ShelterProtectedDog;
import com.ssafy.patpat.protect.mapping.ShelterIdMapping;
import com.ssafy.patpat.test.TestMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShelterProtectedDogRepository extends JpaRepository<ShelterProtectedDog, Long> {
    Page<ShelterProtectedDog> findByShelterShelterIdAndStateCodeNotIn(Long shelterId, List<ProtectState> stateCode, PageRequest pageRequest);
    Page<ShelterProtectedDog> findByStateCodeNotIn(List<ProtectState> stateCode, PageRequest pageRequest);
    Page<ShelterProtectedDog> findByShelterShelterIdAndStateCode(Long shelterId,ProtectState stateCode, PageRequest pageRequest);
    Page<ShelterProtectedDog> findByStateCode(ProtectState stateCode, PageRequest pageRequest);
    ShelterProtectedDog findBySpDogId(Long spDogId);

    List<ShelterIdMapping> findDistinctByShelterSidoCodeAndBreedBreedId(String sidoCode, Long breedId);

    List<ShelterIdMapping> findDistinctByBreedBreedId(Long breedId);

    List<ShelterIdMapping> findDistinctByShelterSidoCodeAndShelterGugunCodeAndBreedBreedId(String sidoCode,String gugunCode,Long breedId);

    @Query(
            value = "select * , (6371 * acos ( cos ( radians(?) )* cos( radians( latitude ) )* cos( radians( longitude )- radians(?) )+ sin ( radians(?) ) * sin( radians( latitude )))) as distance from shelter_protected_dog where regist_date >= ? group by sp_dog_id having distance < 15",
            nativeQuery = true
    )
    List<ShelterProtectedDog> selectBydistance(BigDecimal a, BigDecimal b , BigDecimal c, LocalDate localDate);

    @Query(nativeQuery = true,
        value = "select count(*) from (select" +
                " *," +
                " (6371 * acos ( cos ( radians(:a ) )* cos( radians( latitude ) )* cos( radians( longitude )- radians( :b ) )+ sin ( radians( :c ) ) * sin( radians( latitude )))) as distance" +
                " from" +
                " shelter_protected_dog" +
                " where" +
                " regist_date >= :localDate" +
                " having" +
                " distance < 15) as sc")
    Long countDogByDistance(BigDecimal a, BigDecimal b , BigDecimal c, LocalDate localDate);

}
