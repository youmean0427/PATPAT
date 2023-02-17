package com.ssafy.patpat.protect.controller;

import com.ssafy.patpat.common.dto.FileDto;
import com.ssafy.patpat.common.dto.ResponseListDto;
import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.protect.dto.ProtectDto;
import com.ssafy.patpat.protect.dto.RequestProtectDto;
import com.ssafy.patpat.protect.service.ProtectService;
import com.ssafy.patpat.shelter.dto.ShelterDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/protects")
@Api(tags = {"03. Protect"},description = "보호 동물 서비스")
public class ProtectController {
    @Autowired
    ProtectService service;
    /**
     * 보호동물 리스트
     * @return
     */
    @GetMapping
    //@PreAuthorize("hasAnyRole('USER')")
    @ApiOperation(value = "보호동물 리스트", notes = "{code==0 안락사, code==1 최신순, stateCode 넣으면 상태별}")
    public ResponseEntity<Object> selectProtectList(RequestProtectDto requestProtectDto){
        //서비스 호출 코드
        ResponseListDto protectDtoList = service.selectProtectList(requestProtectDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(protectDtoList);
    }
    /**
     * 보호동물 리스트(보호소별)
     * @return
     */
    @GetMapping("/shelters")
    //@PreAuthorize("hasAnyRole('USER')")
    @ApiOperation(value = "보호동물 리스트", notes = "{shelterId 필수, code==0 안락사, code==1 최신순, breedId 넣으면 상태별}")
    public ResponseEntity<Object> selectProtectListByShelter(RequestProtectDto requestProtectDto){
        //서비스 호출 코드
        ResponseListDto protectDtoList = service.selectProtectListByShelter(requestProtectDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(protectDtoList);
    }

    /**
     * 보호동물 리스트(보호소별)
     * @return
     */
    @GetMapping("/shelters/admin/{shelterId}")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "보호동물 관리 리스트", notes = "{shelterId 필수")
    public ResponseEntity<Object> selectProtectListByShelter(@PathVariable Long shelterId){
        //서비스 호출 코드
        List<ProtectDto> protectDtoList = service.selectProtectListByShelterAdmin(shelterId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(protectDtoList);
    }

    /**
     * 보호동물 상세
     * @return
     */
    @GetMapping("/{protectId}")
    @ApiOperation(value = "보호동물 상세", notes = "보호동물 상세 조회")
    public ResponseEntity<Object> detailProtect(@PathVariable Long protectId){
        ProtectDto protectDto = service.detailProtect(protectId);

        if(protectDto != null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(protectDto);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 보호동물 등록
     * @return
     */
    @PostMapping
    @ApiOperation(value = "보호동물 등록", notes = "보호동물 개별 등록")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ResponseMessage> insertProtect(ProtectDto protectDto, @RequestPart(required = false) List<MultipartFile> uploadFile){
        //서비스 호출 코드
        ResponseMessage responseMessage = service.insertProtect(protectDto,uploadFile);

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseMessage);
    }
    /**
     * 보호동물 일괄등록
     * @return
     */
    @PostMapping("/batches")
    @ApiOperation(value = "보호동물 일괄등록", notes = "보호동물 일괄등록")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ResponseMessage> insertBachProtect(ShelterDto shelterDto, @RequestPart(required = false) MultipartFile uploadFile){
        //서비스 호출 코드
        try{
            ResponseMessage responseMessage = service.insertBatchesProtect(shelterDto,uploadFile);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseMessage);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }

    }
    /**
     * 보호동물 수정
     * @return
     */
    @PostMapping("/{protectId}")
    @ApiOperation(value = "보호동물 수정", notes = "보호동물 수정")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ResponseMessage> updateProtect(@PathVariable Long protectId, @RequestPart(required = false) List<MultipartFile> uploadFile, ProtectDto protectDto){
        //서비스 호출 코드
        ResponseMessage responseMessage = service.updateProtect(protectId,uploadFile,protectDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseMessage);

    }

    @GetMapping("/delete/image")
    public ResponseEntity<Object> deleteImage(){
        List<String> files = service.deleteDogImage();
        return ResponseEntity.status(HttpStatus.OK)
                .body(files);
    }

}
