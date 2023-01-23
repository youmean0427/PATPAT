package com.ssafy.patpat.shelter.repository;

import com.ssafy.patpat.shelter.entity.ShelterDog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShelterDogRepository extends JpaRepository<ShelterDog,Integer>{
    List<ShelterDog> findByBreedIdAndSidoCodeAndGugunCode(int breedId,String sidoCode, String gugunCode);
    List<ShelterDog> findByBreedIdAndSidoCode(int breedId,String sidoCode);
    List<ShelterDog> findBySidoCodeAndGugunCode(String sidoCode, String gugunCode);
    List<ShelterDog> findBySidoCode(String sidoCode);
}
