package com.ssafy.patpat.alarm.service;

import com.ssafy.patpat.protect.entity.ShelterProtectedDog;
import com.ssafy.patpat.protect.repository.ShelterProtectedDogRepository;
import com.ssafy.patpat.report.entity.MissingDog;
import com.ssafy.patpat.report.repository.MissingDogRepository;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.shelter.repository.ShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.math.BigDecimal;
import java.util.List;

import static com.ssafy.patpat.alarm.controller.AlarmController.sseEmitters;

@Service
public class NotificationServiceImpl implements NotificationService{
    @Autowired
    ShelterProtectedDogRepository shelterProtectedDogRepository;
    @Autowired
    MissingDogRepository missingDogRepository;
    @Autowired
    ShelterRepository shelterRepository;

    public void notifyAddProtectDogEvent(Long spDogId){
        ShelterProtectedDog shelterProtectedDog = shelterProtectedDogRepository.findBySpDogId(spDogId);
        BigDecimal lat = shelterProtectedDog.getLatitude();
        BigDecimal log = shelterProtectedDog.getLongitude();

        List<MissingDog> missingDogList = missingDogRepository.selectBydistance(lat,log,lat);

        if(missingDogList.size() > 0){
            for(MissingDog m : missingDogList){
                Long userId = m.getUser().getUserId();

                if(sseEmitters.containsKey(userId)){
                    SseEmitter sseEmitter = sseEmitters.get(userId);
                    try{
                        sseEmitter.send(SseEmitter.event().name("addProtect").data("반경 15km 내 새로운 보호견이 등록되었습니다."));
                    }catch (Exception e){
                        sseEmitters.remove(userId);
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void notifyAddMissingDogEvent(Long missingId){
        MissingDog missingDog = missingDogRepository.findByMissingId(missingId);
        BigDecimal lat = missingDog.getLatitude();
        BigDecimal log = missingDog.getLongitude();

        List<Shelter> shelterList = shelterRepository.selectBydistance(lat,log,lat);

        if(shelterList.size() > 0){
            for(Shelter s : shelterList){
                Long userId = s.getOwner().getOwnerId();

                if(sseEmitters.containsKey(userId)){
                    SseEmitter sseEmitter = sseEmitters.get(userId);
                    try{
                        sseEmitter.send(SseEmitter.event().name("addMissing").data("반경 15km 내 실종견이 등록되었습니다."));
                    }catch (Exception e){
                        sseEmitters.remove(userId);
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
