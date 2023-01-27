package com.ssafy.patpat.shelter.service;

import com.ssafy.patpat.common.code.Breed;
import com.ssafy.patpat.shelter.dto.RequestShelterDto;
import com.ssafy.patpat.shelter.entity.Gugun;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.shelter.entity.Sido;

import java.util.List;
import java.util.Optional;

public interface ShelterService {
    List<Sido> sidoList();
    List<Gugun> gugunList(String sidoCode);
    List<Shelter> shelterList(RequestShelterDto dto);
    List<Shelter> shelterListInVolunteer(String sidoCode, String gugunCode);
    List<Breed> breedListBasedSidoCode(String sidoCode);
    List<Breed> breedListBasedSidoCodeAndGugunCode(String sidoCode, String gugunCode);
    //ResultInsertShelterDto insertShelter(RequestParamShelterInsertDto requestParamShelterInsertDto);

    Optional<Shelter> detailShelter(int shelterId);
    Breed randomBreed();
}
