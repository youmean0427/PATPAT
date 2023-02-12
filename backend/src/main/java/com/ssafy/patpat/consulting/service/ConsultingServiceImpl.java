package com.ssafy.patpat.consulting.service;

import com.ssafy.patpat.common.code.ConsultingState;
import com.ssafy.patpat.common.code.ProtectState;
import com.ssafy.patpat.common.code.TimeCode;
import com.ssafy.patpat.common.code.TimeState;
import com.ssafy.patpat.common.dto.ResponseListDto;
import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.common.service.FileService;
import com.ssafy.patpat.common.util.SecurityUtil;
import com.ssafy.patpat.consulting.dto.ConsultingDto;
import com.ssafy.patpat.consulting.dto.RequestConsultingDto;
import com.ssafy.patpat.consulting.dto.RoomDto;
import com.ssafy.patpat.consulting.dto.TimeDto;
import com.ssafy.patpat.consulting.entity.Consulting;
import com.ssafy.patpat.consulting.entity.Time;
import com.ssafy.patpat.consulting.mapping.TimeCodeMapping;
import com.ssafy.patpat.consulting.repository.ConsultingRepository;
import com.ssafy.patpat.consulting.repository.TimeRepository;
import com.ssafy.patpat.protect.entity.ShelterProtectedDog;
import com.ssafy.patpat.protect.repository.ShelterProtectedDogRepository;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.shelter.repository.ShelterRepository;
import com.ssafy.patpat.user.dto.UserDto;
import com.ssafy.patpat.user.entity.User;
import com.ssafy.patpat.user.repository.UserRepository;
import com.ssafy.patpat.user.service.UserService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ConsultingServiceImpl implements ConsultingService{

    @Autowired
    ConsultingRepository consultingRepository;

    @Autowired
    TimeRepository timeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ShelterRepository shelterRepository;

    @Autowired
    ShelterProtectedDogRepository shelterProtectedDogRepository;

    @Autowired
    UserService userService;

    @Autowired
    FileService fileService;

    @Override
    public ResponseListDto selectConsultingList(RequestConsultingDto requestConsultingDto) {
        ResponseListDto responseListDto = new ResponseListDto();
        List<ConsultingDto> consultingDtoList = new ArrayList<>();
        Optional<User> user = SecurityUtil.getCurrentEmail().flatMap(userRepository::findOneWithAuthoritiesByEmail);

        try{
            PageRequest pageRequest = null;

//            Integer code = requestConsultingDto.getCode();
            ConsultingState consultingState = null;
            Page<Consulting> consultingList = null;
            if(requestConsultingDto.getStateCode() == null){
                pageRequest = PageRequest.of(requestConsultingDto.getOffSet(),requestConsultingDto.getLimit(), Sort.by("consultingDate").ascending());
                consultingList = consultingRepository.findByUserAndConsultingDateGreaterThanEqual(user.get(),LocalDate.now(),pageRequest);
            }
            else{ //consultingState != null
                consultingState = ConsultingState.of(requestConsultingDto.getStateCode());
                pageRequest = PageRequest.of(requestConsultingDto.getOffSet(),requestConsultingDto.getLimit(), Sort.by("consultingDate").ascending());
                consultingList = consultingRepository.findByUserAndConsultingDateGreaterThanEqualAndConsultingState(user.get(),LocalDate.now(), consultingState,pageRequest);
            }


            for(Consulting c : consultingList.toList()){
                ConsultingState transferStateCode = c.getConsultingState();
//                if(c.getConsultingState() == ConsultingState.승인 && c.getConsultingDate().equals(LocalDate.now())){
//                    transferStateCode = ConsultingState.생성;
//                    c.updateConsulting(transferStateCode);
//                    consultingRepository.save(c);
//                }
//                else{
//                    transferStateCode  = c.getConsultingState();
//                }
                Shelter shelter = shelterRepository.findByShelterId(c.getShelter().getShelterId());

                consultingDtoList.add(
                        ConsultingDto.builder()
                                .consultingId(c.getConsultingId())
                                .stateCode(transferStateCode.getCode())
                                .state(transferStateCode.name())
                                .consultingDate(c.getConsultingDate())
                                .shelterName(shelter.getName())
                                .address(shelter.getAddress())
                                .timeCode(c.getTimeCode().getCode())
                                .time(c.getTimeCode().name())
                                .shelterId(shelter.getShelterId())
                                .shelterDogName(c.getShelterProtectedDog().getName())
                                .registDate(c.getRegistDate())
                                .build()
                );
            }
            responseListDto.setList(consultingDtoList);
            responseListDto.setTotalCount(consultingList.getTotalElements());
            responseListDto.setTotalPage(consultingList.getTotalPages());
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return responseListDto;
    }

    @Override
    public ResponseListDto selectConsultingListByShelter(RequestConsultingDto requestConsultingDto) {
        ResponseListDto responseListDto = new ResponseListDto();
        List<ConsultingDto> consultingDtoList = new ArrayList<>();

        try{
            PageRequest pageRequest = null;

//            Integer code = requestConsultingDto.getCode();
            ConsultingState consultingState = null;
            Page<Consulting> consultingList = null;

            if(requestConsultingDto.getStateCode() == null){
                pageRequest = PageRequest.of(requestConsultingDto.getOffSet(),requestConsultingDto.getLimit(), Sort.by("consultingDate").ascending());
                consultingList = consultingRepository.findByShelterShelterIdAndConsultingDateGreaterThanEqual(requestConsultingDto.getShelterId(),LocalDate.now(),pageRequest);
            }
            else{ //consultingState != null
                consultingState = ConsultingState.of(requestConsultingDto.getStateCode());
                pageRequest = PageRequest.of(requestConsultingDto.getOffSet(),requestConsultingDto.getLimit(), Sort.by("consultingDate").ascending());
                consultingList = consultingRepository.findByShelterShelterIdAndConsultingDateGreaterThanEqualAndConsultingState(requestConsultingDto.getShelterId(),LocalDate.now(), consultingState,pageRequest);
            }
            for(Consulting c : consultingList.toList()){
                ConsultingState transferStateCode = c.getConsultingState();
//                if(c.getConsultingState() == ConsultingState.승인 && c.getConsultingDate().equals(LocalDate.now())){
//                    transferStateCode = ConsultingState.생성;
//                    c.updateConsulting(transferStateCode);
//                    consultingRepository.save(c);
//                }
                consultingDtoList.add(
                        ConsultingDto.builder()
                                .consultingId(c.getConsultingId())
                                .stateCode(transferStateCode.getCode())
                                .state(transferStateCode.name())
                                .consultingDate(c.getConsultingDate())
                                //임시값
                                .shelterName(c.getShelter().getName())
                                .shelterId(c.getShelter().getShelterId())
                                .address(c.getShelter().getAddress())
                                .shelterDogId(c.getShelterProtectedDog().getSpDogId())
                                .shelterDogName(c.getShelterProtectedDog().getName())
                                .userId(c.getUser().getUserId())
                                .userName(c.getUser().getNickname())
                                .userExp(c.getUser().getExp())
                                .userProfileUrl(fileService.getFileUrl(c.getUser().getImage()))
                                .timeCode(c.getTimeCode().getCode())
                                .time(c.getTimeCode().name())
                                .registDate(c.getRegistDate())
                                .build()
                );

            }
            responseListDto.setList(consultingDtoList);
            responseListDto.setTotalPage(consultingList.getTotalPages());
            responseListDto.setTotalCount(consultingList.getTotalElements());
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return responseListDto;
    }

    /**
     *
     * @param consultingDto
     * - shelterId
     * - no userId
     * - shelterDogId
     * 유저가 등록할때
     * @return
     */
    @Override
    public ResponseMessage insertConsulting(ConsultingDto consultingDto) {
        ResponseMessage responseMessage = new ResponseMessage();
        Shelter shelter = shelterRepository.findByShelterId(consultingDto.getShelterId());
        Optional<User> user = SecurityUtil.getCurrentEmail().flatMap(userRepository::findOneWithAuthoritiesByEmail);
        ShelterProtectedDog shelterProtectedDog = shelterProtectedDogRepository.findBySpDogId(consultingDto.getShelterDogId());

        try{
            Consulting consulting = Consulting.builder()
                    .shelter(shelter)
                    .user(user.get())
                    .timeCode(TimeCode.of(consultingDto.getTimeCode()))
                    .shelterProtectedDog(shelterProtectedDog)
                    .consultingState(ConsultingState.대기)
                    .consultingDate(consultingDto.getConsultingDate())
                    .build();
            consultingRepository.save(consulting);
            responseMessage.setMessage("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            responseMessage.setMessage("FAIL");
        }
        return responseMessage;
    }

    @Override
    public ResponseMessage updateConsulting(Long consultingId, ConsultingDto consultingDto) {
        ResponseMessage responseMessage = new ResponseMessage();
        System.out.println(consultingDto);
        try{
            Consulting consulting = consultingRepository.findByConsultingId(consultingId);
            consulting.updateConsulting(ConsultingState.of(consultingDto.getStateCode()));
            consultingRepository.save(consulting);
            responseMessage.setMessage("SUCCESS");

        }catch (Exception e){
            e.printStackTrace();
            responseMessage.setMessage("FAIL");
        }
        return responseMessage;
    }

    @Override
    @Transactional
    public List<TimeDto> selectTimeList(Long shelterId, LocalDate date) {

        List<Time> times = timeRepository.findByShelterShelterId(shelterId);
        List<ConsultingState> consultingStates = new ArrayList<>();
        consultingStates.add(ConsultingState.승인);
        List<TimeCodeMapping> timeCodes = consultingRepository.findByShelterShelterIdAndConsultingDateAndConsultingStateIn(shelterId, date, consultingStates);
        List<TimeDto> timeDtoList = new ArrayList<>();
        // 당일이면?
        int check = date.compareTo(LocalDate.now());
        if(check == 0){
            int hour = LocalDateTime.now().getHour();

            for (Time time:
                    times) {
                boolean ok = time.getActive();
                for (TimeCodeMapping t:
                     timeCodes) {
                    if(t.getTimeCode() == time.getTimeCode()){
                        ok = false;
                        break;
                    }
                }
                if(hour >= time.getTimeCode().getCode()) {
                    ok = false;
                }
                timeDtoList.add(
                        TimeDto.builder()
                                .timeCode(time.getTimeCode().getCode())
                                .time(time.getTimeCode().name())
                                .active(ok)
                                .build()
                );
            }
        }
        else if(check < 0){
            for (Time time:
                    times) {
                boolean ok = false;

                timeDtoList.add(
                        TimeDto.builder()
                                .timeCode(time.getTimeCode().getCode())
                                .time(time.getTimeCode().name())
                                .active(ok)
                                .build()
                );
            }
        }
        else{
            for (Time time:
                    times) {
                boolean ok = time.getActive();
                for (TimeCodeMapping t:
                        timeCodes) {
                    if(t.getTimeCode() == time.getTimeCode()){
                        ok = false;
                        break;
                    }
                }
                timeDtoList.add(
                        TimeDto.builder()
                                .timeCode(time.getTimeCode().getCode())
                                .time(time.getTimeCode().name())
                                .active(ok)
                                .build()
                );
            }
        }

//        Shelter shelter = shelterRepository.findByShelterId(5);
//        List<TimeCode> list = new ArrayList<>();
//
//        for(Time t : shelter.getTimes()){
//            if(t.getState() == 1){
//                list.add(t.getTimeCode());
//            }
//        }
//        List<Consulting> consultings = consultingRepository.findByShelterIdAndRegistDate(shelter.getShelterId(),date);
//
//        for(Consulting c : consultings){
//            if(!(c.getStateCode()==2 || c.getStateCode()==3)){
//                list.remove(TimeCode.of(c.getTimeCode()));
//            }
//        }
//
//        if(date.equals(LocalDate.now())){
//            int hour = LocalDateTime.now().getHour();
//
//            System.out.println(list);
//
//            for (TimeCode time:
//                 list) {
//                if(time.getCode() <= hour ){
//                    list.re
//                }
//            }
//            for(int i=0; i<list.size(); i++){
//                if(list.get(i).getCode()==10){
//                    if(list.get(i)+10 <= hour) {
//                        list.remove(Integer.valueOf(list.get(i)));
//                        i--;
//                    }
//                }
//                else{
//                    if(list.get(i)+13 <= hour){
//                        list.remove(Integer.valueOf(list.get(i)));
//                        i--;
//                    }
//                }
//            }
//        }
//
//        System.out.println(list);
//        List<TimeDto> timeDtoList = new ArrayList<>();
//        for(Integer i : list){
//            timeDtoList.add(new TimeDto(i));
//        }

//        return timeDtoList;
//        return null;


        return timeDtoList;

    }

    @Override
    public RoomDto selectRoomDto(Long shelterId,Long consultingId) {
        Shelter shelter = shelterRepository.findByShelterId(shelterId);
        //유저이름 보내주기
        UserDto userDto = userService.getUserWithAuthorities();
        RoomDto roomDto = RoomDto.builder()
                .shelterName(shelter.getName())
                .userName(userDto.getUsername())
                .shelterId(shelterId)
                .consultingId(consultingId)
                .build();
        return roomDto;
    }

}
