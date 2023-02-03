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
@RequestMapping("api/volunteers")
@Api(tags = {"07. Volunteer"},description = "봉사 관련 서비스")
public class VolunteerController {
    /**
     * 봉사 공고 조회(전체)
     * @return
     */
    @GetMapping("/notices")
    @ApiOperation(value = "봉사 공고 조회", notes = "전체 봉사 공고를 조회")
    public ResponseEntity<Object> selectNoticeList(RequestVolunteerDto requestVolunteerDto){
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
     * 봉사 공고 조회(일반 유저가 보호소에 들어간 경우 (카드형식))
     * @return
     */
    @GetMapping("/notices/shelters")
    @ApiOperation(value = "봉사 공고 조회", notes = "일반 유저가 보호소에 들어간 경우 (카드형식)")
    public ResponseEntity<Object> selectNoticeListBySUser(@PathVariable int shelterId){
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
     * 봉사 공고 조회(보호소가 자기 봉사 공고 볼 경우)
     * @return
     */
    @GetMapping("/months")
    @ApiOperation(value = "봉사 공고 조회", notes = "보호소가 자기 봉사 공고 볼 경우")
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
    @GetMapping("/notices/detail/{noticeId}")
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
    @PostMapping("/notices")
    @ApiOperation(value = "봉사 공고 등록", notes = "봉사 공고 등록")
    public ResponseEntity<Object> insertNotice(@RequestBody NoticeDto noticeDto){
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
    @PutMapping("/notices")
    @ApiOperation(value = "봉사 공고 수정", notes = "봉사 공고 수정")
    public ResponseEntity<Object> updateNotice(@RequestBody NoticeDto noticeDto){
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
    @GetMapping("/reservations/users")
    @ApiOperation(value = "봉사 지원서 조회", notes = "개인이 지원한 봉사 지원서 조회")
    public ResponseEntity<Object> selectReservationList(@RequestParam int userId){
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
    @GetMapping("/reservations")
    @ApiOperation(value = "봉사 지원서 조회", notes = "특정 보호소에 지원된 봉사 지원서 조회")
    public ResponseEntity<Object> selectReservationListByShelter(@RequestParam int shelterId){
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
    @PostMapping("/reservations")
    @ApiOperation(value = "봉사 지원서 등록", notes = "봉사 지원서 등록")
    public ResponseEntity<Object> insertReservation(@RequestBody ReservationDto reservationDto){
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
    @PutMapping("/reservations")
    @ApiOperation(value = "봉사 지원서 수정", notes = "봉사 지원서 수정")
    public ResponseEntity<Object> updateReservation(@RequestBody ReservationDto reservationDto){
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
