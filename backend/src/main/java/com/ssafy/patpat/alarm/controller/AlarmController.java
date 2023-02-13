package com.ssafy.patpat.alarm.controller;

import com.ssafy.patpat.alarm.service.NotificationService;
import com.ssafy.patpat.alarm.service.NotificationServiceImpl;
import com.ssafy.patpat.protect.service.ProtectService;
import com.ssafy.patpat.report.service.ReportService;
import com.ssafy.patpat.user.service.UserService;
import com.ssafy.patpat.volunteer.service.VolunteerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
            sseEmitter.send(SseEmitter.event().name("connect").data("ok good you good"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sseEmitter;
    }
}
