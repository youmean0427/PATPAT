package com.ssafy.patpat.protect.service;

import com.ssafy.patpat.common.dto.ResponseListDto;
import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.protect.dto.ProtectDto;
import com.ssafy.patpat.protect.dto.RequestProtectDto;
import com.ssafy.patpat.shelter.dto.ShelterDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProtectService {
    ResponseListDto selectProtectList(RequestProtectDto requestProtectDto);

    ProtectDto detailProtect(Long protectId);

    ResponseMessage insertProtect(ProtectDto protectDto, List<MultipartFile> uploadFile);

    ResponseMessage updateProtect(Long protectId, List<MultipartFile> uploadFile, ProtectDto protectDto);

    ResponseListDto selectProtectListByShelter(RequestProtectDto requestProtectDto);

    List<ProtectDto> selectProtectListByShelterAdmin(Long shelterId);
    ResponseMessage insertBatchesProtect(ShelterDto shelterDto, MultipartFile uploadFile) throws IOException;

    List<String> deleteDogImage();

    Boolean deleteDog(Long spDogId);
}
