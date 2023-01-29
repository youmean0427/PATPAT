//package com.ssafy.patpat.shelter.controller;
//
//import com.ssafy.patpat.board.dto.RequestBoardDto;
//import com.ssafy.patpat.common.dto.ResponseMessage;
//import com.ssafy.patpat.shelter.dto.*;
//import com.ssafy.patpat.shelter.entity.Gugun;
//import com.ssafy.patpat.shelter.entity.Sido;
//import com.ssafy.patpat.shelter.service.ShelterService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping("/shelter")
//@Api(tags = {"05. Shelter"},description = "보호소 관련 서비스")
//public class ShelterController {
//    @Autowired
//    ShelterService service;
//
//    /**
//     * 시도 리스트 반환
//     * @return
//     */
//    @GetMapping("/sido")
//    @ApiOperation(value = "시도 리스트 조회", notes = "시도 리스트 조회")
//    public ResponseEntity<ArrayList<Sido>> selectSidoList(RequestBoardDto requestBoardDto){
//        //service 호출
//        if(true){
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(new ArrayList<Sido>());
//        }else{
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(new ArrayList<Sido>());
//        }
//    }
//    /**
//     * 구군 리스트 반환
//     * @return
//     */
//    @GetMapping("/gugun")
//    @ApiOperation(value = "구군 리스트 조회", notes = "구군 리스트 조회")
//    public ResponseEntity<ArrayList<Gugun>> selectGugunList(@RequestParam String sidoCode){
//        //service 호출
//        ArrayList<Gugun> gugunList = (ArrayList<Gugun>) service.gugunList(sidoCode);
//        if(true){
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(gugunList);
//        }else{
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(gugunList);
//        }
//    }
//    /**
//     * 견종 정보 반환
//     * @return
//     */
//    @GetMapping("/mbti/{mbtiId}")
//    @ApiOperation(value = "견종 정보 반환", notes = "견종 정보 반환(이미지, 견종)")
//    public ResponseEntity<BreedDto> selectRandomBreed(@RequestParam String mbtiId){
//        //service 호출
//        BreedDto breedDto = new BreedDto();
//        if(true){
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(breedDto);
//        }else{
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(breedDto);
//        }
//    }
//    /**
//     * 보호소 조회
//     * @return
//     */
//    @GetMapping
//    @ApiOperation(value = "보호소 조회", notes = "보호소 리스트 조회")
//    public ResponseEntity<ArrayList<ShelterDto>> selectShelterList(RequestShelterDto requestShelterDto){
//        //service 호출
//        if(true){
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(new ArrayList<ShelterDto>());
//        }else{
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(new ArrayList<ShelterDto>());
//        }
//    }
//    /**
//     * 보호소 상세 조회
//     * @return
//     */
//    @GetMapping("/{shelterId}")
//    @ApiOperation(value = "보호소 상세 조회", notes = "보호소 상세 조회")
//    public ResponseEntity<ShelterDto> detailShelter(@PathVariable int shelterId){
//        if(true){
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(new ShelterDto());
//        }else{
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(new ShelterDto());
//        }
//    }
//    /**
//     * 보호소 등록
//     * @return
//     */
//    @PostMapping
//    @ApiOperation(value = "보호소 등록", notes = "보호소 등록")
//    public ResponseEntity<ResponseMessage> insertShelter(RequestShelterInsertDto requestShelterInsertDto){
//        if(true){
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(new ResponseMessage("SUCCESS"));
//        }else{
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(new ResponseMessage("FAIL"));
//        }
//    }
//    /**
//     * 보호소 수정
//     * @return
//     */
//    @PostMapping("/{shelterId}")
//    @ApiOperation(value = "보호소 수정", notes = "보호소 수정")
//    public ResponseEntity<ResponseMessage> updateShelter(@PathVariable String shelterId, MultipartFile[] uploadFile, ShelterDto shelterDto){
//        if(true){
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(new ResponseMessage("SUCCESS"));
//        }else{
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(new ResponseMessage("FAIL"));
//        }
//    }
//    /**
//     * 보호소 인증
//     * @return
//     */
//    @GetMapping("/auth/{authCode}")
//    @ApiOperation(value = "보호소 인증", notes = "보호소 인증")
//    public ResponseEntity<ResponseMessage> authShelter(@PathVariable String authCode){
//        if(true){
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(new ResponseMessage("SUCCESS"));
//        }else{
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(new ResponseMessage("FAIL"));
//        }
//    }
//}
