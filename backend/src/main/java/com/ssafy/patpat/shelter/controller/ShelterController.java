package com.ssafy.patpat.shelter.controller;

import com.ssafy.patpat.board.dto.RequestBoardDto;
import com.ssafy.patpat.common.dto.ResponseListDto;
import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.shelter.dto.*;
import com.ssafy.patpat.shelter.entity.Breed;
import com.ssafy.patpat.shelter.entity.Gugun;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.shelter.entity.Sido;
import com.ssafy.patpat.shelter.service.ShelterService;
import com.ssafy.patpat.shelter.service.ShelterServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/shelters")
@Api(tags = {"05. Shelter"},description = "보호소 관련 서비스")
public class ShelterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShelterServiceImpl.class);
    @Autowired
    ShelterService service;

    /**
     * 시도 리스트 반환
     * @return
     */
    @GetMapping("/sidos")
    @ApiOperation(value = "시도 리스트 조회", notes = "시도 리스트 조회")
    public ResponseEntity<Object> selectSidoList(RequestBoardDto requestBoardDto){
        //service 호출
        List<Sido> list = service.sidoList();
        if(list!=null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(list);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 구군 리스트 반환
     * @return
     */
    @GetMapping("/guguns")
    @ApiOperation(value = "구군 리스트 조회", notes = "구군 리스트 조회")
    public ResponseEntity<Object> selectGugunList(@RequestParam String sidoCode){
        //service 호출
        List<Gugun> gugunList = service.gugunList(sidoCode);
        if(gugunList!=null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(gugunList);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 총 견종 반환
     * @return
     */
    @GetMapping("/breeds")
    @ApiOperation(value = "견종 리스트 조회", notes = "견종 리스트")
    public ResponseEntity<Object> selectBreedList(){
        //service 호출
        List<BreedDto> breedDtoList = service.selectBreedList();
        if(breedDtoList!=null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(breedDtoList);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }

    /**
//     * 견종 정보 반환
//     * @return
//     */
    @GetMapping("/mbti/{mbtiId}")
    @ApiOperation(value = "견종 정보 반환", notes = "견종 정보 반환(이미지, 견종)")
    public ResponseEntity<Object> selectBreedByMbti(@PathVariable String mbtiId){
        //service 호출
        System.out.println(mbtiId);
        BreedDto breedDto = service.selectBreedByMbti(mbtiId);
        if(breedDto!=null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(breedDto);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 8도, 전체 해당 견종을 가진
     * @return
     */
    @GetMapping("/mbti/count/{breedId}")
    @ApiOperation(value = "견종 정보 반환", notes = "견종 정보 반환(이미지, 견종)")
    public ResponseEntity<Object> selectBreedCountByMbti(@PathVariable Long breedId){
        //service 호출
        MbtiMapDto mbtiMapDto = service.selectBreedCountByMbti(breedId);
        if(mbtiMapDto!=null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(mbtiMapDto);
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
    @ApiOperation(value = "보호소 조회", notes = "보호소 리스트 조회")
    public ResponseEntity<Object> selectShelterList(RequestShelterDto requestShelterDto){
        //service 호출
        try{
            ResponseListDto shelterDtoList = service.shelterList(requestShelterDto);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(shelterDtoList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }

    }
    /**
     * 보호소 상세 조회
     * @return
     */
    @GetMapping("/{shelterId}")
    @ApiOperation(value = "보호소 상세 조회", notes = "보호소 상세 조회")
    public ResponseEntity<Object> detailShelter(@PathVariable Long shelterId){
        ShelterDto shelterDto = service.detailShelter(shelterId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(shelterDto);

    }
    /**
     * 전국 보호소 목록
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "보호소 상세 조회", notes = "보호소 상세 조회")
    public ResponseEntity<Object> selectShelterAll(){
        List<ShelterNameDto> list = service.selectShelterAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(list);

    }
    /**
     * 보호소 등록
     * @return
     */
    @PostMapping
    @ApiOperation(value = "보호소 등록", notes = "보호소 등록")
    public ResponseEntity<Object> insertShelter(@RequestBody RequestParamShelterInsertDto requestParamShelterInsertDto){
        LOGGER.info("여긴와?");
        AuthCodeDto authCodeDto = service.insertShelter(requestParamShelterInsertDto);
        if(authCodeDto!=null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(authCodeDto);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 보호소 수정
     * @return
     */
    @PostMapping("/update")
    @ApiOperation(value = "보호소 수정", notes = "보호소 수정")
    public ResponseEntity<ResponseMessage> updateShelter(ShelterDto shelterDto, @RequestPart(value = "uploadFile",required = false) List<MultipartFile> uploadFile) throws Exception{
        ResponseMessage responseMessage = service.updateShelter(shelterDto, uploadFile);
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
     * 보호소 인증
     * @return
     */
    @GetMapping("/auth")
    @PreAuthorize("hasAnyRole('USER')")
    @ApiOperation(value = "보호소 인증", notes = "보호소 인증")
    public ResponseEntity<ResponseMessage> authShelter(@RequestParam("shelterId") Long shelterId, @RequestParam("authCode") String authCode){
        ResponseMessage responseMessage = service.AuthShelter(shelterId, authCode);
        if(responseMessage.getMessage()=="SUCCESS"){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage("SUCCESS"));
        }else{
            responseMessage.setMessage("FAIL");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(responseMessage);
        }
    }

    /**
     * 보호소 인증
     * @return
     */
    @GetMapping("/auth/code")
    @PreAuthorize("hasAnyRole('USER')")
    @ApiOperation(value = "보호소 인증", notes = "보호소 인증")
    public ResponseEntity<ResponseMessage> getAuthCode(@RequestParam("shelterId") Long shelterId){
        String authCode = service.getAuthCode(shelterId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMessage(authCode));

    }

    @DeleteMapping("/delete/{shelterId}")
    @ApiOperation(value = "보호소 인증", notes = "보호소 삭제")
    public ResponseEntity<ResponseMessage> deleteShelter(@PathVariable Long shelterId){
        service.deleteShelter(shelterId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMessage("SUCCESS"));
    }
    
}
