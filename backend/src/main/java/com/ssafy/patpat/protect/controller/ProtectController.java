package com.ssafy.patpat.protect.controller;

import com.ssafy.patpat.common.dto.FileDto;
import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.protect.dto.ProtectDto;
import com.ssafy.patpat.protect.dto.RequestProtectDto;
import com.ssafy.patpat.protect.service.ProtectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/protects")
@Api(tags = {"03. Protect"},description = "보호 동물 서비스")
public class ProtectController {
    @Autowired
    ProtectService service;
    /**
     * 보호동물 리스트
     * @return
     */
    @GetMapping
    @ApiOperation(value = "보호동물 리스트", notes = "{code==0 안락사, code==1 최신순}")
    public ResponseEntity<Object> selectProtectList(RequestProtectDto requestProtectDto){
        //서비스 호출 코드
        List<ProtectDto> protectDtoList = service.selectProtectList(requestProtectDto);
        if(protectDtoList != null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(protectDtoList);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 보호동물 리스트(보호소별)
     * @return
     */
    @GetMapping("/{shelterId}/shelter")
    @ApiOperation(value = "보호동물 리스트", notes = "{code==0 안락사, code==1 최신순, code==2 해당 보호소가 가진 강아지 리스트}")
    public ResponseEntity<Object> selectProtectListByShelter(@PathVariable int shelterId, RequestProtectDto requestProtectDto){
        //서비스 호출 코드
        List<ProtectDto> protectDtoList = service.selectProtectListByShelter(shelterId,requestProtectDto);
        if(protectDtoList != null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(protectDtoList);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 보호동물 상세
     * @return
     */
    @GetMapping("/{protectId}")
    @ApiOperation(value = "보호동물 상세", notes = "보호동물 상세 조회")
    public ResponseEntity<Object> detailProtect(@PathVariable int protectId){
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
    public ResponseEntity<ResponseMessage> insertProtect(ProtectDto protectDto, @RequestPart List<MultipartFile> uploadFile){
        //서비스 호출 코드
        ResponseMessage responseMessage = service.insertProtect(protectDto,uploadFile);

        if(responseMessage.getMessage()=="SUCCESS"){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseMessage);
        }else{
            responseMessage.setMessage("FAIL");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(responseMessage);
        }
    }
    /**
     * 보호동물 수정
     * @return
     */
    @PostMapping("/{protectId}")
    @ApiOperation(value = "보호동물 수정", notes = "보호동물 수정")
    public ResponseEntity<ResponseMessage> updateProtect(@PathVariable int protectId, List<MultipartFile> uploadFile, ProtectDto protectDto){
        //서비스 호출 코드
        ResponseMessage responseMessage = service.updateProtect(protectId,uploadFile);

        if(responseMessage.getMessage().equals("SUCCESS")){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseMessage);
        }else{
            responseMessage.setMessage("FAIL");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(responseMessage);
        }
    }

}
