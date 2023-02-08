package com.ssafy.patpat.protect.service;

import com.ssafy.patpat.common.dto.ResponseListDto;
import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.protect.dto.ProtectDto;
import com.ssafy.patpat.protect.dto.RequestProtectDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProtectService {
    ResponseListDto selectProtectList(RequestProtectDto requestProtectDto);

    ProtectDto detailProtect(Long protectId);

    ResponseMessage insertProtect(ProtectDto protectDto, List<MultipartFile> uploadFile);

    ResponseMessage updateProtect(Long protectId, List<MultipartFile> uploadFile, ProtectDto protectDto);

    ResponseListDto selectProtectListByShelter(RequestProtectDto requestProtectDto);
}
