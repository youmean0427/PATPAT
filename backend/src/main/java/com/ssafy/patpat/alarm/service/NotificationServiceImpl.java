package com.ssafy.patpat.alarm.service;

import com.ssafy.patpat.common.code.MsgCode;
import com.ssafy.patpat.common.code.Reservation;
import com.ssafy.patpat.common.util.SecurityUtil;
import com.ssafy.patpat.consulting.entity.Consulting;
import com.ssafy.patpat.consulting.repository.ConsultingRepository;
import com.ssafy.patpat.protect.entity.ShelterProtectedDog;
import com.ssafy.patpat.protect.repository.ShelterProtectedDogRepository;
import com.ssafy.patpat.report.entity.MissingDog;
import com.ssafy.patpat.report.repository.MissingDogRepository;
import com.ssafy.patpat.report.service.ReportService;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.shelter.repository.ShelterRepository;
import com.ssafy.patpat.user.entity.User;
import com.ssafy.patpat.user.repository.UserRepository;
import com.ssafy.patpat.volunteer.entity.VolunteerNotice;
import com.ssafy.patpat.volunteer.entity.VolunteerReservation;
import com.ssafy.patpat.volunteer.repository.VolunteerNoticeRepository;
import com.ssafy.patpat.volunteer.repository.VolunteerReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.ssafy.patpat.alarm.controller.AlarmController.sseEmitters;

@Service
public class NotificationServiceImpl implements NotificationService{
    @Autowired
    ShelterProtectedDogRepository shelterProtectedDogRepository;
    @Autowired
    MissingDogRepository missingDogRepository;
    @Autowired
    ShelterRepository shelterRepository;
    @Autowired
    ConsultingRepository consultingRepository;
    @Autowired
    VolunteerNoticeRepository volunteerNoticeRepository;
    @Autowired
    VolunteerReservationRepository volunteerReservationRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    ReportService reportService;
    @Transactional
    public void notifyAddProtectDogEvent(Long spDogId){
        ShelterProtectedDog shelterProtectedDog = shelterProtectedDogRepository.findBySpDogId(spDogId);
        BigDecimal lat = shelterProtectedDog.getLatitude();
        BigDecimal log = shelterProtectedDog.getLongitude();

        List<MissingDog> missingDogList = missingDogRepository.selectBydistance(lat,log,lat);

        /** 반경 내에 실종된 동물이 있다면 **/
        if(missingDogList.size() > 0){
            for(MissingDog m : missingDogList){
                /** 반견 내에 있는 실종견이 등록견과 닮아있다면 **/
                if(reportService.isResemble(m,shelterProtectedDog)){
                    Long userId = m.getUser().getUserId();
                    /** 실종견 주인이 현재 구독중이라면 **/
                    if(sseEmitters.containsKey(userId)){
                        SseEmitter sseEmitter = sseEmitters.get(userId);
                        try{
                            MsgCode msgCode = MsgCode.MSG_NEW_RESEMBLE_DOG;
                            sseEmitter.send(SseEmitter.event().name("addProtect").data(msgCode, MediaType.APPLICATION_JSON));
                        }catch (Exception e){
                            sseEmitters.remove(userId);
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    @Transactional
    public void notifyAddMissingDogEvent(Long missingId){
        MissingDog missingDog = missingDogRepository.findByMissingId(missingId);
        BigDecimal lat = missingDog.getLatitude();
        BigDecimal log = missingDog.getLongitude();

        List<Shelter> shelterList = shelterRepository.selectBydistance(lat,log,lat);

        if(shelterList.size() > 0){
            for(Shelter s : shelterList){
                Long userId = s.getOwner().getUser().getUserId();

                if(sseEmitters.containsKey(userId)){
                    SseEmitter sseEmitter = sseEmitters.get(userId);
                    try{
                        MsgCode msgCode = MsgCode.MSG_NEW_MISSING;
                        sseEmitter.send(SseEmitter.event().name("addMissing").data(msgCode,MediaType.APPLICATION_JSON));
                    }catch (Exception e){
                        sseEmitters.remove(userId);
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    @Transactional
    public void notifyAddConsultingEvent(Long consultingId) {
        Consulting consulting = consultingRepository.findByConsultingId(consultingId);
        Shelter shelter = consulting.getShelter();
        Long userId = shelter.getOwner().getUser().getUserId();
        if(sseEmitters.containsKey(userId)){
            SseEmitter sseEmitter = sseEmitters.get(userId);
            try{
                MsgCode msgCode = MsgCode.MSG_NEW_CONSULTING;
                sseEmitter.send(SseEmitter.event().name("addConsulting").data(msgCode,MediaType.APPLICATION_JSON));
            }catch (Exception e){
                sseEmitters.remove(userId);
                e.printStackTrace();
            }
        }
    }

    @Override
    @Transactional
    public void notifyAddVolunteerEvent(Long noticeId) {
        VolunteerNotice notice = volunteerNoticeRepository.findByNoticeId(noticeId);
        Long userId = notice.getShelter().getOwner().getUser().getUserId();
        if(sseEmitters.containsKey(userId)){
            SseEmitter sseEmitter = sseEmitters.get(userId);
            try{
                MsgCode msgCode = MsgCode.MSG_NEW_VOLUNTEER;
                sseEmitter.send(SseEmitter.event().name("addVolunteer").data(msgCode,MediaType.APPLICATION_JSON));
            }catch (Exception e){
                sseEmitters.remove(userId);
                e.printStackTrace();
            }
        }
    }

    @Override
    @Transactional
    public void notifyAccessConsultingEvent(Long consultingId) {
        Consulting consulting = consultingRepository.findByConsultingId(consultingId);
        Long userId = consulting.getUser().getUserId();
        if(sseEmitters.containsKey(userId)){
            SseEmitter sseEmitter = sseEmitters.get(userId);
            try{
                MsgCode msgCode = MsgCode.MSG_ACCESS_CONSULTING;
                sseEmitter.send(SseEmitter.event().name("accessConsulting").data(msgCode,MediaType.APPLICATION_JSON));
            }catch (Exception e){
                sseEmitters.remove(userId);
                e.printStackTrace();
            }
        }
    }

    @Override
    @Transactional
    public void notifyDenyConsultingEvent(Long consultingId) {
        Consulting consulting = consultingRepository.findByConsultingId(consultingId);
        Long userId = consulting.getUser().getUserId();
        if(sseEmitters.containsKey(userId)){
            SseEmitter sseEmitter = sseEmitters.get(userId);
            try{
                MsgCode msgCode = MsgCode.MSG_ACCESS_CONSULTING;
                sseEmitter.send(SseEmitter.event().name("denyConsulting").data(msgCode,MediaType.APPLICATION_JSON));
            }catch (Exception e){
                sseEmitters.remove(userId);
                e.printStackTrace();
            }
        }
    }

    @Override
    @Transactional
    public void notifyAccessVolunteerEvent(Long reservationId) {
        VolunteerReservation volunteerReservation = volunteerReservationRepository.findByReservationId(reservationId);
        Long userId = volunteerReservation.getUser().getUserId();
        if(sseEmitters.containsKey(userId)){
            SseEmitter sseEmitter = sseEmitters.get(userId);
            try{
                MsgCode msgCode = MsgCode.MSG_ACCESS_VOLUNTEER;
                sseEmitter.send(SseEmitter.event().name("accessVolunteer").data(msgCode,MediaType.APPLICATION_JSON));
            }catch (Exception e){
                sseEmitters.remove(userId);
                e.printStackTrace();
            }
        }
    }

    @Override
    @Transactional
    public void notifyDenyVolunteerEvent(Long reservationId) {
        VolunteerReservation volunteerReservation = volunteerReservationRepository.findByReservationId(reservationId);
        Long userId = volunteerReservation.getUser().getUserId();
        if(sseEmitters.containsKey(userId)){
            SseEmitter sseEmitter = sseEmitters.get(userId);
            try{
                MsgCode msgCode = MsgCode.MSG_ACCESS_VOLUNTEER;
                sseEmitter.send(SseEmitter.event().name("denyVolunteer").data(msgCode,MediaType.APPLICATION_JSON));
            }catch (Exception e){
                sseEmitters.remove(userId);
                e.printStackTrace();
            }
        }
    }

    @Override
    @Transactional
    public void notifyCreateRoomEvent(Long consultingId) {
        Consulting consulting = consultingRepository.findByConsultingId(consultingId);
        Long userId = consulting.getUser().getUserId();
        if(sseEmitters.containsKey(userId)){
            SseEmitter sseEmitter = sseEmitters.get(userId);
            try{
                MsgCode msgCode = MsgCode.MSG_CREATE_ROOM;
                sseEmitter.send(SseEmitter.event().name("createRoom").data(msgCode,MediaType.APPLICATION_JSON));
            }catch (Exception e){
                sseEmitters.remove(userId);
                e.printStackTrace();
            }
        }
    }


    public Long getUserId(){
        Optional<User> user = SecurityUtil.getCurrentEmail().flatMap(userRepository::findOneWithAuthoritiesByEmail);
        return user.get().getUserId();
    }

}
