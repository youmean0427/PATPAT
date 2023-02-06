package com.ssafy.patpat.volunteer.service;

import com.ssafy.patpat.common.code.Reservation;
import com.ssafy.patpat.volunteer.dto.RequestVolunteerDto;
import com.ssafy.patpat.volunteer.dto.VolunteerNoticeDto;
import com.ssafy.patpat.volunteer.dto.VolunteerScheduleDto;
import com.ssafy.patpat.volunteer.entity.VolunteerNotice;
import com.ssafy.patpat.volunteer.repository.VolunteerNoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VolunteerNoticeService {
    private final VolunteerNoticeRepository volunteerNoticeRepository;

    /**
     * 봉사 공고 조회(전체)
     * 봉사 찾기로 구군으로 탐색 했을 경우
     * 개인이 보호소 페이지에서 보는 경우
     * @return
     */
    @Transactional
    public List<VolunteerNoticeDto> selectNoticeList(RequestVolunteerDto requestVolunteerDto){
        PageRequest pageRequest = PageRequest.of(requestVolunteerDto.getOffset(),requestVolunteerDto.getLimit(), Sort.by("volunteerDate").ascending());
        List<VolunteerNotice> volunteerNotices;
        if(requestVolunteerDto.getGugunCode() != null){
            volunteerNotices = volunteerNoticeRepository.findWithShelterByShelterGugunCodeAndReservationStateCodeAndVolunteerDateGreaterThan(requestVolunteerDto.getGugunCode(), Reservation.대기중, LocalDate.now().toString(), pageRequest);
        }else if(requestVolunteerDto.getShelterId() != null){
            volunteerNotices = volunteerNoticeRepository.findWithShelterByShelterShelterIdAndReservationStateCodeAndVolunteerDateGreaterThan(requestVolunteerDto.getShelterId(), Reservation.대기중, LocalDate.now().toString(), pageRequest);
        }else{
            return null;
        }
        if(volunteerNotices.isEmpty()){
            return null;
        }
        List<VolunteerNoticeDto> list = new ArrayList<>();
        for (VolunteerNotice vn:
             volunteerNotices) {
            list.add(VolunteerNoticeDto.builder()
                    .name(vn.getShelter().getName())
                    .noticeId(vn.getNoticeId())
                    .shelterId(vn.getShelter().getShelterId())
                    .state(vn.getReservationStateCode().name())
                    .stateCode(vn.getReservationStateCode().getCode())
                    .title(vn.getTitle())
                    .volunteerDate(vn.getVolunteerDate())
                    .build());
        }
        return list;
    }




}
