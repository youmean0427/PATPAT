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
    public List<Shelter> selectShelterList(RequestParamMbtiDto dto){
        //"보호소 검색하기 (시도, 구군, 견종, 이름 필터 선택 후 카운팅 값 까지 포함한 결과) || 시도, 구군 봉사 공고 기반 보호소 검색";
        return service.shelterList(dto);
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
