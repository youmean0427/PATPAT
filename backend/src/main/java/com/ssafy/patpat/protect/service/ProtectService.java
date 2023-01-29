package com.ssafy.patpat.protect.service;

import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.protect.dto.ProtectDto;
import com.ssafy.patpat.protect.dto.RequestProtectDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProtectService {
    List<ProtectDto> selectProtectList(RequestProtectDto requestProtectDto);

    ProtectDto detailProtect(int protectId);

    ResponseMessage insertProtect(ProtectDto protectDto, List<MultipartFile> uploadFile);

    ResponseMessage updateProtect(int protectId, List<MultipartFile> uploadFile);

    List<ProtectDto> selectProtectListByShelter(int shelterId ,RequestProtectDto requestProtectDto);
}
