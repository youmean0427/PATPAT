package com.ssafy.patpat.report.controller;

import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.protect.dto.ProtectDto;
import com.ssafy.patpat.report.dto.ReportDto;
import com.ssafy.patpat.report.dto.RequestReportDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("report")
@Api(tags = {"04. Report"}, description = "실종,임보 관련 서비스")
public class ReportController {
    /**
     * 실종견 리스트
     * @return
     */
    @GetMapping("/missing")
    @ApiOperation(value = "실종견 조회", notes = "{code==0 전체 실종견 , code==1 해당 유저의 실종공고 리스트 , code==2 견종 성별 필터링 검색}")
    public ResponseEntity<ArrayList<ReportDto>> selectMissingList(RequestReportDto requestReportDto){
        //서비스 호출 코드
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ArrayList<ReportDto>());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ArrayList<ReportDto>());
        }
    }
    /**
     * 임보견 리스트
     * @return
     */
    @GetMapping("/personal")
    @ApiOperation(value = "임보견 조회", notes = "{code==0 전체조회, code==1 성별 견종 필터링}")
    public ResponseEntity<ArrayList<ReportDto>> selectPersonalProtectionList(RequestReportDto requestReportDto){
        //서비스 호출 코드
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ArrayList<ReportDto>());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ArrayList<ReportDto>());
        }
    }
    /**
     * 실종견 상세
     * @return
     */
    @GetMapping("/missing/{missingId}")
    @ApiOperation(value = "실종견 상세", notes = "실종견 상세")
    public ResponseEntity<ReportDto> detailMissing(@PathVariable int missingId){
        //서비스 호출 코드
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ReportDto());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ReportDto());
        }
    }
    /**
     * 임보견 상세
     * @return
     */
    @GetMapping("/protect/{personalProtectId}")
    @ApiOperation(value = "임보견 상세", notes = "임보견 상세")
    public ResponseEntity<ReportDto> detailPersonalProtection(@PathVariable int personalProtectId){
        //서비스 호출 코드
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ReportDto());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ReportDto());
        }
    }

    /**
     * 내가 잃어버린 강아지로 의심되는 보호견 리스트
     * @return
     */
    @GetMapping("/recommend")
    @ApiOperation(value = "유사견종 조회", notes = "실종된 견종과 유사한 견종 조회")
    public ResponseEntity<ArrayList<ProtectDto>> selectRecommendList(RequestReportDto requestReportDto){
        //서비스 호출 코드
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ArrayList<ProtectDto>());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ArrayList<ProtectDto>());
        }
    }
    /**
     * 내가 잃어버린 강아지로 의심되는 보호견 갯수
     * @return
     */
    @GetMapping("/recommend/count")
    @ApiOperation(value = "유사견종 마리수 조회", notes = "유사견종 갯수 조회")
    public ResponseEntity<HashMap<String,Integer>> selectRecommendCount(RequestReportDto requestReportDto){
        //서비스 호출 코드
        HashMap<String,Integer> map = new HashMap<>();
        map.put("count",5);
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new HashMap<String,Integer>());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new HashMap<String,Integer>());
        }
    }

    /**
     * 실종,임보 등록
     * @return
     */
    @PostMapping
    @ApiOperation(value = "실종,임보 등록", notes = "0==실종, 1==임보")
    public ResponseEntity<ResponseMessage> insertReport(ReportDto reportDto, MultipartFile[] uploadFile){
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
    @ApiOperation(value = "실종,임보 수정", notes = "0==실종, 1==임보")
    public ResponseEntity<ResponseMessage> updateReport(ReportDto reportDto, MultipartFile[] uploadFile){
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
