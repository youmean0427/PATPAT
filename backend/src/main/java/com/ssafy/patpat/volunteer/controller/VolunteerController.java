package com.ssafy.patpat.volunteer.controller;

import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.volunteer.dto.*;
import com.ssafy.patpat.volunteer.entity.VolunteerSchedule;
import com.ssafy.patpat.volunteer.service.VolunteerNoticeService;
import com.ssafy.patpat.volunteer.service.VolunteerScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/volunteers")
@Api(tags = {"07. Volunteer"},description = "봉사 관련 서비스")
public class VolunteerController {

    private final VolunteerScheduleService volunteerScheduleService;
    private final VolunteerNoticeService volunteerNoticeService;

    /**
     * 봉사 공고 조회(전체)
     * - 파라미터가 gugunCode을 포함하는 경우 구군으로 조회할 때
     * - 파라미터가 shelterId를 포함하는 경우 개인이 보호소 페이지에서 공고볼 때
     * @return
     */
    @GetMapping("/notices")
    @ApiOperation(value = "봉사 공고 조회", notes = "구군 봉사 공고를 조회(gugunCode)/개인의 보호소 페이지 공고 조회(shelterId)")
    public ResponseEntity<Object> selectNoticeList(RequestVolunteerDto requestVolunteerDto){
        //서비스 호출 코드
        List<VolunteerNoticeDto> list = volunteerNoticeService.selectNoticeList(requestVolunteerDto);
        if(list != null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(list);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }

    /**
     * 봉사 공고 조회 - 일별 클릭시
     * 해당 날짜 카드 클릭시 불러올 정보
     * @return
     */
    @GetMapping("/schedules")
    @ApiOperation(value = "일별 봉사 공고 조회", notes = "파라미터로 noticeId")
    public ResponseEntity<Object> selectScheduleList(@RequestParam("noticeId") Long noticeId){
        //서비스 호출 코드
        List<VolunteerScheduleDto> list = volunteerScheduleService.selectScheduleList(noticeId);
        if(list != null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(list);
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
