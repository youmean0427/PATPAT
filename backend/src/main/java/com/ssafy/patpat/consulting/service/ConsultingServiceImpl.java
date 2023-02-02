package com.ssafy.patpat.consulting.service;

import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.consulting.dto.ConsultingDto;
import com.ssafy.patpat.consulting.dto.RequestConsultingDto;
import com.ssafy.patpat.consulting.entity.Consulting;
import com.ssafy.patpat.consulting.repository.ConsultingRepository;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.shelter.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.parameters.P;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ConsultingServiceImpl implements ConsultingService{

    @Autowired
    ConsultingRepository consultingRepository;

    @Autowired
    ShelterRepository shelterRepository;

    @Override
    public List<ConsultingDto> selectConsultingList(RequestConsultingDto requestConsultingDto) {
        List<ConsultingDto> consultingDtoList = new ArrayList<>();
        try{
            PageRequest pageRequest = PageRequest.of(requestConsultingDto.getOffSet(),requestConsultingDto.getLimit());
            List<Consulting> consultingList = consultingRepository.findByUserId(requestConsultingDto.getUserId(),pageRequest);
            for(Consulting c : consultingList){
                Shelter shelter = shelterRepository.findByShelterId(c.getShelterId());
                consultingDtoList.add(
                        ConsultingDto.builder()
                                .consultingId(c.getConsultingId())
                                .stateCode(c.getStateCode())
                                .registDate(c.getRegistDate())
                                .startTime(LocalDateTime.now())
                                .endTime(LocalDateTime.now())
                                .shelterName(shelter.getName())
                                .address(shelter.getAddress())
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
            List<Consulting> consultingList = consultingRepository.findByShelterId(requestConsultingDto.getShelterId(),pageRequest);
            for(Consulting c : consultingList){
                consultingDtoList.add(
                        ConsultingDto.builder()
                                .consultingId(c.getConsultingId())
                                .stateCode(c.getStateCode())
                                .registDate(c.getRegistDate())
                                .startTime(LocalDateTime.now())
                                .endTime(LocalDateTime.now())
                                //임시값
                                .userName("유저아이디로 이름 가져오기")
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
}
