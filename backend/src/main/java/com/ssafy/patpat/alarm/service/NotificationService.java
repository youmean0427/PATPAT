package com.ssafy.patpat.alarm.service;

public interface NotificationService {
    void notifyAddProtectDogEvent(Long spDogId);
    void notifyAddMissingDogEvent(Long missingId);
    Long getUserId();
}
