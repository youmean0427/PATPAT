package com.ssafy.patpat.alarm.controller;

import com.ssafy.patpat.alarm.dto.AlarmDto;
import com.ssafy.patpat.alarm.dto.RequestAlarmDto;
import com.ssafy.patpat.alarm.entity.Alarm;
import com.ssafy.patpat.alarm.service.AlarmService;
import com.ssafy.patpat.alarm.service.NotificationService;
import com.ssafy.patpat.alarm.service.NotificationServiceImpl;
import com.ssafy.patpat.common.dto.ResponseListDto;
import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.protect.service.ProtectService;
import com.ssafy.patpat.report.service.ReportService;
import com.ssafy.patpat.user.service.UserService;
import com.ssafy.patpat.volunteer.service.VolunteerService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/alarm")
public class AlarmController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmController.class);
    public static Map<Long, SseEmitter> sseEmitters = new ConcurrentHashMap<>();
    @Autowired
    UserService userService;

    @Autowired
    NotificationService notificationService;

    private final AlarmService alarmService;

    @GetMapping
    @ApiOperation(value = "알림 리스트", notes = "알림 목록 조회")
    public ResponseEntity<Object> selectAlarmList(RequestAlarmDto requestAlarmDto){
        ResponseListDto responseListDto = alarmService.selectAlarmList(requestAlarmDto);

        return ResponseEntity.status(HttpStatus.OK).body(responseListDto);
    }

    @GetMapping("/{alarmId}")
    @ApiOperation(value = "알림 조회", notes = "알림 상세 조회")
    public ResponseEntity<Object> detailAlarm(@PathVariable Long alarmId){
        AlarmDto alarmDto = alarmService.detailAlarm(alarmId);

        return ResponseEntity.status(HttpStatus.OK).body(alarmDto);
    }

    @DeleteMapping("/{alarmId}")
    @ApiOperation(value = "알림 삭제", notes = "알림 삭제")
    public ResponseEntity<Object> deleteAlarm(@PathVariable Long alarmId){
        alarmService.deleteAlarm(alarmId);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("SUCCESS"));
    }

    @DeleteMapping("/all")
    @ApiOperation(value = "알림 전체 삭제", notes = "알림 전체 삭제")
    public ResponseEntity<Object> deleteAlarmAll(){
        alarmService.deleteAlarmAll();

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("SUCCESS"));
    }


    @GetMapping(value = "/sub", consumes = MediaType.ALL_VALUE)
    public SseEmitter subscribe(@RequestParam Long userId) {
        LOGGER.info("오나? {}",userId);
//        Long userId = notificationService.getUserId();

        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        sseEmitter.onCompletion(() -> sseEmitters.remove(userId));
        sseEmitter.onTimeout(() -> sseEmitters.remove(userId));
        sseEmitter.onError((e) -> sseEmitters.remove(userId));
        sseEmitters.put(userId, sseEmitter);
        try {
            AlarmDto alarmDto = new AlarmDto();
            alarmDto.setAlarmId(1L);
            alarmDto.setMissingId(2L);
            sseEmitter.send(SseEmitter.event().name("connect").data(alarmDto,MediaType.APPLICATION_JSON));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sseEmitter;
    }
}
