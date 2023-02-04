package com.ssafy.patpat.volunteer.service;

import com.ssafy.patpat.shelter.dto.ShelterDto;
import com.ssafy.patpat.volunteer.entity.VolunteerSchedule;
import com.ssafy.patpat.volunteer.repository.VolunteerScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VolunteerScheduleServiceImpl implements VolunteerScheduleService{
    private final VolunteerScheduleRepository volunteerScheduleRepository;

    /** 개인이 구군 정보를 이용하여 전체 봉사일정을 볼 때 */
    @Override
    public List<VolunteerSchedule> volunteerScheduleListByGugun(String gugunCode) {
        return null;
    }
}
