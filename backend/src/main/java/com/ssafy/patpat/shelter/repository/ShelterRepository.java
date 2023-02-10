package com.ssafy.patpat.shelter.repository;

import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.shelter.mapping.ShelterNameMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter,Long> {

    List<Shelter> findByGugunCode(String gugunCode);
    List<Shelter> findByShelterIdIn(List<Long> list);
    Page<Shelter> findByShelterIdIn(List<Long> list, PageRequest pageRequest);
    Page<Shelter> findBySidoCodeAndGugunCode(String sidoCode, String gugunCode, PageRequest pageRequest);
    Page<Shelter>  findBySidoCode(String sidoCode,PageRequest pageRequest);
    Shelter findByShelterId(Long shelterId);
    Shelter findByNameAndRegNumber(String name, String regNumber);

}
