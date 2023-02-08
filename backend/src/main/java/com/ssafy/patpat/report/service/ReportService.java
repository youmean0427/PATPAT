package com.ssafy.patpat.report.service;

import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.protect.dto.ProtectDto;
import com.ssafy.patpat.report.dto.ReportDto;
import com.ssafy.patpat.report.dto.RequestReportDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

public interface ReportService {
    List<ReportDto> selectMissingList(RequestReportDto requestReportDto);
    List<ReportDto> selectMissingListByUser(int userId, RequestReportDto requestReportDto);
    List<ReportDto> selectPersonalProtectionList(RequestReportDto requestReportDto);
    ReportDto detailMissing(int missingId);
    ReportDto detailPersonalProtection(int personalProtectId);
    ResponseMessage updateReport(ReportDto reportDto, List<MultipartFile> uploadFile);
    ResponseMessage insertReport(ReportDto reportDto, List<MultipartFile> uploadFile);
    List<ProtectDto> selectRecommendList(RequestReportDto requestReportDto);
    HashMap<String, Integer> selectRecommendCount(RequestReportDto requestReportDto);
}
