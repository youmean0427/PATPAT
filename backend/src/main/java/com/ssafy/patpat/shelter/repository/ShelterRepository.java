package com.ssafy.patpat.shelter.repository;

import com.ssafy.patpat.shelter.mapping.ShelterDistanceMapping;
import com.ssafy.patpat.shelter.entity.Shelter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter,Long> {

    List<Shelter> findByGugunCode(String gugunCode);
    List<Shelter> findByShelterIdIn(List<Long> list);
    Page<Shelter> findByShelterIdIn(List<Long> list, PageRequest pageRequest);
    Page<Shelter> findBySidoCodeAndGugunCode(String sidoCode, String gugunCode, PageRequest pageRequest);
    Page<Shelter>  findBySidoCode(String sidoCode,PageRequest pageRequest);
    Shelter findByShelterId(Long shelterId);
    Shelter findByNameAndRegNumber(String name, String regNumber);

    @Query(value = "SELECT * , (6371 * acos ( cos ( radians(?) )* cos( radians( latitude ) )* cos( radians( longitude ) - radians(?) )+ sin ( radians(?) ) * sin( radians( latitude )))) AS distance FROM shelter where distance < 100",nativeQuery = true)
    Page<ShelterDistanceMapping> findAllShelter(BigDecimal a, BigDecimal b , BigDecimal c, PageRequest pageRequest);

}
