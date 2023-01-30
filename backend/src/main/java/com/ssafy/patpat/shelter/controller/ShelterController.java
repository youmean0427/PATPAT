package com.ssafy.patpat.shelter.controller;

import com.ssafy.patpat.board.dto.RequestBoardDto;
import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.shelter.dto.*;
import com.ssafy.patpat.shelter.entity.Sido;
import com.ssafy.patpat.shelter.service.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/shelter")
public class ShelterController {
    @Autowired
    ShelterService service;

    /**
     * 시도 리스트 반환
     * @return
     */
    @GetMapping("/sido")
    public ResponseEntity<Object> selectSidoList(RequestBoardDto requestBoardDto){
        //service 호출
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ArrayList<Sido>());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 구군 리스트 반환
     * @return
     */
    @GetMapping("/gugun")
    public ResponseEntity<Object> selectGugunList(@RequestParam String sidoCode){
        //service 호출
        List gugunList = service.gugunList(sidoCode);
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(gugunList);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 견종 정보 반환
     * @return
     */
    @GetMapping("/mbti/{mbtiId}")
    public ResponseEntity<Object> selectRandomBreed(@RequestParam String mbtiId){
        //service 호출
        BreedDto breedDto = new BreedDto();
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(breedDto);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 보호소 조회
     * @return
     */
    @GetMapping
    public ResponseEntity<Object> selectShelterList(RequestShelterDto requestShelterDto){
        //service 호출
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ArrayList<ShelterDto>());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 보호소 상세 조회
     * @return
     */
    @GetMapping("/{shelterId}")
    public ResponseEntity<Object> detailShelter(@PathVariable int shelterId){
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ShelterDto());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 보호소 등록
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> insertShelter(RequestShelterInsertDto requestShelterInsertDto){
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage("SUCCESS"));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 보호소 수정
     * @return
     */
    @PostMapping("/{shelterId}")
    public ResponseEntity<Object> updateShelter(@PathVariable String shelterId, MultipartFile[] uploadFile, ShelterDto shelterDto){
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage("SUCCESS"));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 보호소 인증
     * @return
     */
    @PostMapping("/auth/{authCode}")
    public ResponseEntity<Object> authShelter(@PathVariable String authCode){
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage("SUCCESS"));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
}
