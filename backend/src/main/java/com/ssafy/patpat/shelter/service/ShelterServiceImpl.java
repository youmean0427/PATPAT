package com.ssafy.patpat.shelter.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.patpat.common.code.MBTI;
import com.ssafy.patpat.common.code.TimeCode;
import com.ssafy.patpat.common.dto.FileDto;
import com.ssafy.patpat.common.dto.ResponseListDto;
import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.common.entity.Image;
import com.ssafy.patpat.common.repository.ImageRepository;
import com.ssafy.patpat.common.service.FileService;
import com.ssafy.patpat.common.util.SecurityUtil;
import com.ssafy.patpat.consulting.entity.Time;
import com.ssafy.patpat.consulting.repository.TimeRepository;
import com.ssafy.patpat.protect.entity.ShelterProtectedDog;

import com.ssafy.patpat.protect.mapping.ShelterIdMapping;
import com.ssafy.patpat.protect.repository.ShelterProtectedDogRepository;
import com.ssafy.patpat.shelter.dto.*;
import com.ssafy.patpat.shelter.entity.*;
import com.ssafy.patpat.shelter.mapping.ShelterNameMapping;
import com.ssafy.patpat.shelter.repository.*;
import com.ssafy.patpat.user.dto.UserDto;
import com.ssafy.patpat.user.entity.Authority;
import com.ssafy.patpat.user.entity.Owner;
import com.ssafy.patpat.user.entity.User;
import com.ssafy.patpat.user.repository.OwnerRepository;
import com.ssafy.patpat.user.repository.UserRepository;
import com.ssafy.patpat.user.service.UserService;

import org.apache.commons.collections.ArrayStack;
import org.graalvm.compiler.nodes.calc.IntegerDivRemNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShelterServiceImpl implements ShelterService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ShelterServiceImpl.class);
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
//    @Autowired
//    BreedImageRepository breedImageRepository;
    @Autowired
    ImageRepository imageRepository;

//    @Autowired
//    ShelterImageRepository shelterImageRepository;

    @Autowired
    TimeRepository timeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OwnerRepository ownerRepository;
    @Autowired
    FileService fileService;

    @Autowired
    UserService userService;
    @Override
    public BreedDto selectBreedByMbti(String mbtiId) {
        long breedId = MBTI.valueOf(mbtiId).getCode();
        Breed breed = breedRepository.findByBreedId(breedId);
        Image breedImage = breed.getImage();
//        BreedImage breedImage = breedImageRepository.findByBreedId(breedId);
        BreedDto breedDto = null;
        if(breedImage != null){
//            Image image =imageRepository.findByImageId(breedImage.getImageId());
//            FileDto fileDto = FileDto.builder()
//                    .filePath()
//                    .build();

            breedDto = BreedDto.builder()
                    .breedId(breed.getBreedId())
                    .breedName(breed.getName())
                    .title(breed.getTitle())
                    .description(breed.getDescription())
                    .thumbnail(fileService.getFileUrl(breedImage))
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
        breedDtoList.add(BreedDto.builder()
                        .breedId(0L)
                        .breedName("견종")
                .build());
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
    public MbtiMapDto selectBreedCountByMbti(Long breedId) {
        MbtiMapDto mbtiMapDto = new MbtiMapDto();
        List<SidoCountDto> sidoCountDtoList = new ArrayList<>();
        List<Sido> sidoList = sidoRepository.findAll();
        int total = 0;
        for(Sido s : sidoList){
            List<ShelterIdMapping> count = shelterProtectedDogRepository.findDistinctByShelterSidoCodeAndBreedBreedId(s.getCode(),breedId);
            total += count.size();
            sidoCountDtoList.add(
                    SidoCountDto.builder()
                            .sidoCode(s.getCode())
                            .sidoName(s.getName())
                            .count(count.size())
                            .build()
            );
        }

        mbtiMapDto.setTotalCount(total);
        mbtiMapDto.setList(sidoCountDtoList);
        return mbtiMapDto;

    }

    // 견종만
    // 시도코드
    // 시도코드, 견종 코드만 받아왔을 경우
    // 시도코드 , 구군코드
    // 시도코드 , 구군코드 , 견종
    @Override
    public ResponseListDto shelterList(RequestShelterDto dto) {
        ResponseListDto responseListDto = new ResponseListDto();
        Long breedId = dto.getBreedId();
        String sidoCode = dto.getSidoCode();
        String gugunCode = dto.getGugunCode();
        Integer offSet = dto.getOffSet();
        Integer limit = dto.getLimit();

        PageRequest pageRequest = PageRequest.of(offSet,limit);

        Page<Shelter> shelterList = null;
        List<Long> longList = new ArrayList<>();
        if(breedId==null && sidoCode==null && gugunCode==null){
            shelterList = shelterRepository.findAll(pageRequest);
//            shelterList = pages.toList();
        }
        else if(breedId!=null && sidoCode==null && gugunCode == null){
            //견종만
            List<ShelterIdMapping> list = shelterProtectedDogRepository.findDistinctByBreedBreedId(breedId);
            for(ShelterIdMapping s : list){
                longList.add(s.getShelter().getShelterId());
            }
            shelterList = shelterRepository.findByShelterIdIn(longList,pageRequest);
        }
        else if(breedId==null && sidoCode!=null && gugunCode == null){
            //시도만
            shelterList = shelterRepository.findBySidoCode(sidoCode,pageRequest);
        }
        else if(breedId==null && sidoCode!=null && gugunCode != null){
            shelterList = shelterRepository.findBySidoCodeAndGugunCode(sidoCode,gugunCode,pageRequest);
        }
        else if(breedId!=null && sidoCode != null && gugunCode==null){
            //시도, 견종
            List<ShelterIdMapping> list = shelterProtectedDogRepository.findDistinctByShelterSidoCodeAndBreedBreedId(sidoCode,breedId);
            for(ShelterIdMapping s : list){
                longList.add(s.getShelter().getShelterId());
            }
            shelterList = shelterRepository.findByShelterIdIn(longList,pageRequest);
        }
        else{
            List<ShelterIdMapping> list = shelterProtectedDogRepository.findDistinctByShelterSidoCodeAndShelterGugunCodeAndBreedBreedId(sidoCode,gugunCode,breedId);
            for(ShelterIdMapping s : list){
                longList.add(s.getShelter().getShelterId());
            }
            shelterList = shelterRepository.findByShelterIdIn(longList,pageRequest);
        }
        List<ShelterDto> shelterDtoList = new ArrayList<>();
//        System.out.println(shelterList);
        List<Shelter> shelters = shelterList.toList().stream()
                .filter( s -> Optional.ofNullable(s.getOwner()).isPresent()).collect(Collectors.toList());
        for(Shelter s : shelters){
            List<Image> shelterImage = s.getImages();
            List<FileDto> imageList = new ArrayList<>();
            for(Image i : shelterImage){
                imageList.add(FileDto.builder()
                        .id(i.getImageId())
                        .origFilename(i.getOrigFilename())
                        .filename(i.getFilename())
                        .filePath(fileService.getFileUrl(i))
                        .build());
            }
            shelterDtoList.add(
                    ShelterDto.builder()
                            .address(s.getAddress())
                            .shelterId(s.getShelterId())
                            .name(s.getName())
                            .sidoCode(s.getSidoCode())
                            .gugunCode(s.getGugunCode())
                            .imageList(imageList)
                            .build()
            );
        }

        responseListDto.setList(shelterDtoList);
        responseListDto.setTotalPage(shelterList.getTotalPages());
        responseListDto.setTotalCount(shelterList.getTotalElements());
        return responseListDto;
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
        LOGGER.info("이고이노{}",shelterNm);
        AuthCodeDto dto = new AuthCodeDto();
        try{
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
//                dto.setAuthCode("FAIL");
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
            //보호소 시간 생성 (디폴트 올 트루)
//            for(int i=0; i<4; i++){
//                Time time = Time.builder()
//                        .timeCode(i)
//                        .state(0)
//                        .build();
//                timeRepository.save(time);
//            }
//            rd.close();
//            conn.disconnect();
            Shelter shelter = shelterRepository.findByNameAndRegNumber(shelterNm,shelterCode);

            if(shelter==null){
                dto.setAuthCode("FAIL");
                return dto;
            }
            else{
                String auth = passwordEncoder.encode(shelter.getRegNumber());
                dto.setAuthCode(auth);
                shelter.setAuthCode(auth);
//                System.out.println(dto);
                Optional<Owner> o = Optional.ofNullable(shelter.getOwner());
                if(!o.isPresent()){ // owner가 없다면
                    Optional<User> user = SecurityUtil.getCurrentEmail().flatMap(userRepository::findOneWithAuthoritiesByEmail);
                    user.get().setShelter(shelter);
                    Owner owner = new Owner();
                    owner.setName(user.get().getNickname());
                    owner.setUser(user.get());
                    shelter.setOwner(owner);

                    shelter = shelterRepository.save(shelter);

                    List<Time> timeList = new ArrayList<>();
                    for(TimeCode t : TimeCode.values()){
                        timeList.add(
                                Time.builder()
                                        .timeCode(t)
                                        .active(true)
                                        .shelter(shelter)
                                        .build()
                        );
                    }
                    timeRepository.saveAll(timeList);

                    userRepository.save(user.get());
                    dto.setShelterId(shelter.getShelterId());
                }
                else{
                    dto.setAuthCode("이미 등록된 보호소입니다.");
                    return dto;
                }

            }
        }catch (Exception e){
            e.printStackTrace();
            dto.setAuthCode("FAIL");
        }
        return dto;
    }

    @Override
    @Transactional
    public ResponseMessage updateShelter(ShelterDto shelterDto, List<MultipartFile> uploadFile) throws Exception {
        LOGGER.info("{}",shelterDto.getShelterId());

        Optional<Shelter> s =Optional.ofNullable(shelterRepository.findByShelterId(shelterDto.getShelterId()));
        if(!s.isPresent()){
            return new ResponseMessage("FAIL");
        }

        Shelter shelter = s.get();
        LOGGER.info("sdfsdfsdf{}",shelter.getOwner().getName());
        Optional<Owner> owner = Optional.ofNullable(shelter.getOwner());
        if(!owner.isPresent()){
            return new ResponseMessage("FAIL");
        }

        LOGGER.info("나의 오우너{}",owner.get().getName());
        // 이미지 우선 삭제



        if(uploadFile != null){
            List<Image> shelterImageList = shelter.getImages();
            for (Image i:
                    shelterImageList) {
                fileService.deleteFile(i);
            }
            shelterImageList.removeAll(shelterImageList);
            for (MultipartFile partFile:
                    uploadFile) {
                shelterImageList.add(fileService.insertFile(partFile, "shelter"));
                shelter.setImages(shelterImageList);
            }
        }

        shelter.setInfo(shelterDto.getInfoContent());
        shelter.setName(shelterDto.getName());

        owner.get().setPhoneNumber(shelterDto.getPhoneNumber());
        owner.get().setName(shelterDto.getOwnerName());

        shelter.setOwner(owner.get());

//        if(shelterDto.getName() != null){
//            shelter.setName(shelterDto.getName());
//        }
//        if(shelterDto.getInfoContent() != null){
//            shelter.setInfo(shelterDto.getInfoContent());
//        }
//        if(shelterDto.getPhoneNumber() != null){
//            shelter.getOwner().setPhoneNumber(shelterDto.getPhoneNumber());
//        }
//        if(shelterDto.getOwnerName() != null){
//            shelter.getOwner().setName(shelterDto.getOwnerName());
//        }
//        if(shelterDto.getOwnerId() != null){
//            // 직원인지 체크
//            Optional<User> user = userRepository.findById(shelterDto.getOwnerId());
//            if(user.isPresent()){
//                if(user.get().getShelter().getShelterId() == shelter.getShelterId()){
//                    Owner newOwner = new Owner();
//                    newOwner.setUser(user.get());
//                    newOwner.setName(shelterDto.getName());
//                    newOwner.setPhoneNumber(shelterDto.getPhoneNumber());
//                    shelter.setOwner(newOwner);
//                }
//            }
//        }
        shelterRepository.save(shelter);

        return new ResponseMessage("SUCCESS");
    }

    @Override
    @Transactional
    public ResponseMessage AuthShelter(Long shelterId, String authCode) {
        Optional<Shelter> s = shelterRepository.findById(shelterId);
        if(!s.isPresent()){
            if(passwordEncoder.matches(s.get().getRegNumber(),authCode)){
                Optional<User> user = SecurityUtil.getCurrentEmail().flatMap(userRepository::findOneWithAuthoritiesByEmail);
                user.get().setShelter(s.get());

                List<Authority> authorities = user.get().getAuthorities();
                authorities.add(Authority.builder()
                            .authorityName("ROLE_ADMIN")
                            .build());
                user.get().setAuthorities(authorities);
                userRepository.save(user.get());
            }
            else return new ResponseMessage("FAIL");
        }else return new ResponseMessage("FAIL");
        return new ResponseMessage("SUCCESS");
    }

    @Override
    @Transactional
    public String getAuthCode(Long shelterId) {
        Optional<Shelter> shelter = shelterRepository.findById(shelterId);
        if(!shelter.isPresent()){
            return null;
        }
        Optional<String> authCode = Optional.ofNullable(shelter.get().getAuthCode());
        if(!authCode.isPresent()){
            return null;
        }
        return authCode.get();
    }


    @Override
    @Transactional
    public ShelterDto detailShelter(Long shelterId) {
        Shelter s = shelterRepository.findByShelterId(shelterId);
        List<Image> shelterImageList = s.getImages();
        List<FileDto> imageList = new ArrayList<>();

        Optional<Owner> owner = Optional.ofNullable(s.getOwner());
        if(!owner.isPresent()){
            LOGGER.info("오너가 없다.");
            return null;
        }
        if(shelterImageList.isEmpty()){
            Image i = fileService.getDefaultImage();
            imageList.add(FileDto.builder()
                    .id(i.getImageId())
                    .origFilename(i.getOrigFilename())
                    .filename(i.getFilename())
                    .filePath(fileService.getFileUrl(i))
                    .build());
        }
        else{
            for(Image i : shelterImageList){
                imageList.add(FileDto.builder()
                        .id(i.getImageId())
                        .origFilename(i.getOrigFilename())
                        .filename(i.getFilename())
                        .filePath(fileService.getFileUrl(i))
                        .build());
            }
        }


        ShelterDto shelterDto = ShelterDto.builder()
                .shelterId(s.getShelterId())
                .name(s.getName())
                .phoneNumber(s.getOwner().getPhoneNumber())
                .infoContent(s.getInfo())
                .address(s.getAddress())
                .imageList(imageList)
                .ownerId(s.getOwner().getOwnerId())
                .ownerName(s.getOwner().getName())
                .build();
        return shelterDto;
    }

    @Override
    @Transactional
    public List<ShelterNameDto> selectShelterAll(){
        List<ShelterNameDto> shelterNameDtos = new ArrayList<>();
        List<Shelter> list = shelterRepository.findAll();

        long idx = 0;
        for (Shelter s: list) {
            shelterNameDtos.add(ShelterNameDto.builder()
                    .idx(idx)
                    .name(s.getName())
                    .build());
            idx++;
        }
        return shelterNameDtos;
    }

    @Override
    public Boolean deleteShelter(Long shelterId) {
        LOGGER.info("shelterId : {}", shelterId);
        Shelter shelter = shelterRepository.findByShelterId(shelterId);
        shelterRepository.delete(shelter);
        return true;
    }


}
