package com.ssafy.patpat.volunteer.controller;

import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.volunteer.dto.NoticeDto;
import com.ssafy.patpat.volunteer.dto.RequestVolunteerDto;
import com.ssafy.patpat.volunteer.dto.ReservationDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/volunteer")
public class VolunteerController {
    /**
     * 봉사 공고 조회(전체)
     * @return
     */
    @GetMapping("/notice")
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
    public ResponseEntity<Object> insertNotice(NoticeDto dto){
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
    public ResponseEntity<Object> updateNotice(NoticeDto dto){
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
    @PostMapping("/reservation/{reservationId}")
    public ResponseEntity<Object> updateReservation(@PathVariable int reservationId, RequestVolunteerDto dto){
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
