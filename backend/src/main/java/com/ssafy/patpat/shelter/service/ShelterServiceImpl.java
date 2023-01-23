package com.ssafy.patpat.shelter.service;

import com.ssafy.patpat.shelter.Breed;
import com.ssafy.patpat.shelter.dto.RequestParamMbtiDto;
import com.ssafy.patpat.shelter.entity.Gugun;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.shelter.entity.ShelterDog;
import com.ssafy.patpat.shelter.entity.Sido;
import com.ssafy.patpat.shelter.repository.GugunRepository;
import com.ssafy.patpat.shelter.repository.ShelterDogRepository;
import com.ssafy.patpat.shelter.repository.ShelterRepository;
import com.ssafy.patpat.shelter.repository.SidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class ShelterServiceImpl implements ShelterService{
    @Autowired
    ShelterRepository shelterRepository;
    @Autowired
    SidoRepository sidoRepository;
    @Autowired
    GugunRepository gugunRepository;

    @Autowired
    ShelterDogRepository shelterDogRepository;

    @Override
    public List<Sido> sidoList() {
        return sidoRepository.findAll();
    }

    @Override
    public List<Gugun> gugunList(String sidoCode) {
        return gugunRepository.findBySidoCode(sidoCode);
    }
    @Override
    public List<Breed> breedListBasedSidoCode(String sidoCode){
        List<ShelterDog> list = shelterDogRepository.findBySidoCode(sidoCode);
        HashSet<Integer> set = new HashSet<>();
        for(ShelterDog s : list){
            set.add(s.getBreedId());
        }
        List<Breed> breedList = new ArrayList<>();
        for(Integer i : set){
            breedList.add(Breed.values()[i]);
        }
        return breedList;
    }
    @Override
    public List<Breed> breedListBasedSidoCodeAndGugunCode(String sidoCode, String gugunCode){
        List<ShelterDog> list = shelterDogRepository.findBySidoCodeAndGugunCode(sidoCode,gugunCode);
        HashSet<Integer> set = new HashSet<>();
        for(ShelterDog s : list){
            set.add(s.getBreedId());
        }
        List<Breed> breedList = new ArrayList<>();
        for(Integer i : set){
            breedList.add(Breed.values()[i]);
        }
        return breedList;
    }

    @Override
    public List<Shelter> shelterList(RequestParamMbtiDto dto) {
		Breed breed = Breed.valueOf(dto.getBreedName());
		int breedId = breed.ordinal();
        String sidoCode = dto.getSidoCode();
        String gugunCode = dto.getGugunCode();

        //시도코드, 견종 코드만 받아왔을 경우
        if(gugunCode.equals("")){
            List<ShelterDog> list = shelterDogRepository.findByBreedIdAndSidoCode(breedId,sidoCode);
            HashSet<Integer> set = new HashSet<>();
            for(ShelterDog s : list){
                set.add(s.getShelterId());
            }
            return shelterRepository.findByShelterIdIn(set);
        }
        //시도코드, 구군코드, 견종 모두 받아왔을 경우
        else{
            List<ShelterDog> list = shelterDogRepository.findByBreedIdAndSidoCodeAndGugunCode(breedId,sidoCode,gugunCode);
            HashSet<Integer> set = new HashSet<>();
            for(ShelterDog s : list){
                set.add(s.getShelterId());
            }
            return shelterRepository.findByShelterIdIn(set);
        }
    }

    @Override
    public List<Shelter> shelterListInVolunteer(String sidoCode, String gugunCode) {
        return shelterRepository.findBySidoCodeAndGugunCode(sidoCode,gugunCode);
    }

}
