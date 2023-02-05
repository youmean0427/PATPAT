package com.ssafy.patpat.protect.repository;

import com.ssafy.patpat.protect.entity.ShelterProtectedDog;
import com.ssafy.patpat.protect.mapping.ShelterIdMapping;
import io.swagger.models.auth.In;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShelterProtectedDogRepository extends JpaRepository<ShelterProtectedDog, Integer> {
    List<ShelterProtectedDog> findByShelterIdAndStateCodeNotIn(int shelterId,List<Integer> stateCode, PageRequest pageRequest);
    List<ShelterProtectedDog> findByStateCodeNotIn(List<Integer> stateCode, PageRequest pageRequest);
    ShelterProtectedDog findBySpDogId(int spDogId);

    List<ShelterProtectedDog> findByBreedIdAndSidoCodeAndGugunCode(int breedId, String sidoCode, String gugunCode);
    List<ShelterProtectedDog> findByBreedIdAndSidoCode(int breedId,String sidoCode);
    List<ShelterProtectedDog> findBySidoCodeAndGugunCode(String sidoCode, String gugunCode);
    List<ShelterProtectedDog> findBySidoCode(String sidoCode);
    List<ShelterIdMapping> findDistinctBySidoCodeAndBreedId(String sidoCode, int breedId);
    Integer countShelterIdBySidoCodeAndBreedId(String sidoCode, int breedId);
    List<ShelterProtectedDog> findDistinctShelterIdByBreedId(int breedId);
    List<ShelterProtectedDog> findDistinctShelterIdBySidoCode(String sidoCode);
    List<ShelterProtectedDog> findDistinctShelterIdBySidoCodeAndBreedId(String sidoCode,int breedId);
}
