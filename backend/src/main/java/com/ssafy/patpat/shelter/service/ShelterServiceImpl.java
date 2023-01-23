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
    public List<Shelter> shelterList(RequestParamMbtiDto dto) {
		Breed breed = Breed.valueOf(dto.getBreedName());
		int breedId = breed.ordinal();
        String sidoCode = dto.getSidoCode();
        String gugunCode = dto.getGugunCode();
        List<ShelterDog> list = shelterDogRepository.findByBreedIdAndSidoCodeAndGugunCode(breedId,sidoCode,gugunCode);
        HashSet<Integer> set = new HashSet<>();
        for(ShelterDog s : list){
            set.add(s.getShelterId());
        }
        return shelterRepository.findByShelterIdIn(set);
    }

}
