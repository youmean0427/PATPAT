package com.ssafy.patpat.volunteer.service;

import com.ssafy.patpat.common.code.Reservation;
import com.ssafy.patpat.common.error.VolunteerException;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.shelter.repository.ShelterRepository;
import com.ssafy.patpat.user.service.UserService;
import com.ssafy.patpat.volunteer.dto.*;
import com.ssafy.patpat.volunteer.entity.VolunteerNotice;
import com.ssafy.patpat.volunteer.entity.VolunteerReservation;
import com.ssafy.patpat.volunteer.entity.VolunteerSchedule;
import com.ssafy.patpat.volunteer.repository.VolunteerNoticeRepository;
import com.ssafy.patpat.volunteer.repository.VolunteerReservationRepository;
import com.ssafy.patpat.volunteer.repository.VolunteerScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VolunteerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VolunteerService.class);

    private final VolunteerNoticeRepository volunteerNoticeRepository;
    private final VolunteerScheduleRepository volunteerScheduleRepository;
    private final VolunteerReservationRepository volunteerReservationRepository;

    private final ShelterRepository shelterRepository;

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
            LOGGER.info("탐색 기준이 없습니다.");
            return null;
        }
        if(volunteerNotices.isEmpty()){
            LOGGER.info("검색되는 봉사 공고가 없습니다.");
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

    @Transactional
    public List<VolunteerNoticeDto> selectNoticeListByMonth(VolunteerMonthDto volunteerMonthDto){
        List<VolunteerNotice> volunteerNotices = volunteerNoticeRepository.findWithShelterByShelterShelterIdAndVolunteerDateLikeOrderByVolunteerDateAsc(volunteerMonthDto.getShelterId(), volunteerMonthDto.getYear()+"-"+volunteerMonthDto.getMonth()+"%");
        if(volunteerNotices.isEmpty()){
            LOGGER.info("봉사 공고가 비었습니다.");
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


    /** 개인이 구군 정보를 이용
     * 특정 봉사일정을 보고 싶을 때
     * */
    @Transactional
    public List<VolunteerScheduleDto> selectScheduleList(Long noticeId){

        List<VolunteerSchedule> volunteerSchedules = volunteerScheduleRepository.findWithVolunteerNoticeByVolunteerNoticeNoticeIdAndReservationStateCode(noticeId, Reservation.대기중);
        if(volunteerSchedules.isEmpty()){
            LOGGER.info("봉사 일정이 비었습니다.");
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

    @Transactional
    public boolean insertNotice(NoticeDto noticeDto){

        if(noticeDto.getShelterId() == null){
            LOGGER.info("보호소 Id가 없습니다.");
            return false;
        }
        Optional<Shelter> s = shelterRepository.findById(noticeDto.getShelterId());

        if(!s.isPresent()){
            LOGGER.info("잘못된 보호소 ID입니다.");
            return false;
        }

        // notice 정보
        VolunteerNotice volunteerNotice = VolunteerNotice.builder()
                .title(noticeDto.getTitle())
                .volunteerDate(LocalDate.now().toString())
                .shelter(s.get())
                .build();
        VolunteerNotice v = volunteerNoticeRepository.save(volunteerNotice);

        // schedule 정보
        List<VolunteerSchedule> volunteerSchedules = new ArrayList<>();
        if(noticeDto.getList().isEmpty()){
            // 아무 등록이 없을 때
            LOGGER.info("등록된 봉사 일정이 없습니다.");
            return false;
        }
        for (VolunteerScheduleDto vs:
                noticeDto.getList()) {
            volunteerSchedules.add(VolunteerSchedule.builder()
                    .capacity(vs.getTotalCapacity())
                    .startTime(vs.getStartTime())
                    .endTime(vs.getEndTime())
                    .guideLine(vs.getGuideLine())
                    .volunteerNotice(v)
                    .build());
        }
        volunteerScheduleRepository.saveAll(volunteerSchedules);

        return true;
    }

    @Transactional
    public boolean updateNotice(NoticeDto noticeDto){
        if(noticeDto.getNoticeId() == null){
            LOGGER.info("공고 ID가 비었습니다.");
            return false;
        }
        Optional<VolunteerNotice> vn = volunteerNoticeRepository.findById(noticeDto.getNoticeId());

        if(!vn.isPresent()){
            LOGGER.info("잘못된 공고 ID 입니다.");
            return false;
        }
        // notice 정보 수정
        VolunteerNotice volunteerNotice = VolunteerNotice.builder()
                .title(noticeDto.getTitle())
                .build();
        volunteerNoticeRepository.save(volunteerNotice);

        // schedule 정보 수정
        List<VolunteerSchedule> volunteerSchedules = new ArrayList<>();
        if(noticeDto.getList().isEmpty()){
            // 아무 등록이 없을 때
            LOGGER.info("등록된 봉사 일정이 없습니다.");
            return false;
        }
        for (VolunteerScheduleDto vs:
                noticeDto.getList()) {
            volunteerSchedules.add(VolunteerSchedule.builder()
                    .capacity(vs.getTotalCapacity())
                    .startTime(vs.getStartTime())
                    .endTime(vs.getEndTime())
                    .guideLine(vs.getGuideLine())
                    .build());
        }
        volunteerScheduleRepository.saveAll(volunteerSchedules);

        return true;

    }

    @Transactional
    public boolean deleeteNotice(Long noticeId) throws VolunteerException {
        if(noticeId == null){
            LOGGER.info("공고 ID가 비었습니다.");
            return false;
        }
        Optional<VolunteerNotice> vn = volunteerNoticeRepository.findById(noticeId);

        if(!vn.isPresent()){
            LOGGER.info("잘못된 공고 ID 입니다.");
            return false;
        }
        List<VolunteerSchedule> volunteerSchedules = vn.get().getVolunteerSchedules();

        for (VolunteerSchedule vs:
             volunteerSchedules) {
            List<VolunteerReservation> volunteerReservations = vs.getVolunteerReservations();
            for (VolunteerReservation vr:
                 volunteerReservations) {
                if(vr.getReservationStateCode() == Reservation.수락){
                    throw new VolunteerException("승인된 예약이 있습니다.");
                }
            }
            volunteerReservationRepository.deleteAll(volunteerReservations);
            // schedule 정보 삭제
            volunteerScheduleRepository.delete(vs);
        }
        // notice 정보 삭제
        volunteerNoticeRepository.delete(vn.get());

        return true;
    }

    @Transactional
    public boolean deleteSchedule(Long scheduleId) throws VolunteerException{
        Optional<VolunteerSchedule> volunteerSchedule = volunteerScheduleRepository.findById(scheduleId);
        if(!volunteerSchedule.isPresent()){
            LOGGER.info("잘못된 일정 ID입니다.");
            return false;
        }
        List<VolunteerReservation> volunteerReservations = volunteerSchedule.get().getVolunteerReservations();
        for (VolunteerReservation vr:
                volunteerReservations) {
            if(vr.getReservationStateCode() == Reservation.수락){
                throw new VolunteerException("승인된 예약이 있습니다.");
            }
        }
        volunteerReservationRepository.deleteAll(volunteerReservations);
        return true;
    }
    /**
     * 봉사 지원서 조회(개인)
     * 우선 순위
     * 미완료(3), 수락(1), 대기(0), 완료(5)
     */
    @Transactional
    public ResponseVolunteerDto selectReservationList(RequestVolunteerDto requestVolunteerDto){

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(Reservation.미완료);
        reservations.add(Reservation.수락);
        reservations.add(Reservation.대기중);
        reservations.add(Reservation.완료);
        Optional<Integer> totalCount = volunteerReservationRepository
                .countWithUserByUserUserIdAndReservationStateCodeIn(requestVolunteerDto.getUserId(), reservations);
        if(!totalCount.isPresent()){
            LOGGER.info("목록이 없습니다.");
            return null;
        }
        List<VolunteerReservation> volunteerReservations = volunteerReservationRepository.findWithUserByUserUserIdAndReservationStateCode(requestVolunteerDto.getUserId(), requestVolunteerDto.getOffset(), requestVolunteerDto.getLimit());

        List<VolunteerReservationDto> list = new ArrayList<>();
        for (VolunteerReservation vr:
             volunteerReservations) {
            list.add(VolunteerReservationDto.builder()
                    .reservationId(vr.getReservationId())
                    .scheduleId(vr.getVolunteerSchedule().getScheduleId())
                    .capacity(vr.getCapacity())
                    .shelterName(vr.getShelterName())
                    .reservationState(vr.getReservationStateCode().name())
                    .reservationStateCode(vr.getReservationStateCode().getCode())
                    .build());
        }

        ResponseVolunteerDto responseVolunteerDto = ResponseVolunteerDto.builder()
                .totalCount(totalCount.get())
                .list(Collections.singletonList((list)))
                .build();

        return responseVolunteerDto;
    }


}
