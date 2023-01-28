package com.ssafy.patpat.protect.controller;

import com.ssafy.patpat.common.dto.FileDto;
import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.protect.dto.ProtectDto;
import com.ssafy.patpat.protect.dto.RequestProtectDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/protect")
@Api(tags = {"03. Protect"},description = "보호 동물 서비스")
public class ProtectController {

    /**
     * 보호동물 리스트
     * @return
     */
    @GetMapping
    @ApiOperation(value = "보호동물 리스트", notes = "{code==0 안락사, code==1 최신순, code==2 해당 보호소가 가진 강아지 리스트}")
    public ResponseEntity<List<ProtectDto>> selectProtectList(RequestProtectDto requestProtectDto){
        //서비스 호출 코드
        //dummy data
        List<ProtectDto> list = new ArrayList<>();
        FileDto fileDto = new FileDto(1L,"asd","파일","sd/sd/sd.png");
        FileDto fileDto1 = new FileDto(2L,"asdf","파일2","sd/sd/sssd.png");

        List<FileDto> fileUrlList = new ArrayList<>();
        fileUrlList.add(fileDto);
        fileUrlList.add(fileDto1);

        ProtectDto protectDto = ProtectDto.builder()
                .protectId(1)
                .protectName("견훈")
                .fileUrlList(fileUrlList).build();
        ProtectDto protectDto1 = ProtectDto.builder()
                .protectId(2)
                .protectName("재혁")
                .fileUrlList(fileUrlList).build();

        list.add(protectDto1);
        list.add(protectDto);
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ArrayList<ProtectDto>());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ArrayList<ProtectDto>());
        }
    }
    /**
     * 보호동물 상세
     * @return
     */
    @GetMapping("/{protectId}")
    @ApiOperation(value = "보호동물 상세", notes = "보호동물 상세 조회")
    public ResponseEntity<ResponseMessage> detailProtect(@PathVariable int protectId){
        //서비스 호출 코드
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage("SUCCESS"));
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
    public ResponseEntity<ResponseMessage> insertProtect(ProtectDto protectDto, MultipartFile[] uploadFile){
        //서비스 호출 코드
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage("SUCCESS"));
        }else{
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
    public ResponseEntity<ResponseMessage> updateProtect(@PathVariable int protectId, MultipartFile[] uploadFile, ProtectDto protectDto){
        //서비스 호출 코드
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage("SUCCESS"));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }

}
