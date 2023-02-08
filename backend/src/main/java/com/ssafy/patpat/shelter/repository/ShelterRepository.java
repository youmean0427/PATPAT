package com.ssafy.patpat.shelter.repository;

import com.ssafy.patpat.shelter.entity.Shelter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter,Integer> {

    List<Shelter> findByGugunCode(String gugunCode);
    List<Shelter> findByShelterIdIn(Set<Integer> list);
    List<Shelter> findByShelterIdIn(List<Integer> list,PageRequest pageRequest);
    List<Shelter> findBySidoCodeAndGugunCode(String sidoCode, String gugunCode, PageRequest pageRequest);
    List<Shelter>  findBySidoCode(String sidoCode,PageRequest pageRequest);
    Shelter findByShelterId(int shelterId);
    Shelter findByNameAndRegNumber(String name, String regNumber);

}
