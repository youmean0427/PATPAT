package com.ssafy.patpat.report.service;

import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.protect.dto.ProtectDto;
import com.ssafy.patpat.report.dto.ReportDto;
import com.ssafy.patpat.report.dto.RequestReportDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
@Service
public class ReportServiceImpl implements ReportService{
    @Override
    public List<ReportDto> selectMissingList(RequestReportDto requestReportDto) {
//        int gender = requestReportDto.getGender();
//        int breedId = requestReportDto.getBreedId();
//
        System.out.println(requestReportDto);
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

    @Override
    public ResponseMessage updateReport(ReportDto reportDto, List<MultipartFile> uploadFile) {
        return null;
    }

    @Override
    public ResponseMessage insertReport(ReportDto reportDto, List<MultipartFile> uploadFile) {
        return null;
    }

    @Override
    public List<ProtectDto> selectRecommendList(RequestReportDto requestReportDto) {
        return null;
    }

    @Override
    public HashMap<String, Integer> selectRecommendCount(RequestReportDto requestReportDto) {
        return null;
    }
}
