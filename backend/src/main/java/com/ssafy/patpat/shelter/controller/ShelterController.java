package com.ssafy.patpat.shelter.controller;

import com.ssafy.patpat.shelter.dto.RequestParamMbtiDto;
import com.ssafy.patpat.shelter.entity.Gugun;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.shelter.entity.Sido;
import com.ssafy.patpat.shelter.service.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shelter")
public class ShelterController {
    @Autowired
    ShelterService service;
    @GetMapping("/sido")
    public List<Sido> selectSidoList(){
        return service.sidoList();
    }
    @GetMapping("/gugun")
    public List<Gugun> selectGugunList(@RequestParam String sidoCode){
        return service.gugunList(sidoCode);
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

//    @GetMapping("/breed")
//    public String selectDogTypeList(){
//        return "시도, 구군, 보호소별 존재하는 모든 견종 리스트";
//    }
//    @GetMapping("/{shelterId}")
//    public String selectShelter(){
//        return "보호소 정보 보기(마이페이지 초기 데이터)";
//    }
//    @PostMapping
//    public String insertShelter(){
//        return "보호소 등록 성공,실패";
//    }
//    @PostMapping("/auth")
//    public String authenticateShelter(){
//        return "보호소 인증 성공,실패";
//    }
//    @PutMapping
//    public String updateShelter(){
//        return "보호소 수정 성공,실패";
//    }
//    @DeleteMapping
//    public String deleteShelter(){
//        return "보호소 삭제 성공,실패";
//    }

}
