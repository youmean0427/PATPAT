package com.ssafy.patpat.consulting.controller;

import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.consulting.dto.ConsultingDto;
import com.ssafy.patpat.consulting.dto.RequestConsultingDto;
import com.ssafy.patpat.consulting.entity.Consulting;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("consulting")
@Api(tags = {"02. Consulting"},description = "상담 관련 서비스")
public class ConsultingController {
    /**
     * 내가 예약한 상담을 조회하는 메서드
     * @param
     * @return
     */
    @GetMapping()
    @ApiOperation(value = "상담 조회", notes = "내가 예약한 상담을 조회한다.")
    public ResponseEntity<ArrayList<ConsultingDto>> selectConsultingList(RequestConsultingDto requestConsultingDto){
        //service 호출
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ArrayList<ConsultingDto>());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ArrayList<ConsultingDto>());
        }
    }
    /**
     * 보호소 입장에서 예약된 상담 리스트
     * @param
     * @return
     */
    @GetMapping("/shelter")
    @ApiOperation(value = "상담 조회", notes = "해당 보호소에서 예약된 상담을 조회한다.")
    public ResponseEntity<ArrayList<ConsultingDto>> selectConsultingListByShelter(RequestConsultingDto requestConsultingDto){
        //service 호출
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ArrayList<ConsultingDto>());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ArrayList<ConsultingDto>());
        }
    }
    /**
     * 상담 등록하기
     * @param
     * @return
     */
    @PostMapping()
    @ApiOperation(value = "상담 등록", notes = "상담을 등록한다.")
    public ResponseEntity<ResponseMessage> insertConsulting(@RequestBody ConsultingDto consultingDto){
        //service 호출
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage("SUCCESS"));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 상담 수정
     * @param
     * @return
     */
    @PutMapping("/{consultingId}")
    @ApiOperation(value = "상담 수정", notes = "상담을 수정한다.")
    public ResponseEntity<ResponseMessage> updateConsulting(@PathVariable int consultingId, @RequestBody ConsultingDto consultingDto){
        //service 호출
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage("SUCCESS"));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
}
