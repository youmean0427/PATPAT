package com.ssafy.patpat.shelter.service;

import com.ssafy.patpat.common.dto.ResponseListDto;
import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.shelter.dto.*;
import com.ssafy.patpat.shelter.entity.Gugun;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.shelter.entity.Sido;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ShelterService {
    List<Sido> sidoList();
    List<Gugun> gugunList(String sidoCode);
    ResponseListDto shelterList(RequestShelterDto dto);
    List<ShelterDto> shelterListInVolunteer(String gugunCode);
    ShelterDto detailShelter(Long shelterId);
    BreedDto selectBreedByMbti(String mbtiId);
    AuthCodeDto insertShelter(RequestParamShelterInsertDto requestParamShelterInsertDto);

    ResponseMessage updateShelter(ShelterDto shelterDto, List<MultipartFile> uploadFile) throws Exception;

    ResponseMessage AuthShelter(Long shelterId, String authCode);

    String getAuthCode(Long shelterId);

    List<BreedDto> selectBreedList();

    MbtiMapDto selectBreedCountByMbti(Long breedId);

    List<ShelterNameDto> selectShelterAll();

    Boolean deleteShelter(Long shelterId);

}
