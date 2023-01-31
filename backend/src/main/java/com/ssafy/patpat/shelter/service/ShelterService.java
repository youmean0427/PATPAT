package com.ssafy.patpat.shelter.service;

import com.ssafy.patpat.common.code.Breed;
import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.shelter.dto.BreedDto;
import com.ssafy.patpat.shelter.dto.RequestShelterDto;
import com.ssafy.patpat.shelter.dto.ShelterDto;
import com.ssafy.patpat.shelter.entity.Gugun;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.shelter.entity.Sido;
import org.springframework.web.multipart.MultipartFile;

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

    ShelterDto detailShelter(int shelterId);
    BreedDto selectBreedByMbti(String mbtiId);

    ResponseMessage updateShelter(String shelterId, List<MultipartFile> uploadFile, ShelterDto shelterDto);

    ResponseMessage AuthShelter(String authCode);
}
