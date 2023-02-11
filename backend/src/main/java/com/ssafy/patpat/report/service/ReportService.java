package com.ssafy.patpat.report.service;

import com.ssafy.patpat.common.dto.ResponseListDto;
import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.protect.dto.ProtectDto;
import com.ssafy.patpat.report.dto.ReportDto;
import com.ssafy.patpat.report.dto.RequestReportDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

public interface ReportService {
    ResponseListDto selectMissingList(RequestReportDto requestReportDto);
    ResponseListDto selectMissingListByUser(RequestReportDto requestReportDto);
    ResponseListDto selectPersonalProtectionList(RequestReportDto requestReportDto);
    ReportDto detailMissing(Long missingId);
    ReportDto detailPersonalProtection(Long personalProtectId);
    ResponseMessage updateReport(ReportDto reportDto, List<MultipartFile> uploadFile) throws Exception;
    ResponseMessage insertReport(ReportDto reportDto, List<MultipartFile> uploadFile);
    List<ProtectDto> selectRecommendList(RequestReportDto requestReportDto);
    HashMap<String, Integer> selectRecommendCount(RequestReportDto requestReportDto);
}
