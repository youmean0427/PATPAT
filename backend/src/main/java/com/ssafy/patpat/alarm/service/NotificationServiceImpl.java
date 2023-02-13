package com.ssafy.patpat.alarm.service;

import com.ssafy.patpat.common.util.SecurityUtil;
import com.ssafy.patpat.protect.entity.ShelterProtectedDog;
import com.ssafy.patpat.protect.repository.ShelterProtectedDogRepository;
import com.ssafy.patpat.report.entity.MissingDog;
import com.ssafy.patpat.report.repository.MissingDogRepository;
import com.ssafy.patpat.report.service.ReportService;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.shelter.repository.ShelterRepository;
import com.ssafy.patpat.user.entity.User;
import com.ssafy.patpat.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

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
    UserRepository userRepository;

    @Autowired
    ReportService reportService;
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
                            sseEmitter.send(SseEmitter.event().name("addProtect").data("반경 15km 내 새로운 보호견이 등록되었습니다."));
                        }catch (Exception e){
                            sseEmitters.remove(userId);
                            e.printStackTrace();
                        }
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
                Long userId = s.getOwner().getUser().getUserId();

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

    public Long getUserId(){
        Optional<User> user = SecurityUtil.getCurrentEmail().flatMap(userRepository::findOneWithAuthoritiesByEmail);
        return user.get().getUserId();
    }

}
