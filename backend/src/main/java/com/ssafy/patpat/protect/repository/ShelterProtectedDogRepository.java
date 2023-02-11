package com.ssafy.patpat.protect.repository;

import com.ssafy.patpat.common.code.ProtectState;
import com.ssafy.patpat.protect.entity.ShelterProtectedDog;
import com.ssafy.patpat.protect.mapping.ShelterIdMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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

}
