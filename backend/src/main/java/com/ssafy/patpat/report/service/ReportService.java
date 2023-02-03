package com.ssafy.patpat.report.service;

import com.ssafy.patpat.report.dto.ReportDto;
import com.ssafy.patpat.report.dto.RequestReportDto;

import java.util.List;

public interface ReportService {
    List<ReportDto> selectMissingList(RequestReportDto requestReportDto);

    List<ReportDto> selectMissingListByUser(int userId, RequestReportDto requestReportDto);


    List<ReportDto> selectPersonalProtectionList(RequestReportDto requestReportDto);

    ReportDto detailMissing(int missingId);

    ReportDto detailPersonalProtection(int personalProtectId);
}
