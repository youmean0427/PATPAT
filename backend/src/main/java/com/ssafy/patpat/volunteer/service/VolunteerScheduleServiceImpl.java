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

    @Override
    public List<ShelterDto> volunteerScheduleListInGugun(List<ShelterDto> list) {
        return null;
    }

    @Override
    public List<ShelterDto> volunteerScheduleListInGugun(List<ShelterDto> list) {
        return null;
    }
}
