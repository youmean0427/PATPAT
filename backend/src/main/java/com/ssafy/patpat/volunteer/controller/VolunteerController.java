package com.ssafy.patpat.volunteer.controller;

import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.volunteer.dto.NoticeDto;
import com.ssafy.patpat.volunteer.dto.RequestVolunteerDto;
import com.ssafy.patpat.volunteer.dto.ReservationDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/volunteer")
@Api(tags = {"07. Volunteer"},description = "봉사 관련 서비스")
public class VolunteerController {
    /**
     * 봉사 공고 조회(전체)
     * @return
     */
    @GetMapping("/notice")
    @ApiOperation(value = "봉사 공고 조회", notes = "전체 봉사 공고를 조회")
    public ResponseEntity<Object> selectNoticeList(RequestVolunteerDto dto){
        //서비스 호출 코드
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ArrayList<NoticeDto>());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 봉사 공고 조회(특정 보호소 내부의 봉사 리스트)
     * @return
     */
    @GetMapping("/notice/{shelterId}")
    @ApiOperation(value = "봉사 공고 조회", notes = "특정 보호소 내부의 봉사 리스트 조회")
    public ResponseEntity<Object> selectNoticeListByShelter(@PathVariable int shelterId){
        //서비스 호출 코드
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ArrayList<NoticeDto>());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 봉사 공고 상세 조회
     * @return
     */
    @GetMapping("/notice/detail/{noticeId}")
    @ApiOperation(value = "봉사 공고 상세 조회", notes = "봉사 공고 상세 조회")
    public ResponseEntity<Object> detailNotice(@PathVariable int noticeId){
        //서비스 호출 코드
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new NoticeDto());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 봉사 공고 등록
     * @return
     */
    @PostMapping("/notice")
    @ApiOperation(value = "봉사 공고 등록", notes = "봉사 공고 등록")
    public ResponseEntity<Object> insertNotice(@RequestBody NoticeDto dto){
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
     * 봉사 공고 수정
     * @return
     */
    @PutMapping("/notice")
    @ApiOperation(value = "봉사 공고 수정", notes = "봉사 공고 수정")
    public ResponseEntity<Object> updateNotice(@RequestBody NoticeDto dto){
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
     * 봉사 지원서 조회(개인)
     * @return
     */
    @GetMapping("/reservation")
    @ApiOperation(value = "봉사 지원서 조회", notes = "개인이 지원한 봉사 지원서 조회")
    public ResponseEntity<Object> selectReservationList(RequestVolunteerDto dto){
        //서비스 호출 코드
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ArrayList<ReservationDto>());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 봉사 지원서 조회(보호소)
     * @return
     */
    @GetMapping("/reservation/shelter")
    @ApiOperation(value = "봉사 지원서 조회", notes = "특정 보호소에 지원된 봉사 지원서 조회")
    public ResponseEntity<Object> selectReservationListByShelter(RequestVolunteerDto dto){
        //서비스 호출 코드
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ArrayList<ReservationDto>());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 봉사 지원서 등록
     * @return
     */
    @PostMapping("/reservation")
    @ApiOperation(value = "봉사 지원서 등록", notes = "봉사 지원서 등록")
    public ResponseEntity<Object> insertReservation(RequestVolunteerDto dto){
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
     * 봉사 지원서 수정
     * @return
     */
    @PutMapping("/reservation")
    @ApiOperation(value = "봉사 지원서 수정", notes = "봉사 지원서 수정")
    public ResponseEntity<Object> updateReservation(RequestVolunteerDto dto){
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
