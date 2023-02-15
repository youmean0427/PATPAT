package com.ssafy.patpat.report.controller;

import com.ssafy.patpat.common.dto.ResponseListDto;
import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.protect.dto.ProtectDto;
import com.ssafy.patpat.report.dto.ReportDto;
import com.ssafy.patpat.report.dto.RequestReportDto;
import com.ssafy.patpat.report.service.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/reports")
@Api(tags = {"04. Report"}, description = "실종,임보 관련 서비스")
public class ReportController {
    @Autowired
    ReportService service;
    /**
     * 실종견 리스트
     * @return
     */
    @GetMapping("/missings")
    @ApiOperation(value = "실종견 조회", notes = "gender, breedId 넣는것에 따라 무조건 필수로 주세요")
    public ResponseEntity<Object> selectMissingList(RequestReportDto requestReportDto){
        //서비스 호출 코드
//        System.out.println(requestReportDto);
        ResponseListDto reportDtoList = service.selectMissingList(requestReportDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(reportDtoList);
    }
    /**
     * 실종견 리스트(현재 유저의 실종견 공고 리스트)
     * @return
     */
    @GetMapping("/missings/me")
//    @PreAuthorize("hasAnyRole('USER')")
    @ApiOperation(value = "실종견 조회", notes = "{현재 유저의 실종견 공고 리스트}")
    public ResponseEntity<Object> selectMissingListByUser(RequestReportDto requestReportDto){
        //서비스 호출 코드

        ResponseListDto reportDtoList = service.selectMissingListByUser(requestReportDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(reportDtoList);
    }
    /**
     * 임보견 리스트
     * @return
     */
    @GetMapping("/personals")
    @ApiOperation(value = "임보견 조회", notes = "{code==0 전체조회, code==1 성별 견종 필터링}")
    public ResponseEntity<Object> selectPersonalProtectionList(RequestReportDto requestReportDto){
        //서비스 호출 코드
        ResponseListDto reportDtoList = service.selectPersonalProtectionList(requestReportDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(reportDtoList);
    }
    /**
     * 실종견 상세
     * @return
     */
    @GetMapping("/missings/detail/{missingId}")
    @ApiOperation(value = "실종견 상세", notes = "실종견 상세")
    public ResponseEntity<Object> detailMissing(@PathVariable Long missingId){
        //서비스 호출 코드

        ReportDto reportDto = service.detailMissing(missingId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(reportDto);
    }
    /**
     * 임보견 상세
     * @return
     */
    @GetMapping("/personals/{personalProtectId}")
    @ApiOperation(value = "임보견 상세", notes = "임보견 상세")
    public ResponseEntity<Object> detailPersonalProtection(@PathVariable Long personalProtectId){
        //서비스 호출 코드
        ReportDto reportDto = service.detailPersonalProtection(personalProtectId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(reportDto);
    }

    /**
     * 내가 잃어버린 강아지로 의심되는 보호견 리스트
     * @return
     */
    @GetMapping("/recommends/{missingId}")
    @ApiOperation(value = "유사견종 조회", notes = "실종된 견종과 유사한 견종 조회")
//    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<Object> selectRecommendList(@PathVariable Long missingId,RequestReportDto requestReportDto){
        //서비스 호출 코드
        ResponseListDto responseListDto = service.selectRecommendList(missingId,requestReportDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseListDto);
    }

    /**
     * 실종,임보 등록
     * @return
     */
    @PostMapping
    @ApiOperation(value = "실종,임보 등록", notes = "0==실종, 1==임보")
//    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<ResponseMessage> insertReport(ReportDto reportDto, @RequestPart(required = false) List<MultipartFile> uploadFile){
        //서비스 호출 코드
        System.out.println(reportDto);
        ResponseMessage responseMessage = service.insertReport(reportDto, uploadFile);
        if(responseMessage.getMessage().equals("SUCCESS")){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseMessage);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 실종,임보 수정
     * @return
     */
    @PostMapping("/updates")
    @ApiOperation(value = "실종,임보 수정", notes = "0==실종, 1==임보")
//    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<ResponseMessage> updateReport(ReportDto reportDto, @RequestPart(required = false) List<MultipartFile> uploadFile) throws Exception{
        //서비스 호출 코드
        ResponseMessage responseMessage = service.updateReport(reportDto, uploadFile);
        if(responseMessage.getMessage().equals("SUCCESS")){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseMessage);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }

//    @DeleteMapping("/happy")
//    @ApiOperation(value = "행복 버튼", notes = "모든 report 데이터를 날린다.")
//    public ResponseEntity<Object> deleteAll(){
//        //서비스 호출 코드
////        System.out.println(requestReportDto);
//        service.deleteAll();
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(new ResponseMessage("HAPPY"));
//    }

}
