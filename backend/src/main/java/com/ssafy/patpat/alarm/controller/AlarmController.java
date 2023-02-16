package com.ssafy.patpat.alarm.controller;

import com.ssafy.patpat.alarm.dto.AlarmDto;
import com.ssafy.patpat.alarm.dto.AlarmListDto;
import com.ssafy.patpat.alarm.dto.RequestAlarmDto;
import com.ssafy.patpat.alarm.entity.Alarm;
import com.ssafy.patpat.alarm.service.AlarmService;
import com.ssafy.patpat.alarm.service.NotificationService;
import com.ssafy.patpat.alarm.service.NotificationServiceImpl;
import com.ssafy.patpat.common.dto.ResponseListDto;
import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.protect.service.ProtectService;
import com.ssafy.patpat.report.service.ReportService;
import com.ssafy.patpat.user.dto.UserDto;
import com.ssafy.patpat.user.entity.User;
import com.ssafy.patpat.user.repository.UserRepository;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/alarm")
public class AlarmController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmController.class);
    public static Map<String, SseEmitter> sseEmitters = new ConcurrentHashMap<>();
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    NotificationService notificationService;

    private final AlarmService alarmService;

    @GetMapping
    @ApiOperation(value = "알림 리스트", notes = "알림 목록 조회")
//    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<Object> selectAlarmList(RequestAlarmDto requestAlarmDto){
        AlarmListDto responseListDto = alarmService.selectAlarmList(requestAlarmDto);

        return ResponseEntity.status(HttpStatus.OK).body(responseListDto);
    }

    @GetMapping("/{alarmId}")
    @ApiOperation(value = "알림 조회", notes = "알림 상세 조회")
//    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<Object> detailAlarm(@PathVariable Long alarmId){
        AlarmDto alarmDto = alarmService.detailAlarm(alarmId);

        return ResponseEntity.status(HttpStatus.OK).body(alarmDto);
    }

    @DeleteMapping("/{alarmId}")
    @ApiOperation(value = "알림 삭제", notes = "알림 삭제")
//    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<Object> deleteAlarm(@PathVariable Long alarmId){
        alarmService.deleteAlarm(alarmId);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("SUCCESS"));
    }

    @DeleteMapping("/all")
    @ApiOperation(value = "알림 전체 삭제", notes = "알림 전체 삭제")
//    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<Object> deleteAlarmAll(){
        alarmService.deleteAlarmAll();

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("SUCCESS"));
    }


    @GetMapping(value = "/sub/{userId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    @PreAuthorize("hasAnyRole('USER')")
    public SseEmitter subscribe(@PathVariable Long userId, HttpServletResponse response) {
        LOGGER.info("오나? {}",userId);
//        Long userId = notificationService.getUserId();

        response.addHeader("X-Accel-Buffering", "no");
        response.addHeader("Content-Type", "text/event-stream");
        response.setHeader("Connection", "keep-alive");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Transfer-Encoding","chunked");

        SseEmitter sseEmitter = new SseEmitter(null);
        Optional<User> user = userRepository.findById(userId);
        String id = user.get().getEmail() + "_" + System.currentTimeMillis();
        sseEmitters.put(id, sseEmitter);
        LOGGER.info("id는 이것 {}", id);
        sseEmitter.onCompletion(() -> {
            LOGGER.info("onCompletion sseEmitter {}",id);
            sseEmitters.remove(id);
        });
        sseEmitter.onTimeout(() -> {
            LOGGER.info("onTimeout sseEmitter {}",id);
            sseEmitters.remove(id);
        });
        sseEmitter.onError((e) -> {
            LOGGER.info("Error seeEmitter {}", id);
            sseEmitters.remove(id);
        });

        try {
            LOGGER.info("여긴 와주라 제발 {}", id);
            sseEmitter.send(SseEmitter.event().name("connect").data("Start Connection"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sseEmitter;
    }
}
