package com.ssafy.patpat.volunteer.service;

import com.ssafy.patpat.common.code.ConsultingState;
import com.ssafy.patpat.common.code.Reservation;
import com.ssafy.patpat.common.dto.ResponseListDto;
import com.ssafy.patpat.common.error.VolunteerException;
import com.ssafy.patpat.common.util.SecurityUtil;
import com.ssafy.patpat.shelter.dto.ShelterLocationDto;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.shelter.mapping.ShelterDistanceMapping;
import com.ssafy.patpat.shelter.repository.ShelterRepository;
import com.ssafy.patpat.user.entity.User;
import com.ssafy.patpat.user.repository.UserRepository;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VolunteerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VolunteerService.class);

    private final VolunteerNoticeRepository volunteerNoticeRepository;
    private final VolunteerScheduleRepository volunteerScheduleRepository;
    private final VolunteerReservationRepository volunteerReservationRepository;
    private final UserRepository userRepository;
    private final ShelterRepository shelterRepository;

    /**
     * 봉사 공고 조회(전체)
     * 봉사 찾기로 구군으로 탐색 했을 경우 X
     * 이제 위도 경도를 준답니다.
     * 개인이 보호소 페이지에서 보는 경우
     * @return
     */
    @Transactional
    public List<ShelterLocationDto> selectShelterListByLatLng(RequestVolunteerDto requestVolunteerDto){
//        PageRequest pageRequest = PageRequest.of(requestVolunteerDto.getOffSet(),requestVolunteerDto.getLimit(), Sort.by("volunteerDate").ascending());
        List<VolunteerNotice> volunteerNotices;
        if(requestVolunteerDto.getLatitude() != null &&
                requestVolunteerDto.getLongitude() != null){
            BigDecimal a = new BigDecimal(requestVolunteerDto.getLatitude());
            BigDecimal b = new BigDecimal(requestVolunteerDto.getLongitude());
            List<ShelterDistanceMapping> sheltersInDistance = shelterRepository.findAllShelter(a, b, a, 30);
//            LOGGER.info("뜨긴 뜨나 {}",sheltersInDistance.get(0).getDistance());
            List<Long> shelters = sheltersInDistance.stream().map( s -> s.getShelterId()).collect(Collectors.toList());
//            List<Long> shelterIds = shelters.stream()
//                    .filter( s -> !s.getVolunteerNotices().isEmpty())
//                    .map( s -> s.getShelterId())
//                    .collect(Collectors.toList());

//            List<Shelter> shelterList = shelterRepository.findByShelterIdInAndVolunteerNoticeReservationStateCodeAndVolunteerNoticeVolunteerDateBetween(shelterIds, Reservation.대기중, LocalDate.now().plusDays(1).toString(), LocalDate.now().plusDays(7L).toString());

            volunteerNotices = volunteerNoticeRepository.findWithShelterByShelterShelterIdInAndReservationStateCodeAndVolunteerDateBetween(shelters, Reservation.대기중, LocalDate.now().plusDays(1).toString(), LocalDate.now().plusDays(7L).toString());
            if(volunteerNotices.isEmpty()){
                LOGGER.info("검색되는 봉사 공고가 없습니다.");
                return null;
            }
            List<Shelter> shelterList = volunteerNotices.stream().map( v -> v.getShelter()).distinct().collect(Collectors.toList());
            List<ShelterLocationDto> shelterLocationDtos = shelterList.stream()
                    .map(ShelterLocationDto::new).collect(Collectors.toList());

//            List<VolunteerNoticeDto> list = new ArrayList<>();
//            for (VolunteerNotice vn:
//                    volunteerNotices) {
////                LOGGER.info("검색된 봉사 공고의 보호소 id {}",vn.getShelter().getShelterId());
////                List<Long> scheduleId = new ArrayList<>();
////                for (VolunteerSchedule vs:
////                        vn.getVolunteerSchedules()) {
////                    scheduleId.add(vs.getScheduleId());
////                }
//                List<Long> scheduleId = vn.getVolunteerSchedules().stream()
//                        .map(vs -> vs.getScheduleId()).collect(Collectors.toList());
//
////                LOGGER.info("distance {}", sheltersInDistance.stream()
////                        .filter(s -> s.getShelterId().equals(vn.getShelter().getShelterId()))
////                        .findAny()
////                        .map( d -> d.getDistance())
////                        .get());
//
//                list.add(VolunteerNoticeDto.builder()
//                        .name(vn.getShelter().getName())
//                        .noticeId(vn.getNoticeId())
//                        .shelterId(vn.getShelter().getShelterId())
//                        .state(vn.getReservationStateCode().name())
//                        .stateCode(vn.getReservationStateCode().getCode())
//                        .title(vn.getTitle())
//                        .scheduleId(scheduleId)
//                        .volunteerDate(vn.getVolunteerDate())
//                        .distance(sheltersInDistance.stream()
//                            .filter(s -> s.getShelterId().equals(vn.getShelter().getShelterId()))
//                            .findAny()
//                            .map(d -> d.getDistance())
//                            .get())
//                        .latitude(vn.getShelter().getLatitude().toString())
//                        .longitude(vn.getShelter().getLongitude().toString())
//                        .build());
//            }
//            ResponseListDto responseVolunteerDto = new ResponseListDto();
//            responseVolunteerDto.setTotalCount(volunteerNotices.getTotalElements());
//            responseVolunteerDto.setTotalPage(volunteerNotices.getTotalPages());
//            responseVolunteerDto.setList(list);

            return shelterLocationDtos;

        }else{
            LOGGER.info("탐색 기준이 없습니다.");
            return null;
        }

    }

    @Transactional
    public List<VolunteerNoticeDto> selectNoticeListByLatLng(Long shelterId){
        List<VolunteerNotice> volunteerNotices =
                volunteerNoticeRepository.findWithShelterByShelterShelterIdAndReservationStateCodeAndVolunteerDateBetween(shelterId, Reservation.대기중, LocalDate.now().plusDays(1).toString(), LocalDate.now().plusDays(7L).toString());
        if(volunteerNotices.isEmpty()){
            LOGGER.info("리스트가 없음.");
            return null;
        }

        List<VolunteerNoticeDto> list =
                volunteerNotices.stream()
                .map(VolunteerNoticeDto::new)
                .collect(Collectors.toList());
        return list;
    }


    @Transactional
    public ResponseListDto selectNoticeListByShelter(RequestVolunteerDto requestVolunteerDto){
        PageRequest pageRequest = PageRequest.of(requestVolunteerDto.getOffSet(),requestVolunteerDto.getLimit(), Sort.by("volunteerDate").ascending());
        Page<VolunteerNotice> volunteerNotices;

        if(requestVolunteerDto.getShelterId() != null){
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
                volunteerNotices.toList()) {
            List<Long> scheduleId = new ArrayList<>();
            for (VolunteerSchedule vs:
                    vn.getVolunteerSchedules()) {
                scheduleId.add(vs.getScheduleId());
            }
            list.add(VolunteerNoticeDto.builder()
                    .name(vn.getShelter().getName())
                    .noticeId(vn.getNoticeId())
                    .shelterId(vn.getShelter().getShelterId())
                    .state(vn.getReservationStateCode().name())
                    .stateCode(vn.getReservationStateCode().getCode())
                    .title(vn.getTitle())
                    .scheduleId(scheduleId)
                    .volunteerDate(vn.getVolunteerDate())
                    .build());
        }
        ResponseListDto responseVolunteerDto = new ResponseListDto();
        responseVolunteerDto.setTotalCount(volunteerNotices.getTotalElements());
        responseVolunteerDto.setTotalPage(volunteerNotices.getTotalPages());
        responseVolunteerDto.setList(list);

        return responseVolunteerDto;
    }


    @Transactional
    public List<VolunteerNoticeDto> selectNoticeListByMonth(VolunteerMonthDto volunteerMonthDto){
        String year = volunteerMonthDto.getYear().toString();
        String month = volunteerMonthDto.getMonth() < 10 ? "0"+volunteerMonthDto.getMonth().toString() : volunteerMonthDto.getMonth().toString();
        List<VolunteerNotice> volunteerNotices = volunteerNoticeRepository.findWithShelterByShelterShelterIdAndVolunteerDateLikeOrderByVolunteerDateAsc(volunteerMonthDto.getShelterId(), year+"-"+month+"%");
        List<VolunteerNoticeDto> list = new ArrayList<>();
        if(volunteerNotices.isEmpty()){
            LOGGER.info("봉사 공고가 비었습니다.");
            return null;
        }

        for (VolunteerNotice vn:
                volunteerNotices) {
            List<Long> scheduleId = new ArrayList<>();
            for (VolunteerSchedule vs:
                    vn.getVolunteerSchedules()) {
                scheduleId.add(vs.getScheduleId());
            }

            list.add(VolunteerNoticeDto.builder()
                    .name(vn.getShelter().getName())
                    .noticeId(vn.getNoticeId())
                    .shelterId(vn.getShelter().getShelterId())
                    .state(vn.getReservationStateCode().name())
                    .stateCode(vn.getReservationStateCode().getCode())
                    .title(vn.getTitle())
                    .scheduleId(scheduleId)
                    .volunteerDate(vn.getVolunteerDate())
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

        return true;

    }

    @Transactional
    public boolean deleteNotice(Long noticeId) throws VolunteerException {
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
                if(vr.getReservationStateCode() == Reservation.승인){
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

    /** 개인이 구군 정보를 이용
     * 특정 봉사일정을 보고 싶을 때
     * */
    @Transactional
    public ResponseListDto selectScheduleList(RequestVolunteerDto requestVolunteerDto){

        Optional<VolunteerSchedule> volunteerSchedule = volunteerScheduleRepository.findById(requestVolunteerDto.getScheduleId());
        if(!volunteerSchedule.isPresent()){
            LOGGER.info("봉사 일정이 비었습니다.");
            return null;
        }

        VolunteerSchedule vs = volunteerSchedule.get();
        int capacity = 0;
        PageRequest pageRequest = PageRequest.of(requestVolunteerDto.getOffSet(),requestVolunteerDto.getLimit());
        Page<VolunteerReservation> volunteerReservations = volunteerReservationRepository.findWithVolunteerScheduleByVolunteerScheduleScheduleIdAndReservationStateCodeNot(vs.getScheduleId(), Reservation.거절, pageRequest);
        List<VolunteerReservationDto> list = new ArrayList<>();
        if(!volunteerReservations.isEmpty()){
            for (VolunteerReservation vr:
                    volunteerReservations.toList()) {
                if(vr.getReservationStateCode() == Reservation.승인) {
                    capacity += vr.getCapacity();
                }
                list.add(VolunteerReservationDto.builder()
                        .reservationId(vr.getReservationId())
                        .scheduleId(vs.getScheduleId())
                        .capacity(vr.getCapacity())
                        .shelterName(vr.getVolunteerSchedule().getVolunteerNotice().getShelter().getName())
                        .shelterAddress(vr.getVolunteerSchedule().getVolunteerNotice().getShelter().getAddress())
                        .volunteerDate(vr.getVolunteerSchedule().getVolunteerNotice().getVolunteerDate())
                        .startTime(vr.getVolunteerSchedule().getStartTime())
                        .endTime(vr.getVolunteerSchedule().getEndTime())
                        .reservationState(vr.getReservationStateCode().name())
                        .reservationStateCode(vr.getReservationStateCode().getCode())
                        .build());
            }
        }

        ResponseListDto responseVolunteerDto = new ResponseListDto();

        responseVolunteerDto.setList(list);
        responseVolunteerDto.setTotalCount(volunteerReservations.getTotalElements());
        responseVolunteerDto.setTotalPage(volunteerReservations.getTotalPages());
        VolunteerScheduleDto volunteerScheduleDto = VolunteerScheduleDto.builder()
                .scheduleId(vs.getScheduleId())
                .noticeId(vs.getVolunteerNotice().getNoticeId())
                .startTime(vs.getStartTime())
                .endTime(vs.getEndTime())
                .totalCapacity(vs.getCapacity())
                .capacity(capacity)
                .guideLine(vs.getGuideLine())
                .reservationState(vs.getReservationStateCode().name())
                .reservationStateCode(vs.getReservationStateCode().getCode())
                .responseListDto(responseVolunteerDto)
                .build();

        return responseVolunteerDto;
    }

    @Transactional
    public VolunteerScheduleDto detailSchedule(Long scheduleId){

        Optional<VolunteerSchedule> volunteerSchedule = volunteerScheduleRepository.findById(scheduleId);
        if(!volunteerSchedule.isPresent()){
            LOGGER.info("봉사 일정이 비었습니다.");
            return null;
        }

        VolunteerSchedule vs = volunteerSchedule.get();

        VolunteerScheduleDto volunteerScheduleDto = VolunteerScheduleDto.builder()
                .scheduleId(vs.getScheduleId())
                .noticeId(vs.getVolunteerNotice().getNoticeId())
                .startTime(vs.getStartTime())
                .endTime(vs.getEndTime())
                .totalCapacity(vs.getCapacity())
                .capacity(vs.getCapacity())
                .guideLine(vs.getGuideLine())
                .reservationState(vs.getReservationStateCode().name())
                .reservationStateCode(vs.getReservationStateCode().getCode())
                .build();
        return volunteerScheduleDto;

    }

    @Transactional
    public boolean insertSchedule(NoticeDto noticeDto){

        Optional<VolunteerNotice> vn = volunteerNoticeRepository.findById(noticeDto.getNoticeId());

        if(!vn.isPresent()){
            LOGGER.info("잘못된 봉사 공고 id 입니다.");
            return false;
        }
        List<VolunteerSchedule> volunteerSchedules = new ArrayList<>();

        List<VolunteerScheduleDto> volunteerScheduleDtos = noticeDto.getList();
        if(volunteerScheduleDtos.isEmpty()){
            LOGGER.info("등록된 정보가 없습니다.");
            return false;
        }

        for (VolunteerScheduleDto vs:
             volunteerScheduleDtos) {
            volunteerSchedules.add(
                    VolunteerSchedule.builder()
                    .volunteerNotice(vn.get())
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
    public boolean updateSchedule(VolunteerScheduleDto volunteerScheduleDto){

        Optional<VolunteerSchedule> volunteerSchedule = volunteerScheduleRepository.findById(volunteerScheduleDto.getScheduleId());

        if(!volunteerSchedule.isPresent()){
            // 아무 등록이 없을 때
            LOGGER.info("등록된 봉사 일정이 없습니다.");
            return false;
        }
        VolunteerSchedule vs = VolunteerSchedule.builder()
                .scheduleId(volunteerScheduleDto.getScheduleId())
                .capacity(volunteerScheduleDto.getTotalCapacity())
                .startTime(volunteerScheduleDto.getStartTime())
                .endTime(volunteerScheduleDto.getEndTime())
                .guideLine(volunteerScheduleDto.getGuideLine())
                .build();

        volunteerScheduleRepository.save(vs);

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
            if(vr.getReservationStateCode() == Reservation.승인){
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
    public ResponseListDto selectReservationList(RequestVolunteerDto requestVolunteerDto){

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(Reservation.승인);
        reservations.add(Reservation.대기중);
        reservations.add(Reservation.완료);
        Optional<User> user = SecurityUtil.getCurrentEmail().flatMap(userRepository::findOneWithAuthoritiesByEmail);
        Optional<Long> totalCount = volunteerReservationRepository
                .countWithUserByUserUserIdAndReservationStateCodeIn(user.get().getUserId(), reservations);
        if(!totalCount.isPresent()){
            LOGGER.info("목록이 없습니다.");
            return null;
        }

        PageRequest pageRequest = PageRequest.of(requestVolunteerDto.getOffSet(),requestVolunteerDto.getLimit(), Sort.by("volunteerDate").ascending());
        Page<VolunteerReservation> volunteerReservations = volunteerReservationRepository.findByUserAndVolunteerDateGreaterThanEqual(user.get(), LocalDate.now().toString(), pageRequest);

        List<VolunteerReservationDto> list = new ArrayList<>();
        for (VolunteerReservation vr:
                volunteerReservations) {
            list.add(VolunteerReservationDto.builder()
                    .reservationId(vr.getReservationId())
                    .scheduleId(vr.getVolunteerSchedule().getScheduleId())
                    .noticeId(vr.getVolunteerSchedule().getVolunteerNotice().getNoticeId())
                    .capacity(vr.getCapacity())
                    .shelterName(vr.getVolunteerSchedule().getVolunteerNotice().getShelter().getName())
                    .shelterAddress(vr.getVolunteerSchedule().getVolunteerNotice().getShelter().getAddress())
                    .volunteerDate(vr.getVolunteerSchedule().getVolunteerNotice().getVolunteerDate())
                    .startTime(vr.getVolunteerSchedule().getStartTime())
                    .endTime(vr.getVolunteerSchedule().getEndTime())
                    .reservationState(vr.getReservationStateCode().name())
                    .reservationStateCode(vr.getReservationStateCode().getCode())
                    .build());
        }


        Long totalPage = totalCount.get() % requestVolunteerDto.getLimit() == 0 ?
                totalCount.get() / requestVolunteerDto.getLimit()
                : (totalCount.get() / requestVolunteerDto.getLimit()) +1;

        ResponseListDto responseVolunteerDto = new ResponseListDto();
        responseVolunteerDto.setList(list);
        responseVolunteerDto.setTotalPage(volunteerReservations.getTotalPages());
        responseVolunteerDto.setTotalCount(volunteerReservations.getTotalElements());

        return responseVolunteerDto;
    }

    /** 예약 등록 */
    @Transactional
    public boolean insertReservation(ReservationDto reservationDto) throws VolunteerException {
        /** 봉사 일정 조회 */
        Optional<VolunteerSchedule> volunteerSchedule = volunteerScheduleRepository.findById(reservationDto.getScheduleId());
        if(!volunteerSchedule.isPresent()){
            LOGGER.info("등록된 공고가 아닙니다.");
            return false;
        }
        /** user 조회 */
        Optional<User> user = SecurityUtil.getCurrentEmail().flatMap(userRepository::findOneWithAuthoritiesByEmail);
        if(!user.isPresent()){
            LOGGER.info("등록된 유저가 아닙니다.");
            return false;
        }
        if(volunteerSchedule.get().getTotaclCapacity() < volunteerSchedule.get().getCapacity() + reservationDto.getCapacity()){
            throw new VolunteerException("인원이 맞지 않습니다.");
        }

        VolunteerReservation volunteerReservation =
                VolunteerReservation.builder()
                        .capacity(reservationDto.getCapacity())
                        .user(user.get())
                        .volunteerDate(volunteerSchedule.get().getVolunteerNotice().getVolunteerDate())
                        .volunteerSchedule(volunteerSchedule.get())
                        .build();
        volunteerReservationRepository.save(volunteerReservation);

        return true;
    }

    @Transactional
    public boolean updateReservation(ReservationDto reservationDto) throws VolunteerException {
        Optional<VolunteerReservation> volunteerReservation = volunteerReservationRepository.findById(reservationDto.getReservationId());
        if(!volunteerReservation.isPresent()){
            LOGGER.info("유효한 예약이 아닙니다.");
            return false;
        }
        Optional<User> user = SecurityUtil.getCurrentEmail().flatMap(userRepository::findOneWithAuthoritiesByEmail);
        if(!user.isPresent()){
            LOGGER.info("등록된 회원이 아닙니다.");
            return false;
        }
        if(volunteerReservation.get().getReservationStateCode() == Reservation.승인){
            throw new VolunteerException("이미 승인된 예약입니다.");
        }

        VolunteerReservation vr = volunteerReservation.get();
        vr.setCapacity(reservationDto.getCapacity());
        volunteerReservationRepository.save(vr);

        return true;
    }

    @Transactional
    public boolean deleteReservation(ReservationDto reservationDto) throws VolunteerException {
        Optional<VolunteerReservation> volunteerReservation = volunteerReservationRepository.findById(reservationDto.getReservationId());
        if(!volunteerReservation.isPresent()){
            LOGGER.info("유효한 예약이 아닙니다.");
            return false;
        }
        VolunteerReservation vr = volunteerReservation.get();
        if(vr.getUser().getUserId() != reservationDto.getUserId()){
            LOGGER.info("등록된 회원이 아닙니다.");
            return false;
        }
        if(vr.getReservationStateCode() != Reservation.대기중){
            LOGGER.info("이미 처리된 예약입니다.");
            return false;
        }

        volunteerReservationRepository.delete(volunteerReservation.get());
        return true;
    }

    @Transactional
    public boolean changeReservationState(ReservationDto reservationDto) throws VolunteerException {
        LOGGER.info("dto{}", reservationDto.toString());
        Optional<VolunteerReservation> volunteerReservation = volunteerReservationRepository.findById(reservationDto.getReservationId());
        if(!volunteerReservation.isPresent()){
            LOGGER.info("유효한 예약이 아닙니다.");
            throw new VolunteerException("유효한 예약이 아닙니다.");
        }
        VolunteerReservation vr = volunteerReservation.get();
//        if(vr.getUser().getUserId() != reservationDto.getUserId()){
//            LOGGER.info("등록된 회원이 아닙니다.");
//            return false;
//        }
//        if(vr.getReservationStateCode() == Reservation.완료 || vr.getReservationStateCode() == Reservation.승인){
//            LOGGER.info("이미 처리된 예약입니다.");
//            throw new VolunteerException("이미 처리된 예약입니다.");
//        }

        // 수락시
        if(reservationDto.getStateCode() == 1){
            vr.setReservationStateCode(Reservation.승인);
            volunteerReservationRepository.save(vr);

            // 전체 봉사 일정 체크 필요
            VolunteerSchedule volunteerSchedule = vr.getVolunteerSchedule();
            int totalCapacity = volunteerSchedule.getTotaclCapacity();
            int capacity = volunteerSchedule.getCapacity();
            if(totalCapacity == capacity + vr.getCapacity()){
                volunteerSchedule.setReservationStateCode(Reservation.승인);
                volunteerScheduleRepository.save(volunteerSchedule);

                // 전체 공고 체크 필요
                VolunteerNotice volunteerNotice = volunteerSchedule.getVolunteerNotice();
                boolean ok = true;
                for (VolunteerSchedule vs:
                        volunteerNotice.getVolunteerSchedules()) {
                    if(vs.getReservationStateCode() != Reservation.승인){
                        ok = false;
                    }
                }
                if(ok){
                    volunteerNotice.setReservationStateCode(Reservation.승인);
                }
            }

            // 나머지 예약들 거절 시키기
            List<VolunteerReservation> volunteerReservations = volunteerSchedule.getVolunteerReservations();
            for (VolunteerReservation vrs:
                 volunteerReservations) {
                if(vrs.getReservationId() != vr.getReservationId()){
                    vrs.setReservationStateCode(Reservation.거절);
                    volunteerReservationRepository.save(vrs);
                }
            }
        }

        // 거절시
        if(reservationDto.getStateCode() == 2){
            vr.setReservationStateCode(Reservation.거절);
            volunteerReservationRepository.save(vr);
            // 전체 봉사 일정 체크 필요
            VolunteerSchedule volunteerSchedule = vr.getVolunteerSchedule();
            if(volunteerSchedule.getReservationStateCode() == Reservation.승인){
                int capacity = volunteerSchedule.getCapacity();
                volunteerSchedule.setCapacity(capacity-vr.getCapacity());
                volunteerSchedule.setReservationStateCode(Reservation.대기중);
                volunteerScheduleRepository.save(volunteerSchedule);

                // 전체 공고 체크 필요
                VolunteerNotice volunteerNotice = volunteerSchedule.getVolunteerNotice();
                if(volunteerNotice.getReservationStateCode() == Reservation.승인){
                    volunteerNotice.setReservationStateCode(Reservation.대기중);
                    volunteerNoticeRepository.save(volunteerNotice);
                }
            }
            // 나머지 예약들 대기중 바꾸기
            List<VolunteerReservation> volunteerReservations = volunteerSchedule.getVolunteerReservations();
            for (VolunteerReservation vrs:
                    volunteerReservations) {
                if(vrs.getReservationId() != vr.getReservationId()){
                    vrs.setReservationStateCode(Reservation.대기중);
                    volunteerReservationRepository.save(vrs);
                }
            }

        }
        if(reservationDto.getStateCode() == 4){
            vr.setReservationStateCode(Reservation.완료);
            volunteerReservationRepository.save(vr);
            /** ---- 경험치 올리기 코드 ----**/
                User user = vr.getUser();
                user.updateExp(user.getExp()+1);
                userRepository.save(user);
            /** ------------------------**/
        }
        if(reservationDto.getStateCode() == 3){
            vr.setReservationStateCode(Reservation.불참);
            volunteerReservationRepository.save(vr);
            /** ---- 경험치 올리기 코드 ----**/
            User user = vr.getUser();
            user.updateExp(user.getExp()-1);
            userRepository.save(user);
            /** ------------------------**/
        }


        return true;
    }

    @Transactional
    public boolean completeReservationState(ReservationDto reservationDto) throws VolunteerException {
        Optional<VolunteerReservation> volunteerReservation = volunteerReservationRepository.findById(reservationDto.getReservationId());
        if(!volunteerReservation.isPresent()){
            LOGGER.info("유효한 예약이 아닙니다.");
            throw new VolunteerException("유효한 예약이 아닙니다.");
        }
        VolunteerReservation vr = volunteerReservation.get();
//        if(vr.getUser().getUserId() != reservationDto.getUserId()){
//            LOGGER.info("등록된 회원이 아닙니다.");
//            return false;
//        }
        if(vr.getReservationStateCode() == Reservation.완료){
            LOGGER.info("이미 처리된 예약입니다.");
            throw new VolunteerException("이미 처리된 예약입니다.");
        }

        // 누르면 완료야
        vr.setReservationStateCode(Reservation.완료);
        volunteerReservationRepository.save(vr);

        return true;
    }
}
