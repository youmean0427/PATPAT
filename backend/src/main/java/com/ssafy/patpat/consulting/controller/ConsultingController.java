package com.ssafy.patpat.consulting.controller;

import com.ssafy.patpat.common.dto.ResponseListDto;
import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.consulting.dto.ConsultingDto;
import com.ssafy.patpat.consulting.dto.RequestConsultingDto;
import com.ssafy.patpat.consulting.dto.RoomDto;
import com.ssafy.patpat.consulting.dto.TimeDto;
import com.ssafy.patpat.consulting.entity.Consulting;
import com.ssafy.patpat.consulting.entity.Time;
import com.ssafy.patpat.consulting.service.ConsultingService;
import com.ssafy.patpat.shelter.entity.Shelter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/consultations")
@Api(tags = {"02. Consulting"},description = "상담 관련 서비스")
public class ConsultingController {
    @Autowired
    ConsultingService service;
    /**
     * 내가 예약한 상담을 조회하는 메서드
     * @param
     * @return
     */
    @GetMapping()
    @ApiOperation(value = "상담 조회", notes = "내가 예약한 상담을 조회한다.")
    public ResponseEntity<Object> selectConsultingList(RequestConsultingDto requestConsultingDto){
        //service 호출
//        System.out.println(requestConsultingDto);
        ResponseListDto consultingDtoList = service.selectConsultingList(requestConsultingDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(consultingDtoList);
    }
    /**
     * 보호소 입장에서 예약된 상담 리스트
     * @param
     * @return
     */
    @GetMapping("/shelters")
    @ApiOperation(value = "상담 조회", notes = "해당 보호소에서 예약된 상담을 조회한다.")
    public ResponseEntity<Object> selectConsultingListByShelter(RequestConsultingDto requestConsultingDto){
        //service 호출
        ResponseListDto consultingDtoList = service.selectConsultingListByShelter(requestConsultingDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(consultingDtoList);
    }
    /**
     * 상담 등록시 예약가능 한 시간 리스트
     * @param
     * @return
     */
    @GetMapping("/shelters/{shelterId}")
    @ApiOperation(value = "예약 가능한 시간 리스트", notes = "예약 가능한 시간 리스트")
    public ResponseEntity<Object> selectTimeList(@PathVariable Long shelterId,
                                                 @RequestParam("date")
                                                @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
        //service 호출
//        System.out.println(date);
        List<TimeDto> timeDtoList = service.selectTimeList(shelterId,date);
        return ResponseEntity.status(HttpStatus.OK)
                .body(timeDtoList);


    }
    /**
     * 유저, 보호소가 방생성, 혹은 참가를 클릭한 경우
     * @param
     * @return
     */
    @GetMapping("/rooms")
    @ApiOperation(value = "상담 방 생성코드 및 유저정보 주기", notes = "방 생성 , 참가")
    public ResponseEntity<Object> selectTimeList(@RequestParam Long shelterId, @RequestParam Long consultingId){
        //service 호출
        RoomDto roomDto = service.selectRoomDto(shelterId,consultingId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(roomDto);

    }
    /**
     * 상담 등록하기
     * @param
     * @return
     */
    @PostMapping()
    @ApiOperation(value = "상담 등록", notes = "상담을 등록한다.")
    public ResponseEntity<Object> insertConsulting(@RequestBody ConsultingDto consultingDto){
        //service 호출
        ResponseMessage responseMessage = service.insertConsulting(consultingDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseMessage);
    }

    /**
     * 상담 수정
     * @param
     * @return
     */
    @PutMapping("/{consultingId}")
    @ApiOperation(value = "상담 수정", notes = "상담을 수정한다.")
    public ResponseEntity<Object> updateConsulting(@PathVariable Long consultingId, @RequestBody ConsultingDto consultingDto){
        //service 호출
        ResponseMessage responseMessage = service.updateConsulting(consultingId,consultingDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseMessage);
    }

}
