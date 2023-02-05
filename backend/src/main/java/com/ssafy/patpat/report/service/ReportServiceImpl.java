package com.ssafy.patpat.report.service;

import com.ssafy.patpat.report.dto.ReportDto;
import com.ssafy.patpat.report.dto.RequestReportDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReportServiceImpl implements ReportService{

    @Override
    public List<ReportDto> selectMissingList(RequestReportDto requestReportDto) {
        return null;
    }

    @Override
    public List<ReportDto> selectMissingListByUser(int userId, RequestReportDto requestReportDto) {
        return null;
    }

    @Override
    public List<ReportDto> selectPersonalProtectionList(RequestReportDto requestReportDto) {
        return null;
    }

    @Override
    public ReportDto detailMissing(int missingId) {
        return null;
    }

    @Override
    public ReportDto detailPersonalProtection(int personalProtectId) {
        return null;
    }
}
