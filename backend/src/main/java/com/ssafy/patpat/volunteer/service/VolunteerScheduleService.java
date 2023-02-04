package com.ssafy.patpat.volunteer.service;

import com.ssafy.patpat.shelter.dto.ShelterDto;
import com.ssafy.patpat.volunteer.entity.VolunteerSchedule;

import java.util.List;

public interface VolunteerScheduleService {
    /** 개인이 전체 봉사일정을 볼 때 */
    List<VolunteerSchedule> volunteerScheduleListByGugun(String gugunCode);

    /**
     * 리턴 타입 : 쉘터 ID List
     * 파라미터 타입 : 쉘터 DtoList
     *
     * */
    List<ShelterDto> volunteerScheduleListInGugun(List<ShelterDto> list);

}
