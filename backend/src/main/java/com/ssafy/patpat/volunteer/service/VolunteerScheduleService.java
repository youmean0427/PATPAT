package com.ssafy.patpat.volunteer.service;

import com.ssafy.patpat.common.code.Reservation;
import com.ssafy.patpat.shelter.dto.ShelterDto;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.shelter.repository.ShelterRepository;
import com.ssafy.patpat.shelter.service.ShelterService;
import com.ssafy.patpat.volunteer.dto.VolunteerScheduleDto;
import com.ssafy.patpat.volunteer.dto.VolunteerShelterDto;
import com.ssafy.patpat.volunteer.entity.VolunteerSchedule;
import com.ssafy.patpat.volunteer.mapping.VolunteerShelterIdMapping;
import com.ssafy.patpat.volunteer.repository.VolunteerScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VolunteerScheduleService{
    private final VolunteerScheduleRepository volunteerScheduleRepository;
    private final ShelterService shelterService;

    /** 개인이 구군 정보를 이용하여 전체 봉사일정을 볼 때
     * return : Shelter의 봉사 공고 정보
     * 상태 코드 대기중 (0)
     *
     * */

    @Transactional
    public List<VolunteerShelterDto> volunteerScheduleListInGugun(String gugunCode) {


        List<VolunteerShelterIdMapping> volunteerScheduleList = volunteerScheduleRepository.findShelter(0, gugunCode);

        if(volunteerScheduleList == null){
            return null;
        }
        List<VolunteerShelterDto> volunteerShelterDtoList = new ArrayList<>();
        for (VolunteerShelterIdMapping v:
             volunteerScheduleList) {
            volunteerShelterDtoList.add(VolunteerShelterDto.builder()
                    .name(v.getName())
                    .shelterId(v.getShelterId())
                    .volunteerDate(v.getVolunteerDate())
                    .build());
        }

        return volunteerShelterDtoList;
    }

    /** 개인이 구군 정보를 이용하여 전체 봉사일정을 클릭했을 때
     * return : 봉사 공고의 상세 정보들
     * */
    @Transactional
    public List<VolunteerScheduleDto> volunteerScheduleDetailList(String volunteerDate, int shelterId){
        List<VolunteerSchedule> list = volunteerScheduleRepository.findWithShelterByVolunteerDateAndShelterShelterIdOrderByStartTimeAsc(volunteerDate, shelterId);

        if (list == null){
            return null;
        }
        List<VolunteerScheduleDto> volunteerScheduleDtoList = new ArrayList<>();
        for (VolunteerSchedule vs:
             list) {
            volunteerScheduleDtoList.add(VolunteerScheduleDto.builder()
                    .volunteerId(vs.getVolunteerId())
                    .capacity(vs.getCapacity())
                    .totalCapacity(vs.getTotalCapacity())
                    .volunteerDate(vs.getVolunteerDate())
                    .shelterId(vs.getShelter().getShelterId())
                    .shelterName(vs.getShelter().getName())
                    .endTime(vs.getEndTime())
                    .guideLine(vs.getGuideLine())
                    .reservationStateCode(vs.getReservationStateCode().getCode())
                    .reservationState(vs.getReservationStateCode().name())
                    .startTime(vs.getStartTime())
                    .title(vs.getTitle())
                    .build());

        }
        return volunteerScheduleDtoList;
    }
}
