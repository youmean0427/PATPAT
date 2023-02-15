package com.ssafy.patpat.alarm.service;

public interface NotificationService {
    void notifyAddProtectDogEvent(Long spDogId);
    void notifyAddMissingDogEvent(Long missingId);
    void notifyAddConsultingEvent(Long consultingId);
    void notifyAddVolunteerEvent(Long noticeId);
    void notifyAccessConsultingEvent(Long consultingId);
    void notifyDenyConsultingEvent(Long consultingId);
    void notifyAccessVolunteerEvent(Long reservationId);
    void notifyDenyVolunteerEvent(Long reservationId);
    void notifyCreateRoomEvent(Long consultingId);
    Long getUserId();
}
