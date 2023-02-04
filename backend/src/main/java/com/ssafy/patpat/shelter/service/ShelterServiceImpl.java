package com.ssafy.patpat.shelter.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.patpat.common.code.MBTI;
import com.ssafy.patpat.common.dto.FileDto;
import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.common.entity.Image;
import com.ssafy.patpat.common.repository.ImageRepository;
import com.ssafy.patpat.consulting.entity.Time;
import com.ssafy.patpat.consulting.repository.TimeRepository;
import com.ssafy.patpat.protect.entity.ShelterProtectedDog;
import com.ssafy.patpat.protect.repository.ShelterProtectedDogRepository;
import com.ssafy.patpat.shelter.dto.*;
import com.ssafy.patpat.shelter.entity.*;
import com.ssafy.patpat.shelter.repository.*;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    ShelterProtectedDogRepository shelterProtectedDogRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    BreedRepository breedRepository;
    @Autowired
    BreedImageRepository breedImageRepository;
    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ShelterImageRepository shelterImageRepository;

    @Autowired
    TimeRepository timeRepository;
    @Override
    public BreedDto selectBreedByMbti(String mbtiId) {
        int breedId = MBTI.valueOf(mbtiId).ordinal();
        Breed breed = breedRepository.findByBreedId(breedId);
        BreedImage breedImage = breedImageRepository.findByBreedId(breedId);
        BreedDto breedDto = null;
        if(breedImage != null){
            Image image =imageRepository.findByImageId(breedImage.getImageId());
            FileDto fileDto = FileDto.builder()
                    .filePath(image.getFilePath())
                    .build();

            breedDto = BreedDto.builder()
                    .breedId(breed.getBreedId())
                    .breedName(breed.getName())
                    .title(breed.getTitle())
                    .description(breed.getDescription())
                    .fileDto(fileDto)
                    .build();
        }else{
            breedDto = BreedDto.builder()
                    .breedId(breed.getBreedId())
                    .breedName(breed.getName())
                    .title(breed.getTitle())
                    .description(breed.getDescription())
                    .build();
        }

        return breedDto;
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
    public List<BreedDto> selectBreedList() {
        List<Breed> breedList = breedRepository.findAll();
        List<BreedDto> breedDtoList = new ArrayList<>();
        for(Breed b : breedList){
            breedDtoList.add(
                    BreedDto.builder()
                            .breedId(b.getBreedId())
                            .breedName(b.getName())
                            .build()
            );
        }
        return breedDtoList;
    }

    @Override
    public List<SidoCountDto> selectBreedCountByMbti(int breedId) {
        List<SidoCountDto> sidoCountDtoList = new ArrayList<>();
        List<Sido> sidoList = sidoRepository.findAll();
        for(Sido s : sidoList){
            int count = shelterProtectedDogRepository.countDistinctShelterIdBySidoCodeAndBreedId(s.getCode(),breedId);
            sidoCountDtoList.add(
                    SidoCountDto.builder()
                            .sidoCode(s.getCode())
                            .sidoName(s.getName())
                            .count(count)
                            .build()
            );
        }
        return sidoCountDtoList;
    }

    // 견종만
    // 시도코드
    // 시도코드, 견종 코드만 받아왔을 경우
    // 시도코드 , 구군코드
    // 시도코드 , 구군코드 , 견종
    @Override
    public List<ShelterDto> shelterList(RequestShelterDto dto) {
        int breedId = dto.getBreedId();
        String sidoCode = dto.getSidoCode();
        String gugunCode = dto.getGugunCode();
        int offSet = dto.getOffSet();
        int limit = dto.getLimit();

        PageRequest pageRequest = PageRequest.of(offSet,limit);

        List<Shelter> shelterList = null;
        List<Integer> integerList = new ArrayList<>();
        if(breedId==0 && sidoCode==null && gugunCode==null){
            Page<Shelter> pages = shelterRepository.findAll(pageRequest);
            shelterList = pages.toList();
        }
        else if(breedId>0 && sidoCode==null && gugunCode == null){
            //견종만
            List<ShelterProtectedDog> list = shelterProtectedDogRepository.findDistinctShelterIdByBreedId(breedId);
            for(ShelterProtectedDog s : list){
                integerList.add(s.getShelterId());
            }
            shelterList = shelterRepository.findByShelterIdIn(integerList,pageRequest);
        }
        else if(breedId==0 && sidoCode!=null && gugunCode == null){
            //시도만
            List<ShelterProtectedDog> list = shelterProtectedDogRepository.findDistinctShelterIdBySidoCode(sidoCode);
            for(ShelterProtectedDog s : list){
                integerList.add(s.getShelterId());
            }
            shelterList = shelterRepository.findByShelterIdIn(integerList,pageRequest);
        }
        else if(breedId==0 && sidoCode!=null && gugunCode != null){
            //시도, 구군만
            List<ShelterProtectedDog> list = shelterProtectedDogRepository.findDistinctShelterIdBySidoCodeAndGugunCode(sidoCode,gugunCode);
            for(ShelterProtectedDog s : list){
                integerList.add(s.getShelterId());
            }
            shelterList = shelterRepository.findByShelterIdIn(integerList,pageRequest);
        }
        else if(breedId>0 && sidoCode != null && gugunCode==null){
            //시도, 견종
            List<ShelterProtectedDog> list = shelterProtectedDogRepository.findDistinctShelterIdBySidoCodeAndBreedId(sidoCode,breedId);
            for(ShelterProtectedDog s : list){
                integerList.add(s.getShelterId());
            }
            shelterList = shelterRepository.findByShelterIdIn(integerList,pageRequest);
        }
        else{
            List<ShelterProtectedDog> list = shelterProtectedDogRepository.findDistinctShelterIdBySidoCodeAndGugunCodeAndBreedId(sidoCode,gugunCode,breedId);
            for(ShelterProtectedDog s : list){
                integerList.add(s.getShelterId());
            }
            shelterList = shelterRepository.findByShelterIdIn(integerList,pageRequest);
        }
        List<ShelterDto> shelterDtoList = new ArrayList<>();
        System.out.println(shelterList);
        for(Shelter s : shelterList){
            ShelterImage shelterImage = shelterImageRepository.findByShelterId(s.getShelterId());
            FileDto fileDto = FileDto.builder()
                    .filePath("noProfile")
                    .build();
            Image image = null;
            if(shelterImage != null){
                image = imageRepository.findByImageId(shelterImage.getImageId());
            }
            if(image != null){
                fileDto = FileDto.builder()
                        .filePath(image.getFilePath())
                        .build();
            }
            shelterDtoList.add(
                    ShelterDto.builder()
                            .address(s.getAddress())
                            .shelterId(s.getShelterId())
                            .name(s.getName())
                            .fileDto(fileDto)
                            .build()
            );
        }

        return shelterDtoList;
    }

    @Override
    public List<ShelterDto> shelterListInVolunteer(String gugunCode) {
        List<Shelter> shelterList = shelterRepository.findByGugunCode(gugunCode);
        List<ShelterDto> shelterDtoList = new ArrayList<>();
        for(Shelter s : shelterList){
            shelterDtoList.add(
                    ShelterDto.builder()
                            .name(s.getName())
                            .shelterId(s.getShelterId())
                            .address(s.getAddress())
                            .build()
            );
        }
        return shelterDtoList;
    }

    /**
     *보호소 등록, 수정, 상세
     */
    @Override
    public AuthCodeDto insertShelter(RequestParamShelterInsertDto requestParamShelterInsertDto){
        String shelterNm = requestParamShelterInsertDto.getShelterName();
        String shelterCode = requestParamShelterInsertDto.getShelterCode();
        AuthCodeDto dto = new AuthCodeDto();
        try{
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1543061/animalShelterSrvc/shelterInfo");
            urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=4YzbaAQ76Mr8ENklHJNGymdysODMSkne%2Bmi9616VcdzI4KuXMA7ugRh5rvN7HLAgjV1qetFWKEHGzR7XhH4mEA%3D%3D");
            urlBuilder.append("&" + URLEncoder.encode("care_nm","UTF-8") + "=" + URLEncoder.encode(shelterNm, "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode());
            BufferedReader rd = null;
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            } else {
                dto.setAuthCode("FAIL");
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> map = new HashMap<>();
            map = objectMapper.readValue(sb.toString(), new TypeReference<Map<String, Object>>() {});
            Map<String, Object> response = (Map<String,Object>) map.get("response");
            Map<String, Object> body = (Map<String,Object>) response.get("body");
            Map<String, Object> items = (Map<String,Object>) body.get("items");
            List<Map<String,Object>> itemList = (List<Map<String, Object>>) items.get("item");
            List<Shelter> list = new ArrayList<>();
            String str = null;
            for(Map<String,Object> item:itemList){
                Shelter s = Shelter.builder()
                        .address(item.get("careAddr").toString())
                        .latitude(item.get("lat").toString())
                        .longitude(item.get("lng").toString())
                        .phoneNum(item.get("careTel").toString())
                        .name(item.get("careNm").toString()).build();
                str = (item.get("jibunAddr").toString());
                list.add(s);
            }
            String[] arr = str.split(" ");
            System.out.println(Arrays.toString(arr));

            Sido sido = sidoRepository.findByName(arr[0]);
            list.get(0).setSidoCode(sido.getCode());
            Gugun gugun = gugunRepository.findBySidoCodeAndName(sido.getCode(),arr[1]);
            list.get(0).setGugunCode(gugun.getCode());
            list.get(0).setRegNumber(LocalDateTime.now().toString());
            Shelter shelter = shelterRepository.save(list.get(0));
            //보호소 시간 생성 (디폴트 올 트루)
            for(int i=0; i<4; i++){
                Time time = Time.builder()
                        .timeCode(i)
                        .state(0)
                        .build();
                timeRepository.save(time);
            }
            rd.close();
            conn.disconnect();
            if(shelter.equals(null)){
                dto.setAuthCode("FAIL");
            }
            else{
                dto.setAuthCode(passwordEncoder.encode(list.get(0).getRegNumber()));
            }
        }catch (Exception e){
            dto.setAuthCode("FAIL");
        }
        return dto;
    }
    @Override
    public ResponseMessage updateShelter(String shelterId, List<MultipartFile> uploadFile, ShelterDto shelterDto) {
        return null;
    }

    @Override
    public ResponseMessage AuthShelter(String authCode) {
        return null;
    }


    @Override
    public ShelterDto detailShelter(int shelterId) {
        Shelter s = shelterRepository.findByShelterId(shelterId);
        ShelterImage shelterImage = shelterImageRepository.findByShelterId(s.getShelterId());
        Image image = imageRepository.findByImageId(shelterImage.getImageId());
        FileDto fileDto = FileDto.builder()
                .filePath(image.getFilePath())
                .build();
        ShelterDto shelterDto = ShelterDto.builder()
                .shelterId(s.getShelterId())
                .name(s.getName())
                .phoneNum(s.getPhoneNum())
                .infoContent(s.getInfo())
                .address(s.getAddress())
                .fileDto(fileDto)
                .build();
        return shelterDto;
    }


}
