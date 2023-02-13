package com.ssafy.patpat.alarm.controller;

import com.ssafy.patpat.alarm.service.NotificationService;
import com.ssafy.patpat.alarm.service.NotificationServiceImpl;
import com.ssafy.patpat.protect.service.ProtectService;
import com.ssafy.patpat.report.service.ReportService;
import com.ssafy.patpat.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Slf4j
@RestController("/api/alarm")
public class AlarmController {
    public static Map<Long, SseEmitter> sseEmitters = new ConcurrentHashMap<>();
    @Autowired
    UserService userService;

    @Autowired
    NotificationService notificationService;

    @CrossOrigin
    @GetMapping(value = "/sub", consumes = MediaType.ALL_VALUE)
    public SseEmitter subscribe(@RequestParam String token) {
        Long userId = notificationService.getUserId();

        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        try {
            sseEmitter.send(SseEmitter.event().name("connect"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        sseEmitters.put(userId, sseEmitter);
        sseEmitter.onCompletion(() -> sseEmitters.remove(userId));
        sseEmitter.onTimeout(() -> sseEmitters.remove(userId));
        sseEmitter.onError((e) -> sseEmitters.remove(userId));

        return sseEmitter;
    }
}
