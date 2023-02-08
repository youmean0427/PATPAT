package com.ssafy.patpat.consulting.service;

import com.ssafy.patpat.common.dto.ResponseListDto;
import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.consulting.dto.ConsultingDto;
import com.ssafy.patpat.consulting.dto.RequestConsultingDto;
import com.ssafy.patpat.consulting.dto.RoomDto;
import com.ssafy.patpat.consulting.dto.TimeDto;
import com.ssafy.patpat.consulting.entity.Consulting;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;

public interface ConsultingService {

    ResponseListDto selectConsultingList(RequestConsultingDto requestConsultingDto);
    ResponseListDto selectConsultingListByShelter(RequestConsultingDto requestConsultingDto);
    ResponseMessage insertConsulting(ConsultingDto consultingDto);
    ResponseMessage updateConsulting(Long consultingId, ConsultingDto consultingDto);

    List<TimeDto> selectTimeList(Long shelterId, LocalDate date);

    RoomDto selectRoomDto(Long shelterId,Long consultingId);

}
