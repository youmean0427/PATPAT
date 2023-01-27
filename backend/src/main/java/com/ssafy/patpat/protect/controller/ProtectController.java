package com.ssafy.patpat.protect.controller;

import com.ssafy.patpat.common.dto.FileDto;
import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.protect.dto.ProtectDto;
import com.ssafy.patpat.protect.dto.RequestProtectDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/protect")
public class ProtectController {

    /**
     * 보호동물 리스트
     * @return
     */
    @GetMapping
    public ResponseEntity<Object> selectProtectList(RequestProtectDto requestProtectDto){
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
                    .body(list);
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
    public ResponseEntity<Object> detailProtect(@PathVariable int protectId){
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
    public ResponseEntity<Object> insertProtect(ProtectDto protectDto, MultipartFile[] uploadFile){
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
    public ResponseEntity<Object> updateProtect(@PathVariable int protectId, MultipartFile[] uploadFile, ProtectDto protectDto){
        //서비스 호출 코드
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ProtectDto());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }

}
