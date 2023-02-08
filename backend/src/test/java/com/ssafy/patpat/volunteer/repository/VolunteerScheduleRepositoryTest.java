package com.ssafy.patpat.volunteer.repository;

import com.ssafy.patpat.common.code.Reservation;
import com.ssafy.patpat.common.code.TimeCode;
import com.ssafy.patpat.consulting.entity.Time;
import com.ssafy.patpat.consulting.repository.TimeRepository;
import com.ssafy.patpat.protect.entity.ShelterProtectedDog;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.shelter.repository.ShelterRepository;
import com.ssafy.patpat.user.entity.User;
import com.ssafy.patpat.user.repository.UserRepository;
import com.ssafy.patpat.volunteer.dto.VolunteerShelterDto;
import com.ssafy.patpat.volunteer.entity.VolunteerNotice;
import com.ssafy.patpat.volunteer.entity.VolunteerReservation;
import com.ssafy.patpat.volunteer.entity.VolunteerSchedule;
import com.ssafy.patpat.volunteer.mapping.VolunteerShelterIdMapping;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VolunteerScheduleRepositoryTest {

    @Autowired
    private VolunteerScheduleRepository volunteerScheduleRepository;

    @Autowired
    private VolunteerNoticeRepository volunteerNoticeRepository;

    @Autowired
    private VolunteerReservationRepository volunteerReservationRepository;

    @Autowired
    private ShelterRepository shelterRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TimeRepository timeRepository;

    @Test
    @Transactional
    void find(){

//
//        List<Integer> ids = new ArrayList<>();
//        ids.add(1);
//        ids.add(2);
//        ids.add(3);
//        ids.add(4);

//        List<VolunteerShelterIdMapping> vl = volunteerScheduleRepository.findShelter(0, "11110");
//// 2 3 1 4 2
//        for (VolunteerShelterIdMapping v:
//             vl) {
//            System.out.println(v.getShelterId());
//            System.out.println(v.getVolunteerDate());
//            System.out.println(v.getName());
//        }
//        PageRequest pageRequest = PageRequest.of(0,4, Sort.by("volunteerDate").ascending());
//        List<VolunteerNotice> list = volunteerNoticeRepository.findWithShelterByShelterShelterIdAndVolunteerDateLikeOrderByVolunteerDateAsc(2,"2023-02%");
//
//        for (VolunteerNotice v:
//             list) {
//            System.out.println(v.getNoticeId());
//        }

//        PageRequest pageRequest = PageRequest.of(0,3);
//        Page<VolunteerNotice> vn = volunteerNoticeRepository.findWithShelterByShelterGugunCodeAndReservationStateCodeAndVolunteerDateGreaterThanOrderByVolunteerDate("11110", Reservation.대기중, LocalDate.now().toString(), pageRequest);
//        PageRequest pageRequest = PageRequest.of(0,3, Sort.by("volunteerDate").ascending());
//        Page<VolunteerReservation> vr = volunteerReservationRepository.findWithUserByUserUserIdAndReservationStateCode(5L, pageRequest);
//        System.out.println(vr.getTotalPages());
//        System.out.println(vr.getTotalElements());
//        for (VolunteerReservation v:
//                vr.toList()) {
//            System.out.println(v.getVolunteerDate() +" "+ v.getReservationId());
//        }
        List<Integer> t = new ArrayList<>();
        t.add(2);
        t.add(3);
        int hour = LocalDateTime.now().getHour();
        List<Time> time = timeRepository.findWithShelterByShelterShelterIdAndTimeCodeGreaterThanEqualAndStateNotIn(5, hour, t);

        for (Time ti:
             time) {
            System.out.println(ti.getTimeCode());
        }

//        Optional<User> user = userRepository.findWithFavoriteDogsByUserId(3L);
//        List<ShelterProtectedDog> list = user.get().getFavoriteDogs();
//        for (ShelterProtectedDog d:
//                list) {
//            System.out.println(d.getBreedId());
//        }
    }

}