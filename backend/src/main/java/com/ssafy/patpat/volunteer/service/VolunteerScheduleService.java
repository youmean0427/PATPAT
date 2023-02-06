package com.ssafy.patpat.volunteer.service;

import com.ssafy.patpat.common.code.Reservation;
import com.ssafy.patpat.shelter.dto.ShelterDto;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.shelter.repository.ShelterRepository;
import com.ssafy.patpat.shelter.service.ShelterService;
import com.ssafy.patpat.volunteer.dto.VolunteerNoticeDto;
import com.ssafy.patpat.volunteer.dto.VolunteerScheduleDto;
import com.ssafy.patpat.volunteer.dto.VolunteerShelterDto;
import com.ssafy.patpat.volunteer.entity.VolunteerNotice;
import com.ssafy.patpat.volunteer.entity.VolunteerReservation;
import com.ssafy.patpat.volunteer.entity.VolunteerSchedule;
import com.ssafy.patpat.volunteer.mapping.VolunteerShelterIdMapping;
import com.ssafy.patpat.volunteer.repository.VolunteerReservationRepository;
import com.ssafy.patpat.volunteer.repository.VolunteerScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VolunteerScheduleService{
    private final VolunteerScheduleRepository volunteerScheduleRepository;
    private final VolunteerReservationRepository volunteerReservationRepository;

    /** 개인이 구군 정보를 이용
     * 특정 봉사일정을 보고 싶을 때
     * */
    @Transactional
    public List<VolunteerScheduleDto> selectScheduleList(Long noticeId){

        List<VolunteerSchedule> volunteerSchedules = volunteerScheduleRepository.findWithVolunteerNoticeByVolunteerNoticeNoticeIdAndReservationStateCode(noticeId, Reservation.대기중);
        if(volunteerSchedules.isEmpty()){
            return null;
        }
        List<VolunteerScheduleDto> list = new ArrayList<>();
        for (VolunteerSchedule vs:
                volunteerSchedules) {
            int capacity = 0;
            List<VolunteerReservation> volunteerReservations = volunteerReservationRepository.findWithVolunteerScheduleByVolunteerScheduleScheduleIdAndReservationStateCode(vs.getScheduleId(), Reservation.수락);
            if(!volunteerReservations.isEmpty()){
                for (VolunteerReservation vr:
                     volunteerReservations) {
                    capacity += vr.getCapacity();
                }
            }

            list.add(VolunteerScheduleDto.builder()
                    .scheduleId(vs.getScheduleId())
                    .startTime(vs.getStartTime())
                    .endTime(vs.getEndTime())
                    .totalCapacity(vs.getCapacity())
                    .capacity(capacity)
                    .guideLine(vs.getGuideLine())
                    .reservationState(vs.getReservationStateCode().name())
                    .reservationStateCode(vs.getReservationStateCode().getCode())
                    .build());
        }
        return list;
    }
}
