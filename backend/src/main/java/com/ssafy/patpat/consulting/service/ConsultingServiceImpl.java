package com.ssafy.patpat.consulting.service;

import com.ssafy.patpat.common.code.TimeCode;
import com.ssafy.patpat.common.dto.ResponseListDto;
import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.consulting.dto.ConsultingDto;
import com.ssafy.patpat.consulting.dto.RequestConsultingDto;
import com.ssafy.patpat.consulting.dto.RoomDto;
import com.ssafy.patpat.consulting.dto.TimeDto;
import com.ssafy.patpat.consulting.entity.Consulting;
import com.ssafy.patpat.consulting.entity.Time;
import com.ssafy.patpat.consulting.repository.ConsultingRepository;
import com.ssafy.patpat.consulting.repository.TimeRepository;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.shelter.repository.ShelterRepository;
import com.ssafy.patpat.user.dto.UserDto;
import com.ssafy.patpat.user.service.UserService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    ShelterRepository shelterRepository;

    @Autowired
    UserService userService;

    @Override
    public ResponseListDto selectConsultingList(RequestConsultingDto requestConsultingDto) {
        ResponseListDto responseListDto = new ResponseListDto();
        List<ConsultingDto> consultingDtoList = new ArrayList<>();
//        System.out.println(requestConsultingDto);
        try{
            PageRequest pageRequest = PageRequest.of(requestConsultingDto.getOffSet(),requestConsultingDto.getLimit());
//            System.out.println(requestConsultingDto);
            Page<Consulting> consultingList = consultingRepository.findByUserIdAndRegistDateGreaterThanEqual(requestConsultingDto.getUserId(),LocalDate.now(),pageRequest);
//            System.out.println(consultingList);
            for(Consulting c : consultingList.toList()){
                int transferStateCode = 0;
                if(c.getStateCode() == 2 && c.getRegistDate().equals(LocalDate.now())){
                    transferStateCode = 5;
                    c.updateConsulting(transferStateCode);
                    consultingRepository.save(c);
                }
                else{
                    transferStateCode  = c.getStateCode();
                }
                Shelter shelter = shelterRepository.findByShelterId(c.getShelterId());

                consultingDtoList.add(
                        ConsultingDto.builder()
                                .consultingId(c.getConsultingId())
                                .stateCode(transferStateCode)
                                .registDate(c.getRegistDate())
                                .shelterName(shelter.getName())
                                .address(shelter.getAddress())
                                .timeCode(c.getTimeCode())
                                .shelterId(c.getShelterId())
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
        UserDto userDto = userService.getUserWithAuthorities();
        try{
            PageRequest pageRequest = PageRequest.of(requestConsultingDto.getOffSet(),requestConsultingDto.getLimit());
            Page<Consulting> consultingList = consultingRepository.findByShelterIdAndRegistDateGreaterThanEqual(requestConsultingDto.getShelterId(), LocalDate.now(),pageRequest);
            for(Consulting c : consultingList.toList()){
                int transferStateCode = 0;
                if(c.getStateCode() == 2 && c.getRegistDate().equals(LocalDate.now())){
                    transferStateCode = 5;
                    c.updateConsulting(transferStateCode);
                    consultingRepository.save(c);
                }
                consultingDtoList.add(
                        ConsultingDto.builder()
                                .consultingId(c.getConsultingId())
                                .stateCode(c.getStateCode())
                                .registDate(c.getRegistDate())
                                //임시값
                                .shelterId(c.getShelterId())
                                .userId(requestConsultingDto.getUserId())
                                .userName(userDto.getUsername())
                                .timeCode(c.getTimeCode())
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
    @Override
    public ResponseMessage insertConsulting(ConsultingDto consultingDto) {
        ResponseMessage responseMessage = new ResponseMessage();
        try{
            Consulting consulting = Consulting.builder()
                    .shelterId(consultingDto.getShelterId())
                    .userId(consultingDto.getUserId())
                    .timeCode(consultingDto.getTimeCode())
                    .spDogId(consultingDto.getShelterDogId())
                    .stateCode(0)
                    .registDate(consultingDto.getRegistDate())
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
            consulting.updateConsulting(consultingDto.getStateCode());
            consultingRepository.save(consulting);
            responseMessage.setMessage("SUCCESS");

        }catch (Exception e){
            e.printStackTrace();
            responseMessage.setMessage("FAIL");
        }
        return responseMessage;
    }

    @Override
    public List<TimeDto> selectTimeList(Long shelterId, LocalDate date) {
//        List<Integer> t = new ArrayList<>();
//        t.add(2);
//        t.add(3);
//        int hour = LocalDateTime.now().getHour();
//        timeRepository.findWithShelterByShelterShelterIdAndTimeCodeGreaterThanEqualAndStateNotIn(5, hour, t);
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

        //해당 보호소 가져오기

//        Shelter shelter = shelterRepository.findByShelterId(shelterId);
//        List<Integer> list = new ArrayList<>();
//
//        for(Time t : shelter.getTimeList()){
//            if(t.getState() == 1){
//                list.add(t.timeCode);
//            }
//        }`
        
//        }
//        List<Consulting> consultings = consultingRepository.findByShelterIdAndRegistDate(shelter.getShelterId(),date);
//
//        for(Consulting c : consultings){
//            if(!(c.getStateCode()==2 || c.getStateCode()==3)){
//                list.remove(Integer.valueOf(c.getTimeCode()));
//            }
//        }
//
//        if(date.equals(LocalDate.now())){
//            int hour = LocalDateTime.now().getHour();
//
//            System.out.println(list);
//
//            for(int i=0; i<list.size(); i++){
//                if(list.get(i)==0){
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
//
//        return timeDtoList;
        return null;

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
