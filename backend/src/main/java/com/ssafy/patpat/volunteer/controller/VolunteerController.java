package com.ssafy.patpat.volunteer.controller;

import com.ssafy.patpat.common.dto.ResponseListDto;
import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.common.error.ErrorDto;
import com.ssafy.patpat.common.error.VolunteerException;
import com.ssafy.patpat.shelter.dto.ShelterLocationDto;
import com.ssafy.patpat.volunteer.dto.*;
import com.ssafy.patpat.volunteer.entity.VolunteerSchedule;
import com.ssafy.patpat.volunteer.service.VolunteerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/volunteers")
@Api(tags = {"07. Volunteer"},description = "봉사 관련 서비스")
public class VolunteerController {

    private final VolunteerService volunteerService;

    /**
     * 봉사 공고 조회(보호소가 자기 봉사 공고 볼 경우)
     * @return
     */
    @GetMapping("/months")
    @ApiOperation(value = "봉사 공고 조회", notes = "보호소가 자기 봉사 공고 볼 경우 파라미터로 year, month, shelterId")
    public ResponseEntity<Object> selectNoticeListByMonth(VolunteerMonthDto volunteerMonthDto){
        //서비스 호출 코드
        List<VolunteerNoticeDto> list = volunteerService.selectNoticeListByMonth(volunteerMonthDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(list);
    }

    /**
     * 봉사 공고 조회(전체)
     * - 파라미터가 위도 경도로 조회할 때
     * @return
     */
    @GetMapping("/notices")
    @ApiOperation(value = "봉사 공고 조회", notes = "위도 경도로 조회")
    public ResponseEntity<Object> selectShelterListByLatLng(RequestVolunteerDto requestVolunteerDto){
        //서비스 호출 코드
        List<ShelterLocationDto> responseVolunteerDto = volunteerService.selectShelterListByLatLng(requestVolunteerDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseVolunteerDto);
    }

    /**
     * 특정 보호소 봉사 공고 리스트 조회
     * @return
     */
    @GetMapping("/notices/details/{shelterId}")
    @ApiOperation(value = "봉사 공고 상세 조회", notes = "클릭시 해당 보호소 공고 리스트 조회 shelterId 어짜피 최대 7개")
    public ResponseEntity<Object> detailNoticeByLatLng(@PathVariable Long shelterId){
        //서비스 호출 코드
        List<VolunteerNoticeDto> responseVolunteerDto = volunteerService.selectNoticeListByLatLng(shelterId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseVolunteerDto);
    }

    /**
     * 봉사 공고 등록
     * @return
     */
    @PostMapping("/notices")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "봉사 공고 등록", notes = "봉사 공고 등록")
    public ResponseEntity<Object> insertNotice(@RequestBody NoticeDto noticeDto){
        //서비스 호출 코드
        volunteerService.insertNotice(noticeDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMessage("SUCCESS"));
    }

    /**
     * 봉사 공고 수정
     * @return
     */
    @PutMapping("/notices")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "봉사 공고 수정", notes = "봉사 공고 수정")
    public ResponseEntity<Object> updateNotice(@RequestBody NoticeDto noticeDto){
        //서비스 호출 코드
        volunteerService.updateNotice(noticeDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMessage("SUCCESS"));

    }

    /**
     * 봉사 공고 삭제
     * @return
     */
    @DeleteMapping("/notices")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "봉사 공고 삭제", notes = "봉사 공고 삭제...상태코드로 한다했나..?")
    public ResponseEntity<Object> deleteNotice(@RequestParam("noticeId") Long noticeId){
        //서비스 호출 코드
        try {
            volunteerService.deleteNotice(noticeId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage("SUCCESS"));
        }catch (VolunteerException e){
            ErrorDto error = new ErrorDto(e.getMessage(),"012");
            return ResponseEntity.status(HttpStatus.OK)
                    .body(error);
        }



    }

    /**
     * 봉사 공고 상세 조회 - 일별 클릭시
     * 해당 날짜 카드 클릭시 불러올 정보
     * @return
     */
    @GetMapping("/schedules")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "일별 봉사 일정 조회", notes = "일별 상세 조회 - 파라미터로 noticeId offset limit")
    public ResponseEntity<Object> selectScheduleList(RequestVolunteerDto requestVolunteerDto){
        //서비스 호출 코드
        VolunteerNoticeDto volunteerScheduleDto = volunteerService.selectScheduleList(requestVolunteerDto);

        return ResponseEntity.status(HttpStatus.OK)
                    .body(volunteerScheduleDto);
    }

    @GetMapping("/schedules/{scheduleId}")
//    @PreAuthorize("hasAnyRole('USER')")
    @ApiOperation(value = "일별 봉사 일정 조회", notes = "일별 상세 조회 - 파라미터로 scheduleId")
    public ResponseEntity<Object> detailSchedule(@PathVariable Long scheduleId){
        //서비스 호출 코드
        VolunteerScheduleDto volunteerScheduleDto = volunteerService.detailSchedule(scheduleId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(volunteerScheduleDto);
    }

    /**
     * 봉사 공고 조회(전체)
     * - 파라미터가 shelterId를 포함하는 경우 개인이 보호소 페이지에서 공고볼 때
     * @return
     */
    @GetMapping("/schedules/shelter")
//    @PreAuthorize("hasAnyRole('USER')")
    @ApiOperation(value = "봉사 공고 조회", notes = "개인의 보호소 페이지 공고 조회(shelterId)")
    public ResponseEntity<Object> selectScheduleListByShelter(RequestVolunteerDto requestVolunteerDto){
        //서비스 호출 코드
        ResponseListDto responseVolunteerDto = volunteerService.selectScheduleListByShelter(requestVolunteerDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseVolunteerDto);
    }

    @PostMapping("/schedules")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "봉사 일정 추가", notes = "봉사 일정 추가/ noticeId, startTime, endTime, totalCapacity, guideLine")
    public ResponseEntity<Object> insertSchedule(@RequestBody ScheduleDto scheduleDto){
        try {
            volunteerService.insertSchedule(scheduleDto);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage("SUCCESS"));
        }catch (VolunteerException e){
            ErrorDto error = new ErrorDto(e.getMessage(), "009");
            return ResponseEntity.status(HttpStatus.OK).body(error);
        }
    }

    @PutMapping("/schedules")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "봉사 일정 수정", notes = "봉사 일정 수정 / scheduleId, startTime, endTime, totalCapacity, guideLine")
    public ResponseEntity<Object> updateSchedule(@RequestBody ScheduleDto volunteerScheduleDto){
        try {
            volunteerService.updateSchedule(volunteerScheduleDto);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage("SUCCESS"));
        }catch (VolunteerException e){
            ErrorDto error = new ErrorDto(e.getMessage(), "009");
            return ResponseEntity.status(HttpStatus.OK).body(error);
        }

    }

    @DeleteMapping("/schedules")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "봉사 일정 삭제", notes = "봉사 일정 삭제 - 파라미터로 scheduleId")
    public ResponseEntity<Object> deleteSchedule(@RequestParam("scheduleId") Long scheduleId) {
        //서비스 호출 코드
        try {
            volunteerService.deleteSchedule(scheduleId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage("SUCCESS"));
        }catch (VolunteerException e){
            ErrorDto errorDto = new ErrorDto(e.getMessage(), "012");
            return ResponseEntity.status(HttpStatus.OK)
                    .body(errorDto);
        }


    }

    /**
     * 봉사 지원서 조회(개인)
     * @return
     */
    @GetMapping("/reservations")
//    @PreAuthorize("hasAnyRole('USER')")
    @ApiOperation(value = "봉사 지원서 조회", notes = "개인이 지원한 봉사 지원서 조회 parameter: userId, limit, offset")
    public ResponseEntity<Object> selectReservationList(RequestVolunteerDto requestVolunteerDto){
        //서비스 호출 코드
        ResponseListDto responseVolunteerDto = volunteerService.selectReservationList(requestVolunteerDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseVolunteerDto);

    }

    /**
     * 봉사 지원서 가능 여부 조회
     * @return
     */
    @GetMapping("/reservations/check/{noticeId}")
//    @PreAuthorize("hasAnyRole('USER')")
    @ApiOperation(value = "봉사 지원서 조회", notes = "개인이 지원한 봉사 지원서 조회 pathValue : scheduleId")
    public ResponseEntity<Object> checkReservationPossible(@PathVariable Long noticeId){
        //서비스 호출 코드
//      responseVolunteerDto = volunteerService.checkReservationPossible(requestVolunteerDto);
        List<CheckVolunteerDto> checkVolunteerDtos = volunteerService.checkReservationPossible(noticeId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(checkVolunteerDtos);
    }

    /**
     * 봉사 지원서 조회(보호소) 이건 위에 스케쥴과 함께
     * @return
     *
    @GetMapping("/reservations")
    @ApiOperation(value = "봉사 지원서 조회", notes = "특정 보호소에 지원된 봉사 지원서 조회 parameter: shelterId, limit, offset")
    public ResponseEntity<Object> selectReservationListByShelter(RequestVolunteerDto requestVolunteerDto){
        //서비스 호출 코드
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ArrayList<ReservationDto>());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    */
    /**
     * 봉사 지원서 등록
     * @return
     */
    @PostMapping("/reservations")
//    @PreAuthorize("hasAnyRole('USER')")
    @ApiOperation(value = "봉사 지원서 등록", notes = "봉사 지원서 등록 로긘필수 json : scheduleId, capacity")
    public ResponseEntity<Object> insertReservation(@RequestBody ReservationDto reservationDto) {
        //서비스 호출 코드
        try {
            volunteerService.insertReservation(reservationDto);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage("SUCCESS"));
        }catch (VolunteerException e){
            ErrorDto error = new ErrorDto(e.getMessage(),"011");
            return ResponseEntity.status(HttpStatus.OK)
                    .body(error);
        }

    }
    /**
     * 봉사 지원서 수정
     * @return
     */
    @PutMapping("/reservations")
//    @PreAuthorize("hasAnyRole('USER')")
    @ApiOperation(value = "봉사 지원서 수정", notes = "봉사 지원서 수정 reservationId, userId, capacity")
    public ResponseEntity<Object> updateReservation(@RequestBody ReservationDto reservationDto) throws VolunteerException {
        //서비스 호출 코드
        volunteerService.updateReservation(reservationDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMessage("SUCCESS"));
    }

    @DeleteMapping("/reservations")
//    @PreAuthorize("hasAnyRole('USER')")
    @ApiOperation(value = "봉사 지원서 수정", notes = "봉사 지원서 수정 reservationId, userId")
    public ResponseEntity<Object> deleteReservation(@RequestBody ReservationDto reservationDto) throws VolunteerException {
        //서비스 호출 코드
        volunteerService.deleteReservation(reservationDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMessage("SUCCESS"));
    }

    /** 수락 및 거절 상태 변경 */
    @GetMapping("/reservations/state")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "수락 거절 상태", notes = "봉사 예약 수락 / 거절 인즈엉 파라미터 : reservationId, stateCode(수락 : 1, 거절 : 2)")
    public ResponseEntity<Object> changeReservationState(ReservationDto reservationDto) {
        //서비스 호출 코드
        try{
            VolunteerMessage responseMessage =volunteerService.changeReservationState(reservationDto);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseMessage);
        }catch (VolunteerException e){
            ErrorDto error = new ErrorDto(e.getMessage(), "010");
            return ResponseEntity.status(HttpStatus.OK)
                    .body(error);
        }

    }

    /** 완료 변경 */
    @GetMapping("/reservations/complete")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "완료", notes = "봉사 완료 파라미터 : reservationId 무적권 완료")
    public ResponseEntity<Object> completeReservationState(ReservationDto reservationDto) throws VolunteerException {
        //서비스 호출 코드
        volunteerService.completeReservationState(reservationDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMessage("SUCCESS"));
    }
}
