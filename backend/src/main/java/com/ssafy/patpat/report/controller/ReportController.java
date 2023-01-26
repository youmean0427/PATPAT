package com.ssafy.patpat.report.controller;

import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.protect.dto.ProtectDto;
import com.ssafy.patpat.report.dto.ReportDto;
import com.ssafy.patpat.report.dto.RequestReportDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("report")
public class ReportController {
    /**
     * 실종견 리스트
     * @return
     */
    @GetMapping("/missing")
    public ResponseEntity<Object> selectMissingList(RequestReportDto requestReportDto){
        //서비스 호출 코드
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ArrayList<ReportDto>());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 임보견 리스트
     * @return
     */
    @GetMapping("/personal")
    public ResponseEntity<Object> selectPersonalProtectionList(RequestReportDto requestReportDto){
        //서비스 호출 코드
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ArrayList<ReportDto>());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 실종견 상세
     * @return
     */
    @GetMapping("/missing/{missingId}")
    public ResponseEntity<Object> detailMissing(@PathVariable int missingId){
        //서비스 호출 코드
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ReportDto());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 임보견 상세
     * @return
     */
    @GetMapping("/protect/{personalProtectId}")
    public ResponseEntity<Object> detailPersonalProtection(@PathVariable int personalProtectId){
        //서비스 호출 코드
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ReportDto());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }

    /**
     * 내가 잃어버린 강아지로 의심되는 보호견 리스트
     * @return
     */
    @GetMapping("/recommend")
    public ResponseEntity<Object> selectRecommendList(RequestReportDto requestReportDto){
        //서비스 호출 코드
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ArrayList<ProtectDto>());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 내가 잃어버린 강아지로 의심되는 보호견 갯수
     * @return
     */
    @GetMapping("/recommend/count")
    public ResponseEntity<Object> selectRecommendCount(RequestReportDto requestReportDto){
        //서비스 호출 코드
        HashMap<String,Integer> map = new HashMap<>();
        map.put("count",5);
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(map);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }

    /**
     * 실종,임보 등록
     * @return
     */
    @PostMapping
    public ResponseEntity<Object> insertReport(ReportDto reportDto, MultipartFile[] uploadFile){
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
     * 실종,임보 수정
     * @return
     */
    @PostMapping("/update")
    public ResponseEntity<Object> updateReport(ReportDto reportDto, MultipartFile[] uploadFile){
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
