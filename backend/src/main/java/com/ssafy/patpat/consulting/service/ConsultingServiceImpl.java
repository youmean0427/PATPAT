package com.ssafy.patpat.consulting.service;

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
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class ConsultingServiceImpl implements ConsultingService{

    @Autowired
    ConsultingRepository consultingRepository;

    @Autowired
    ShelterRepository shelterRepository;

    @Override
    public List<ConsultingDto> selectConsultingList(RequestConsultingDto requestConsultingDto) {
        List<ConsultingDto> consultingDtoList = new ArrayList<>();
        System.out.println(requestConsultingDto);
        try{
            PageRequest pageRequest = PageRequest.of(requestConsultingDto.getOffSet(),requestConsultingDto.getLimit());
            System.out.println(requestConsultingDto);
            List<Consulting> consultingList = consultingRepository.findByUserIdAndRegistDateGreaterThanEqual(requestConsultingDto.getUserId(),LocalDate.now(),pageRequest);
            System.out.println(consultingList);
            for(Consulting c : consultingList){
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
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return consultingDtoList;
    }

    @Override
    public List<ConsultingDto> selectConsultingListByShelter(RequestConsultingDto requestConsultingDto) {
        List<ConsultingDto> consultingDtoList = new ArrayList<>();
        try{
            PageRequest pageRequest = PageRequest.of(requestConsultingDto.getOffSet(),requestConsultingDto.getLimit());
            List<Consulting> consultingList = consultingRepository.findByShelterIdAndRegistDateGreaterThanEqual(requestConsultingDto.getShelterId(), LocalDate.now(),pageRequest);
            for(Consulting c : consultingList){
                int transferStateCode = 0;
                if(c.getStateCode() == 2 && c.getRegistDate().equals(LocalDate.now())){
                    transferStateCode = 5;
                    c.updateConsulting(transferStateCode);
                    consultingRepository.save(c);
                }
                else{
                    transferStateCode  = c.getStateCode();
                }
                consultingDtoList.add(
                        ConsultingDto.builder()
                                .consultingId(c.getConsultingId())
                                .stateCode(transferStateCode)
                                .registDate(c.getRegistDate())
                                //임시값
                                .shelterId(c.getShelterId())
                                .userId(requestConsultingDto.getUserId())
                                .userName("유저아이디로 이름 가져오기")
                                .timeCode(c.getTimeCode())
                                .build()
                );

            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return consultingDtoList;
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
    public ResponseMessage updateConsulting(int consultingId, ConsultingDto consultingDto) {
        ResponseMessage responseMessage = new ResponseMessage();
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
    public List<TimeDto> selectTimeList(int shelterId, LocalDate date) {
        Shelter shelter = shelterRepository.findByShelterId(5);
        List<Integer> list = new ArrayList<>();

        for(Time t : shelter.getTimeList()){
            if(t.getState() == 1){
                list.add(t.timeCode);
            }
        }
        List<Consulting> consultings = consultingRepository.findByShelterIdAndRegistDate(shelter.getShelterId(),date);

        for(Consulting c : consultings){
            if(!(c.getStateCode()==2 || c.getStateCode()==3)){
                list.remove(Integer.valueOf(c.getTimeCode()));
            }
        }

        if(date.equals(LocalDate.now())){
            int hour = LocalDateTime.now().getHour();

            System.out.println(list);

            for(int i=0; i<list.size(); i++){
                if(list.get(i)==0){
                    if(list.get(i)+10 <= hour) {
                        list.remove(Integer.valueOf(list.get(i)));
                        i--;
                    }
                }
                else{
                    if(list.get(i)+13 <= hour){
                        list.remove(Integer.valueOf(list.get(i)));
                        i--;
                    }
                }
            }
        }

        System.out.println(list);
        List<TimeDto> timeDtoList = new ArrayList<>();
        for(Integer i : list){
            timeDtoList.add(new TimeDto(i));
        }

        return timeDtoList;
    }

    @Override
    public RoomDto selectRoomDto(int shelterId,int consultingId) {
        Shelter shelter = shelterRepository.findByShelterId(shelterId);
        //유저이름 보내주기
        RoomDto roomDto = RoomDto.builder()
                .shelterName(shelter.getName())
                .userName("경훈")
                .shelterId(shelterId)
                .consultingId(consultingId)
                .build();
        return roomDto;
    }

}
