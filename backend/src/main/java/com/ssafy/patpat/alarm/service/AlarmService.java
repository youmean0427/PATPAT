package com.ssafy.patpat.alarm.service;

import com.ssafy.patpat.alarm.controller.AlarmController;
import com.ssafy.patpat.alarm.dto.AlarmDto;
import com.ssafy.patpat.alarm.dto.RequestAlarmDto;
import com.ssafy.patpat.alarm.entity.Alarm;
import com.ssafy.patpat.alarm.repository.AlarmRepository;
import com.ssafy.patpat.common.dto.ResponseListDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlarmService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmService.class);
    private final AlarmRepository alarmRepository;

    @Transactional
    public ResponseListDto selectAlarmList(RequestAlarmDto requestAlarmDto){
        PageRequest pageRequest = PageRequest.of(requestAlarmDto.getOffSet(), requestAlarmDto.getLimit(), Sort.by("registDate").descending());

        Page<Alarm> alarms =  alarmRepository.findByUserUserId(requestAlarmDto.getUserId(), pageRequest);
        List<AlarmDto> alarmDtoList =  alarms.toList().stream()
                .map(AlarmDto::new).collect(Collectors.toList());

        ResponseListDto responseListDto = new ResponseListDto();
        responseListDto.setList(alarmDtoList);
        responseListDto.setTotalPage(alarms.getTotalPages());
        responseListDto.setTotalCount(alarms.getTotalElements());

        return responseListDto;
    }
}
