package com.ssafy.patpat.shelter.repository;

import com.ssafy.patpat.shelter.entity.Shelter;
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
    List<Shelter> findByShelterIdIn(List<Integer> list);
    Shelter findByShelterId(int shelterId);

}
