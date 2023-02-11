package com.ssafy.patpat.protect.repository;

import com.ssafy.patpat.common.code.ProtectState;
import com.ssafy.patpat.protect.entity.ShelterProtectedDog;
import com.ssafy.patpat.protect.mapping.ShelterIdMapping;
import io.swagger.models.auth.In;
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

    List<ShelterProtectedDog> findByBreedBreedIdAndSidoCodeAndGugunCode(Long breedId, String sidoCode, String gugunCode);
    List<ShelterProtectedDog> findByBreedBreedIdAndSidoCode(Long breedId,String sidoCode);
    List<ShelterProtectedDog> findBySidoCodeAndGugunCode(String sidoCode, String gugunCode);
    List<ShelterProtectedDog> findBySidoCode(String sidoCode);
    List<ShelterIdMapping> findDistinctBySidoCodeAndBreedBreedId(String sidoCode, Long breedId);
    Integer countShelterIdBySidoCodeAndBreedBreedId(String sidoCode, Long breedId);
    List<ShelterIdMapping> findDistinctByBreedBreedId(Long breedId);
    List<ShelterProtectedDog> findDistinctShelterIdBySidoCode(String sidoCode);
//    List<ShelterIdMapping> findDistinctBySidoCodeAndBreedBreedId(String sidoCode,Long breedId);
    List<ShelterProtectedDog> findDistinctShelterIdBySidoCodeAndGugunCode(String sidoCode,String gugunCode);
    List<ShelterIdMapping> findDistinctBySidoCodeAndGugunCodeAndBreedBreedId(String sidoCode,String gugunCode,Long breedId);
}
