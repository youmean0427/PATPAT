package com.ssafy.patpat.consulting.service;

import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.consulting.dto.ConsultingDto;
import com.ssafy.patpat.consulting.dto.RequestConsultingDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ConsultingService {

    List<ConsultingDto> selectConsultingList(RequestConsultingDto requestConsultingDto);
    List<ConsultingDto> selectConsultingListByShelter(RequestConsultingDto requestConsultingDto);
    ResponseMessage insertConsulting(ConsultingDto consultingDto);
    ResponseMessage updateConsulting(int consultingId, ConsultingDto consultingDto);
}
