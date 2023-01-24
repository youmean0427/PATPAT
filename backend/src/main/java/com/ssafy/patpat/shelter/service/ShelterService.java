package com.ssafy.patpat.shelter.service;

import com.ssafy.patpat.shelter.Breed;
import com.ssafy.patpat.shelter.dto.RequestParamMbtiDto;
import com.ssafy.patpat.shelter.dto.RequestParamShelterInsertDto;
import com.ssafy.patpat.shelter.dto.ResultDto;
import com.ssafy.patpat.shelter.entity.Gugun;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.shelter.entity.Sido;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface ShelterService {
    List<Sido> sidoList();
    List<Gugun> gugunList(String sidoCode);
    List<Shelter> shelterList(RequestParamMbtiDto dto);
    List<Shelter> shelterListInVolunteer(String sidoCode, String gugunCode);
    List<Breed> breedListBasedSidoCode(String sidoCode);
    List<Breed> breedListBasedSidoCodeAndGugunCode(String sidoCode, String gugunCode);
    ResultDto insertShelter(RequestParamShelterInsertDto requestParamShelterInsertDto);
}
