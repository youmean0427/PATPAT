package com.ssafy.patpat.shelter.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.patpat.common.code.Breed;
import com.ssafy.patpat.shelter.dto.RequestShelterDto;
import com.ssafy.patpat.shelter.entity.Gugun;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.shelter.entity.ShelterDog;
import com.ssafy.patpat.shelter.entity.Sido;
import com.ssafy.patpat.shelter.repository.GugunRepository;
import com.ssafy.patpat.shelter.repository.ShelterDogRepository;
import com.ssafy.patpat.shelter.repository.ShelterRepository;
import com.ssafy.patpat.shelter.repository.SidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.*;

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
    @Autowired
    PasswordEncoder passwordEncoder;
    /**
     * 각종 조건들에 의한 보호소 리스트 조회
     */
    @Override
    public Breed randomBreed() {
        Breed[] arr = Breed.values();
        Random random = new Random();
        Breed breed = Breed.values()[random.nextInt(arr.length)];
        return breed;
    }
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
    public List<Shelter> shelterList(RequestShelterDto dto) {
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

    /**
     *보호소 등록, 수정, 상세
     */
//    @Override
//    public ResultInsertShelterDto insertShelter(RequestParamShelterInsertDto requestParamShelterInsertDto){
//        String shelterNm = requestParamShelterInsertDto.getShelterName();
//        String shelterCode = requestParamShelterInsertDto.getShelterCode();
//        ResultInsertShelterDto dto = new ResultInsertShelterDto();
//        try{
//            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1543061/animalShelterSrvc/shelterInfo");
//            urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=4YzbaAQ76Mr8ENklHJNGymdysODMSkne%2Bmi9616VcdzI4KuXMA7ugRh5rvN7HLAgjV1qetFWKEHGzR7XhH4mEA%3D%3D");
//            urlBuilder.append("&" + URLEncoder.encode("care_nm","UTF-8") + "=" + URLEncoder.encode(shelterNm, "UTF-8"));
//            urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
//            URL url = new URL(urlBuilder.toString());
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            conn.setRequestProperty("Content-type", "application/json");
//            System.out.println("Response code: " + conn.getResponseCode());
//            BufferedReader rd = null;
//            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//                rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
//            } else {
//                dto.setResult(0);
//            }
//            StringBuilder sb = new StringBuilder();
//            String line;
//            while ((line = rd.readLine()) != null) {
//                sb.append(line);
//            }
//            ObjectMapper objectMapper = new ObjectMapper();
//            Map<String, Object> map = new HashMap<>();
//            map = objectMapper.readValue(sb.toString(), new TypeReference<Map<String, Object>>() {});
//            Map<String, Object> response = (Map<String,Object>) map.get("response");
//            Map<String, Object> body = (Map<String,Object>) response.get("body");
//            Map<String, Object> items = (Map<String,Object>) body.get("items");
//            List<Map<String,Object>> itemList = (List<Map<String, Object>>) items.get("item");
//            List<Shelter> list = new ArrayList<>();
//            String str = null;
//            for(Map<String,Object> item:itemList){
//                Shelter s = Shelter.builder()
//                        .address(item.get("careAddr").toString())
//                        .latitude(item.get("lat").toString())
//                        .longitude(item.get("lng").toString())
//                        .phoneNum(item.get("careTel").toString())
//                        .name(item.get("careNm").toString()).build();
//                str = (item.get("jibunAddr").toString());
//                list.add(s);
//            }
//            String[] arr = str.split(" ");
//            System.out.println(Arrays.toString(arr));
//
//            Sido sido = sidoRepository.findByName(arr[0]);
//            list.get(0).setSidoCode(sido.getCode());
//            Gugun gugun = gugunRepository.findBySidoCodeAndName(sido.getCode(),arr[1]);
//            list.get(0).setGugunCode(gugun.getCode());
//            list.get(0).setRegNumber(LocalDateTime.now().toString());
//            Shelter shelter = shelterRepository.save(list.get(0));
//            rd.close();
//            conn.disconnect();
//            if(shelter.equals(null)){
//                dto.setResult(0);
//            }
//            else{
//                dto.setResult(1);
//                dto.setAuthCode(passwordEncoder.encode(list.get(0).getRegNumber()));
//            }
//        }catch (Exception e){
//            dto.setResult(0);
//        }
//        return dto;
//    }

    @Override
    public Optional<Shelter> detailShelter(int shelterId) {
        return shelterRepository.findById(shelterId);
    }


}
