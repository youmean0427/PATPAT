package com.ssafy.patpat.shelter.repository;

import com.ssafy.patpat.shelter.entity.Breed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BreedRepository extends JpaRepository<Breed,String> {
    Breed findByBreedId(int breedId);
    List<Breed> findByBreedIdIn(Set<Integer> breedIdList);
    Breed findByName(String breedName);
}
