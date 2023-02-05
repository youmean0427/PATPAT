package com.ssafy.patpat.volunteer.repository;

import com.ssafy.patpat.common.code.Reservation;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.shelter.repository.ShelterRepository;
import com.ssafy.patpat.volunteer.dto.VolunteerShelterDto;
import com.ssafy.patpat.volunteer.entity.VolunteerSchedule;
import com.ssafy.patpat.volunteer.mapping.VolunteerShelterIdMapping;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VolunteerScheduleRepositoryTest {

    @Autowired
    private VolunteerScheduleRepository volunteerScheduleRepository;

    @Autowired
    private ShelterRepository shelterRepository;

    @Test
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

        List<VolunteerSchedule> list = volunteerScheduleRepository.findWithShelterByVolunteerDateAndShelterShelterIdOrderByStartTimeAsc("2023-02-06", 2);
        for (VolunteerSchedule v:
             list) {
            System.out.println(v.getVolunteerId());
        }
    }

}