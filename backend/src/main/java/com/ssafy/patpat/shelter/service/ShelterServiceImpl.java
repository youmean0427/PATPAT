package com.ssafy.patpat.shelter.service;

import com.ssafy.patpat.shelter.dto.RequestShelterFindingDto;
import com.ssafy.patpat.shelter.dto.ShelterDto;
import com.ssafy.patpat.shelter.entity.Gugun;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.shelter.entity.Sido;
import com.ssafy.patpat.shelter.repository.GugunRepository;
import com.ssafy.patpat.shelter.repository.ShelterRepository;
import com.ssafy.patpat.shelter.repository.SidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShelterServiceImpl implements ShelterService{
    @Autowired
    ShelterRepository shelterRepository;
    @Autowired
    SidoRepository sidoRepository;
    @Autowired
    GugunRepository gugunRepository;

    @Override
    public List<Sido> sidoList() {
        return sidoRepository.findAll();
    }

    @Override
    public List<Gugun> gugunList(String sidoCode) {
        return gugunRepository.findBySidoCode(sidoCode);
    }

    @Override
    public long gugunCount(String gugunCode) {
        return 0;
    }

    @Override
    public List<ShelterDto> shelterList(RequestShelterFindingDto dto) {
        List<Shelter> list = shelterRepository.findAll();
        for(Shelter s:list){
            System.out.println(s);
        }
        return null;
    }

    @Override
    public ShelterDto shelterDetail(int shelterId) {
        return null;
    }
}
