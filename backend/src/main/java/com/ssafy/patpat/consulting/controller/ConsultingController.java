package com.ssafy.patpat.consulting.controller;

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
@RequestMapping("consultations")
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
        List<ConsultingDto> consultingDtoList = service.selectConsultingList(requestConsultingDto);
        if(consultingDtoList!=null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(consultingDtoList);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
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
        List<ConsultingDto> consultingDtoList = service.selectConsultingListByShelter(requestConsultingDto);
        if(consultingDtoList!=null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(consultingDtoList);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 상담 등록시 예약가능 한 시간 리스트
     * @param
     * @return
     */
    @GetMapping("/shelters/{shelterId}")
    @ApiOperation(value = "예약 가능한 시간 리스트", notes = "예약 가능한 시간 리스트")
    public ResponseEntity<Object> selectTimeList(@PathVariable int shelterId,
                                                 @RequestParam("date")
                                                @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
        //service 호출
        System.out.println(date);
        List<TimeDto> timeDtoList = service.selectTimeList(shelterId,date);

        if(timeDtoList!=null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(timeDtoList);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("예약가능한 날짜가 없거나 요청이 잘못됨"));
        }

    }
    /**
     * 유저, 보호소가 방생성, 혹은 참가를 클릭한 경우
     * @param
     * @return
     */
    @GetMapping("/rooms")
    @ApiOperation(value = "상담 방 생성코드 및 유저정보 주기", notes = "방 생성 , 참가")
    public ResponseEntity<Object> selectTimeList(@RequestParam int shelterId, @RequestParam int consultingId){
        //service 호출
        RoomDto roomDto = service.selectRoomDto(shelterId,consultingId);

        if(roomDto!=null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(roomDto);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("방 생성 실패"));
        }
    }
    /**
     * 방 이 종료되고 상태가 완료로 변경됨
     * @param
     * @return
     */
    @PutMapping("/rooms")
    @ApiOperation(value = "방생성 끝나고 상태 변경", notes = "완료상태로변경")
    public ResponseEntity<Object> updateConsulting(@RequestParam int consultingId){
        //service 호출
        Consulting consulting = service.exitRoom(consultingId);
        ResponseMessage responseMessage = new ResponseMessage("SUCCESS");

        if(consulting!=null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseMessage);
        }else{
            responseMessage.setMessage("FAIL");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage(""));
        }
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
        if(responseMessage!=null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseMessage);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(responseMessage);
        }
    }

    /**
     * 상담 수정
     * @param
     * @return
     */
    @PutMapping("/{consultingId}")
    @ApiOperation(value = "상담 수정", notes = "상담을 수정한다.")
    public ResponseEntity<Object> updateConsulting(@PathVariable int consultingId, @RequestBody ConsultingDto consultingDto){
        //service 호출
        ResponseMessage responseMessage = service.updateConsulting(consultingId,consultingDto);
        if(responseMessage!=null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseMessage);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(responseMessage);
        }
    }
}
