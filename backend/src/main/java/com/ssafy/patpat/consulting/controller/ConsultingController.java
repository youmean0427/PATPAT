package com.ssafy.patpat.consulting.controller;

import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.consulting.dto.ConsultingDto;
import com.ssafy.patpat.consulting.dto.RequestConsultingDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("consulting")
public class ConsultingController {
    /**
     * 내가 예약한 상담을 조회하는 메서드
     * @param
     * @return
     */
    @GetMapping()
    public ResponseEntity<Object> selectConsultingList(RequestConsultingDto requestConsultingDto){
        //service 호출
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ArrayList<ConsultingDto>());
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
    @GetMapping("/shelter")
    public ResponseEntity<Object> selectConsultingListByShelter(RequestConsultingDto requestConsultingDto){
        //service 호출
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ArrayList<ConsultingDto>());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 상담 등록하기
     * @param
     * @return
     */
    @PostMapping()
    public ResponseEntity<Object> insertConsulting(RequestConsultingDto requestConsultingDto){
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
    public ResponseEntity<Object> updateConsulting(@PathVariable int consultingId){
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
