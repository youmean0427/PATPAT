package com.ssafy.patpat.shelter.service;

import com.ssafy.patpat.shelter.dto.RequestParamMbtiDto;
import com.ssafy.patpat.shelter.entity.Gugun;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.shelter.entity.Sido;

import java.util.List;

public interface ShelterService {
    List<Sido> sidoList();
    List<Gugun> gugunList(String sidoCode);
    List<Shelter> shelterList(RequestParamMbtiDto dto);
}
