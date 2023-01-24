package com.ssafy.patpat.shelter.controller;

import com.ssafy.patpat.shelter.Breed;
import com.ssafy.patpat.shelter.dto.RequestParamMbtiDto;
import com.ssafy.patpat.shelter.dto.RequestParamShelterInsertDto;
import com.ssafy.patpat.shelter.dto.ResultDto;
import com.ssafy.patpat.shelter.entity.Gugun;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.shelter.entity.Sido;
import com.ssafy.patpat.shelter.service.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/shelter")
public class ShelterController {
    @Autowired
    ShelterService service;

    private final int SUCCESS = 1;
    @GetMapping("/sido")
    public List<Sido> selectSidoList(){
        return service.sidoList();
    }
    @GetMapping("/gugun")
    public List<Gugun> selectGugunList(@RequestParam String sidoCode){
        return service.gugunList(sidoCode);
    }
    @GetMapping("/breed/sido")
    public List<Breed> selectBreedListBasedSidoCode(@RequestParam String sidoCode){
        return service.breedListBasedSidoCode(sidoCode);
    }
    @GetMapping("/breed/all")
    public List<Breed> selectBreedListBasedSidoCodeAndGugunCode(@RequestParam String sidoCode, @RequestParam String gugunCode){
        return service.breedListBasedSidoCodeAndGugunCode(sidoCode,gugunCode);
    }

    @GetMapping("/breed")
    public List<Shelter> selectBreedShelterList(RequestParamMbtiDto dto){
        //견종 기반 검색 Dto sido기반시 구군 빈문자열 들어옴;
        return service.shelterList(dto);
    }
    @GetMapping("/volunteer")
    public List<Shelter> selectVolunteerShelterList(@RequestParam String sidoCode , @RequestParam String gugunCode){
        //견종 기반 검색 Dto sido기반시 구군 빈문자열 들어옴;
        return service.shelterListInVolunteer(sidoCode, gugunCode);
    }

//    @GetMapping("/{shelterId}")
//    public String selectShelter(){
//        return "보호소 정보 보기(마이페이지 초기 데이터)";
//    }
    @PostMapping
    public ResultDto insertShelter(@RequestBody RequestParamShelterInsertDto requestParamShelterInsertDto) {
        /**
         * 등록에 성공하면 암호화 등록코드기반 암호화 한 것을 내려준다.
         * 사용자는 /auth url에 해당 코드를 가지고 요청을 보내면
         * 유저에 보호소 접근 권한이 주어진다.
         */
        return service.insertShelter(requestParamShelterInsertDto);
    }
    @PostMapping("/auth")
    public String authenticateShelter(){
        return "보호소 인증 성공,실패";
        /**
         * 등록에 성공하면 보호소 접근 권한 주어짐
         */
    }
//    @PutMapping
//    public String updateShelter(){
//        return "보호소 수정 성공,실패";
//    }
}
