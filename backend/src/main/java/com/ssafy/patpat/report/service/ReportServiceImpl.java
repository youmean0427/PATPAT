package com.ssafy.patpat.report.service;

import com.ssafy.patpat.common.code.DogType;
import com.ssafy.patpat.common.code.MissingState;
import com.ssafy.patpat.common.code.ProtectState;
import com.ssafy.patpat.common.code.category.*;
import com.ssafy.patpat.common.dto.FileDto;
import com.ssafy.patpat.common.dto.ResponseListDto;
import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.common.entity.DogColor;
import com.ssafy.patpat.common.entity.Image;
import com.ssafy.patpat.common.entity.Recommend;
import com.ssafy.patpat.common.repository.ImageRepository;
import com.ssafy.patpat.common.service.ColorService;
import com.ssafy.patpat.common.service.FileService;
import com.ssafy.patpat.common.util.SecurityUtil;
import com.ssafy.patpat.protect.dto.ProtectDto;
import com.ssafy.patpat.protect.entity.ShelterProtectedDog;
import com.ssafy.patpat.protect.repository.ShelterProtectedDogRepository;
import com.ssafy.patpat.report.dto.RecommendDto;
import com.ssafy.patpat.report.dto.ReportDto;
import com.ssafy.patpat.report.dto.RequestReportDto;
import com.ssafy.patpat.report.entity.MissingDog;
//import com.ssafy.patpat.report.entity.MissingDogImage;
import com.ssafy.patpat.report.entity.PersonalProtectedDog;
//import com.ssafy.patpat.report.repository.MissingDogImageRepository;
import com.ssafy.patpat.report.repository.MissingDogRepository;
//import com.ssafy.patpat.report.entity.PersonalProtectedDogImage;
//import com.ssafy.patpat.report.repository.PersonalProtectedDogImageRepository;
import com.ssafy.patpat.report.repository.PersonalProtectedDogRepository;
import com.ssafy.patpat.shelter.entity.Breed;
import com.ssafy.patpat.shelter.repository.BreedRepository;
import com.ssafy.patpat.user.dto.UserDto;
import com.ssafy.patpat.user.entity.User;
import com.ssafy.patpat.user.repository.UserRepository;
import com.ssafy.patpat.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService{

    @Autowired
    MissingDogRepository missingDogRepository;
    @Autowired
    PersonalProtectedDogRepository personalProtectedDogRepository;
//    @Autowired
//    MissingDogImageRepository missingDogImageRepository;
//    @Autowired
//    PersonalProtectedDogImageRepository personalProtectedDogImageRepository;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    BreedRepository breedRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FileService fileService;

    @Autowired
    UserService userService;
    @Autowired
    ColorService colorService;

    @Autowired
    ShelterProtectedDogRepository shelterProtectedDogRepository;

    @Value("${app.fileupload.uploadPath}")
    String uploadPath;

    @Value("${app.fileupload.uploadDir}")
    String uploadFolder;
    @Override
    public ResponseListDto selectMissingList(RequestReportDto requestReportDto) {
        try{
            ResponseListDto responseListDto = new ResponseListDto();
            Gender gender = Gender.of(requestReportDto.getGender());
            Long breedId = requestReportDto.getBreedId();
            int limit = requestReportDto.getLimit();
            int offSet = requestReportDto.getOffSet();
            PageRequest pageRequest = PageRequest.of(offSet,limit, Sort.by("missingId").descending());

            Page<MissingDog> missingDogList = null;
            if(gender.getCode() == 0 && breedId == 0){
              missingDogList = missingDogRepository.findAll(pageRequest);
            }
            else if(gender.getCode()>0 && breedId==0){
                missingDogList = missingDogRepository.findByGender(gender,pageRequest);
            }
            else if(gender.getCode() == 0 && breedId > 0){
                missingDogList = missingDogRepository.findByBreedBreedId(breedId,pageRequest);
            }
            else if(gender.getCode() > 0 && breedId > 0){
                missingDogList = missingDogRepository.findByGenderAndBreedBreedId(gender, breedId, pageRequest);
            }

            List<ReportDto> reportDtoList = new ArrayList<>();
//            System.out.println(missingDogList);
            for(MissingDog missingDog : missingDogList.toList()){
                //이미지 FK 가져오기
//                List<MissingDogImage> missingDogImageList = missingDogImageRepository.findByMissingId(missingDog.getMissingId());
                //실제 이미지 가져오기
                List<Image> missingDogImageList = missingDog.getImages();
//                List<Integer> imageIdList = new ArrayList<>();
//                for(MissingDogImage missingDogImage : missingDogImageList){
//                    imageIdList.add(missingDogImage.getImageId());
//                }
//                List<Image> imageList = imageRepository.findByImageIdIn(imageIdList);
                //이미지 담을 파일 객체 리스트 생성
                FileDto thumbnail = null;
                if(missingDogImageList.isEmpty()){
                    thumbnail = FileDto.builder()
                            .filePath(fileService.getFileUrl(fileService.getDefaultImage()))
                            .build();
                }
                if(missingDogImageList.size() > 0){
                    thumbnail = FileDto.builder()
                            .filePath(fileService.getFileUrl(missingDogImageList.get(0)))
                            .build();
                }
                Breed breed = missingDog.getBreed();
//                System.out.println(breed);
                reportDtoList.add(
                        ReportDto.builder()
                                .missingId(missingDog.getMissingId())
                                .title(missingDog.getTitle())
                                .content(missingDog.getFeature())
                                .thumbnail(thumbnail)
                                .build()
                );
            }
            responseListDto.setList(reportDtoList);
            responseListDto.setTotalPage(missingDogList.getTotalPages());
            responseListDto.setTotalCount(missingDogList.getTotalElements());
            return responseListDto;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public ResponseListDto selectMissingListByUser(RequestReportDto requestReportDto) {
        ResponseListDto responseListDto = new ResponseListDto();
        Optional<User> user = SecurityUtil.getCurrentEmail().flatMap(userRepository::findOneWithAuthoritiesByEmail);
        int limit = requestReportDto.getLimit();
        int offSet = requestReportDto.getOffSet();
        PageRequest pageRequest = PageRequest.of(offSet,limit);
//        System.out.println(userId);
//        System.out.println(requestReportDto);

        Page<MissingDog> missingDogList = missingDogRepository.findByUserUserId(user.get().getUserId(),pageRequest);

//        System.out.println(missingDogList);
        List<ReportDto> reportDtoList = new ArrayList<>();

        for(MissingDog missingDog : missingDogList.toList()){
            //이미지 FK 가져오기
            List<Image> missingDogImageList = missingDog.getImages();
//            List<MissingDogImage> missingDogImageList = missingDogImageRepository.findByMissingId(missingDog.getMissingId());
            //실제 이미지 가져오기
//            List<Integer> imageIdList = new ArrayList<>();
//            for(MissingDogImage missingDogImage : missingDogImageList){
//                imageIdList.add(missingDogImage.getImageId());
//            }
//            List<Image> imageList = imageRepository.findByImageIdIn(imageIdList);
            //이미지 담을 파일 객체 리스트 생성
            FileDto thumbnail = new FileDto();
            if(missingDogImageList.isEmpty()){
                thumbnail = FileDto.builder()
                        .filePath(fileService.getFileUrl(fileService.getDefaultImage()))
                        .build();
            }
            if(missingDogImageList.size() > 0){
                thumbnail =  FileDto.builder()
                        .filePath(fileService.getFileUrl(missingDogImageList.get(0)))
                        .build();
            }
            Breed breed = missingDog.getBreed();
            reportDtoList.add(
                    ReportDto.builder()
                            .title(missingDog.getTitle())
                            .name(missingDog.getName())
                            .missingId(missingDog.getMissingId())
                            .genderCode(missingDog.getGender().getCode())
                            .gender(missingDog.getGender().name())
                            .neuteredCode(missingDog.getNeutered().getCode())
                            .neutered(missingDog.getNeutered().name())
                            .age(missingDog.getAge())
                            .breedId(breed.getBreedId())
                            .breedName(breed.getName())
                            .kg(missingDog.getWeight())
                            .thumbnail(thumbnail)
                            .build()
            );
        }
        responseListDto.setList(reportDtoList);
        responseListDto.setTotalPage(missingDogList.getTotalPages());
        responseListDto.setTotalCount(missingDogList.getTotalElements());
        return responseListDto;
    }

    @Override
    public ResponseListDto selectPersonalProtectionList(RequestReportDto requestReportDto) {
        ResponseListDto responseListDto = new ResponseListDto();
        Gender gender = Gender.of(requestReportDto.getGender());
        long breedId = requestReportDto.getBreedId();
        int limit = requestReportDto.getLimit();
        int offSet = requestReportDto.getOffSet();
        PageRequest pageRequest = PageRequest.of(offSet,limit,Sort.by("ppDogId").descending());

        Page<PersonalProtectedDog> personalProtectedDogList = null;
        if(gender.getCode() == 0 && breedId == 0){
            personalProtectedDogList = personalProtectedDogRepository.findAll(pageRequest);

        }
        else if(gender.getCode() > 0 && breedId == 0){
            personalProtectedDogList = personalProtectedDogRepository.findByGender(gender,pageRequest);
        }
        else if(gender.getCode() == 0 && breedId > 0){
            personalProtectedDogList = personalProtectedDogRepository.findByBreedBreedId(breedId,pageRequest);
        }
        else if(gender.getCode() > 0 && breedId > 0){
            personalProtectedDogList = personalProtectedDogRepository.findByGenderAndBreedBreedId(gender, breedId, pageRequest);
        }

        List<ReportDto> reportDtoList = new ArrayList<>();

        for(PersonalProtectedDog personalProtectedDog : personalProtectedDogList.toList()){
            //이미지 FK 가져오기
            List<Image> personalProtectedDogImageList = personalProtectedDog.getImages();
//            List<PersonalProtectedDogImage> personalProtectedDogImageList = personalProtectedDogImageRepository.findByPpDogId(personalProtectedDog.getPpDogId());
            //실제 이미지 가져오기
//            List<Integer> imageIdList = new ArrayList<>();
//            for(PersonalProtectedDogImage personalProtectedDogImage : personalProtectedDogImageList){
//                imageIdList.add(personalProtectedDogImage.getImageId());
//            }
//            List<Image> imageList = imageRepository.findByImageIdIn(imageIdList);
            //이미지 담을 파일 객체 리스트 생성
            FileDto thumbnail = new FileDto();
            if(personalProtectedDogImageList.isEmpty()){
                thumbnail = FileDto.builder()
                        .filePath(fileService.getFileUrl(fileService.getDefaultImage()))
                        .build();
            }
            if(personalProtectedDogImageList.size() > 0){
                FileDto.builder()
                        .filePath(fileService.getFileUrl(personalProtectedDogImageList.get(0)))
                        .build();
            }
            Breed breed = personalProtectedDog.getBreed();
            reportDtoList.add(
                    ReportDto.builder()
                            .personalProtectionId(personalProtectedDog.getPpDogId())
                            .title(personalProtectedDog.getTitle())
                            .content(personalProtectedDog.getFeature())
                            .thumbnail(thumbnail)
                            .build()
            );
        }
        responseListDto.setList(reportDtoList);
        responseListDto.setTotalPage(personalProtectedDogList.getTotalPages());
        responseListDto.setTotalCount(personalProtectedDogList.getTotalElements());
        return responseListDto;
    }

    @Override
    public ReportDto detailMissing(Long missingId) {
        MissingDog missingDog = missingDogRepository.findByMissingId(missingId);
        List<Image> missingDogImageList = missingDog.getImages();
//        List<MissingDogImage> missingDogImageList = missingDogImageRepository.findByMissingId(missingId);
        //실제 이미지 가져오기
//        List<Integer> imageIdList = new ArrayList<>();
//        for(MissingDogImage missingDogImage : missingDogImageList) {
//            imageIdList.add(missingDogImage.getImageId());
//        }
//        List<Image> imageList = imageRepository.findByImageIdIn(imageIdList);
        //이미지 dto에 넣기
        List<FileDto> fileDtoList = new ArrayList<>();

        for(Image i : missingDogImageList){
            fileDtoList.add(
                    FileDto.builder()
                            .filePath(fileService.getFileUrl(i))
                            .build()
            );
        }
        Breed breed = missingDog.getBreed();
        /** color 처리 로직 필요  */
        List<DogColor> colors = missingDog.getColors();
        List<String> colorCode = new ArrayList<>();
        for (DogColor color:
                colors) {
            colorCode.add(color.getColorCode());
        }
        ReportDto reportDto = ReportDto.builder()
                .missingId(missingDog.getMissingId())
                .breedName(breed.getName())
                .breedId(breed.getBreedId())
                .kg(missingDog.getWeight())
                .genderCode(missingDog.getGender().getCode())
                .gender(missingDog.getGender().name())
                .neuteredCode(missingDog.getNeutered().getCode())
                .neutered(missingDog.getNeutered().name())
                .age(missingDog.getAge())
                .name(missingDog.getName())
                .categoryCloth(missingDog.getCategoryCloth().name())
                .categoryClothCode(missingDog.getCategoryCloth().getCode())
                .categoryColor(colorCode)
                .categoryEar(missingDog.getCategoryEar().name())
                .categoryEarCode(missingDog.getCategoryEar().getCode())
                .categoryPattern(missingDog.getCategoryPattern().name())
                .categoryPatternCode(missingDog.getCategoryPattern().getCode())
                .categoryTail(missingDog.getCategoryTail().name())
                .categoryTailCode(missingDog.getCategoryTail().getCode())
                .content(missingDog.getFeature())
                .title(missingDog.getTitle())
                .fileUrlList(fileDtoList)
                .latitude(missingDog.getLatitude().toString())
                .longitude(missingDog.getLongitude().toString())
                .state(missingDog.getStateCode().name())
                .stateCode(missingDog.getStateCode().getCode())
                .userId(missingDog.getUser().getUserId())
                .build();
        return reportDto;
    }

    @Override
    public ReportDto detailPersonalProtection(Long personalProtectId) {
        PersonalProtectedDog personalProtectedDog = personalProtectedDogRepository.findByPpDogId(personalProtectId);
        List<Image> personalProtectedDogImageList = personalProtectedDog.getImages();
//        List<PersonalProtectedDogImage> personalProtectedDogImageList = personalProtectedDogImageRepository.findByPpDogId(personalProtectId);
        //실제 이미지 가져오기
//        List<Integer> imageIdList = new ArrayList<>();
//        for(PersonalProtectedDogImage personalProtected : personalProtectedDogImageList) {
//            imageIdList.add(personalProtected.getImageId());
//        }
//        List<Image> imageList = imageRepository.findByImageIdIn(imageIdList);
        //이미지 dto에 넣기
        List<FileDto> fileDtoList = new ArrayList<>();
        if(personalProtectedDogImageList.size() > 0){
            for(Image i : personalProtectedDogImageList){
                fileDtoList.add(
                        FileDto.builder()
                                .filePath(fileService.getFileUrl(i))
                                .build()
                );
            }
        }
        /** color 처리 로직 필요  */
        List<DogColor> colors = personalProtectedDog.getColors();
        List<String> colorCode = new ArrayList<>();
        for (DogColor color:
                colors) {
            colorCode.add(color.getColorCode());
        }
        Breed breed = personalProtectedDog.getBreed();
        ReportDto reportDto = ReportDto.builder()
                .personalProtectionId(personalProtectedDog.getPpDogId())
                .breedName(breed.getName())
                .breedId(breed.getBreedId())
                .kg(personalProtectedDog.getWeight())
                .genderCode(personalProtectedDog.getGender().getCode())
                .gender(personalProtectedDog.getGender().name())
                .neuteredCode(personalProtectedDog.getNeutered().getCode())
                .neutered(personalProtectedDog.getNeutered().name())
                .age(personalProtectedDog.getAge())
                .name(personalProtectedDog.getName())
                .categoryCloth(personalProtectedDog.getCategoryCloth().name())
                .categoryTailCode(personalProtectedDog.getCategoryCloth().getCode())
                .categoryColor(colorCode)
                .categoryEar(personalProtectedDog.getCategoryEar().name())
                .categoryEarCode(personalProtectedDog.getCategoryEar().getCode())
                .categoryPattern(personalProtectedDog.getCategoryPattern().name())
                .categoryPatternCode(personalProtectedDog.getCategoryPattern().getCode())
                .categoryTail(personalProtectedDog.getCategoryTail().name())
                .categoryTailCode(personalProtectedDog.getCategoryTail().getCode())
                .content(personalProtectedDog.getFeature())
                .title(personalProtectedDog.getTitle())
                .latitude(personalProtectedDog.getLatitude().toString())
                .longitude(personalProtectedDog.getLongitude().toString())
                .fileUrlList(fileDtoList)
                .stateCode(personalProtectedDog.getStateCode().getCode())
                .state(personalProtectedDog.getStateCode().name())
                .userId(personalProtectedDog.getUser().getUserId())
                .build();


        return reportDto;
    }

    @Override
    public ResponseMessage updateReport(ReportDto reportDto, List<MultipartFile> uploadFile) throws Exception {
        ResponseMessage responseMessage = new ResponseMessage();
        try{
            if(reportDto.getTypeCode() == 1) {
                MissingDog missingDog = missingDogRepository.findByMissingId(reportDto.getMissingId());

                Breed breed = breedRepository.findByBreedId(reportDto.getBreedId());

                List<DogColor> colors = missingDog.getColors();
                colors.removeAll(colors);
                for (String c:
                        reportDto.getCategoryColor()) {
                    colors.add(DogColor.builder()
                            .colorCode(c)
                            .build());
                }
                missingDog.setColors(colors);


                if(uploadFile != null){
                    List<Image> missingDogImageList = missingDog.getImages();
                    for (Image i : missingDogImageList){
                        fileService.deleteFile(i);
                    }
                    missingDogImageList.removeAll(missingDogImageList);

                    for (MultipartFile partFile : uploadFile){
                        missingDogImageList.add(fileService.insertFile(partFile,"report"));
                    }
                    missingDog.setImages(missingDogImageList);
                }
                /** Color 처리 로직 필요 */
                Color color = null;

                missingDog.update(
                        MissingState.of(reportDto.getStateCode()),
                        reportDto.getContent(),
                        Gender.of(reportDto.getGenderCode()),
                        breed,
                        reportDto.getKg(),
                        Neutered.of(reportDto.getNeuteredCode()),
                        Ear.of(reportDto.getCategoryEarCode()),
                        Tail.of(reportDto.getCategoryTailCode()),
                        color,
                        Pattern.of(reportDto.getCategoryPatternCode()),
                        Cloth.of(reportDto.getCategoryClothCode()));

                missingDogRepository.save(missingDog);
//                File uploadDir = new File(uploadPath + File.separator + uploadFolder);
//                if (!uploadDir.exists()) uploadDir.mkdir();
//                List<MissingDogImage> missingDogImageList = missingDogImageRepository.findByMissingId(reportDto.getMissingId());
//
//                List<Integer> list = new ArrayList<>();
//                for (MissingDogImage i : missingDogImageList) {
//                    list.add(i.getImageId());
//                }
//                List<Image> imageList = imageRepository.findByImageIdIn(list);
//                for (Image i : imageList) {
//                    File file = new File(uploadPath + File.separator + i.getFilePath());
//                    if (file.exists()) file.delete();
//                }

//                imageRepository.deleteByImageIdIn(list);
//                missingDogImageRepository.deleteByMissingId(reportDto.getMissingId());

//                for (MultipartFile partFile : uploadFile) {
//                    int missingId = missingDog.getMissingId();
//                    String fileName = partFile.getOriginalFilename();
//
//                    UUID uuid = UUID.randomUUID();
//
//                    String extension = FilenameUtils.getExtension(fileName);
//
//                    String savingFileName = uuid + "." + extension;
//
//                    File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
//
//                    partFile.transferTo(destFile);
//
//                    Image image = Image.builder()
//                            .origFilename(fileName)
//                            .fileSize((int) partFile.getSize())
//                            .filename(fileName)
//                            .filePath(uploadFolder + "/" + savingFileName)
//                            .build();
//
//                    imageRepository.save(image);
//                    MissingDogImage missingDogImage = MissingDogImage.builder()
//                            .imageId(image.getImageId())
//                            .missingId(missingId)
//                            .build();
//
//                    missingDogImageRepository.save(missingDogImage);
//                }
            }
            else {
                PersonalProtectedDog personalProtectedDog = personalProtectedDogRepository.findByPpDogId(reportDto.getPersonalProtectionId());
                Breed breed = breedRepository.findByBreedId(reportDto.getBreedId());
                
                List<DogColor> colors = personalProtectedDog.getColors();
                colors.removeAll(colors);
                for (String c:
                        reportDto.getCategoryColor()) {
                    colors.add(DogColor.builder()
                            .colorCode(c)
                            .build());
                }
                personalProtectedDog.setColors(colors);

                if (uploadFile != null) {
                    List<Image> personalProtectedDogImageList = personalProtectedDog.getImages();
                    for (Image i : personalProtectedDogImageList) {
                        fileService.deleteFile(i);
                    }
                    personalProtectedDogImageList.removeAll(personalProtectedDogImageList);

                    for (MultipartFile partFile : uploadFile) {
                        personalProtectedDogImageList.add(fileService.insertFile(partFile, "report"));
                    }
                    personalProtectedDog.setImages(personalProtectedDogImageList);
                }
                /** Color 처리 로직 필요 */
                Color color = null;

                personalProtectedDog.update(
                        ProtectState.of(reportDto.getStateCode()),
                        reportDto.getContent(),
                        Gender.of(reportDto.getGenderCode()),
                        breed,
                        reportDto.getKg(),
                        Neutered.of(reportDto.getNeuteredCode()),
                        Ear.of(reportDto.getCategoryEarCode()),
                        Tail.of(reportDto.getCategoryTailCode()),
                        color,
                        Pattern.of(reportDto.getCategoryPatternCode()),
                        Cloth.of(reportDto.getCategoryClothCode()));

                

                personalProtectedDogRepository.save(personalProtectedDog);
            }
//                File uploadDir = new File(uploadPath + File.separator + uploadFolder);
//                if (!uploadDir.exists()) uploadDir.mkdir();
//                List<PersonalProtectedDogImage> personalProtectedDogImageList = personalProtectedDogImageRepository.findByPpDogId(reportDto.getPersonalProtectionId());
//
//                List<Integer> list = new ArrayList<>();
//                for (PersonalProtectedDogImage i : personalProtectedDogImageList) {
//                    list.add(i.getImageId());
//                }
//                List<Image> imageList = imageRepository.findByImageIdIn(list);
//                for (Image i : imageList) {
//                    File file = new File(uploadPath + File.separator + i.getFilePath());
//                    if (file.exists()) file.delete();
//                }
//
//                imageRepository.deleteByImageIdIn(list);
//                personalProtectedDogImageRepository.deleteByPdImageId(reportDto.getPersonalProtectionId());
//
//                for (MultipartFile partFile : uploadFile) {
//                    int ppDogId = personalProtectedDog.getPpDogId();
//                    String fileName = partFile.getOriginalFilename();
//
//                    UUID uuid = UUID.randomUUID();
//
//                    String extension = FilenameUtils.getExtension(fileName);
//
//                    String savingFileName = uuid + "." + extension;
//
//                    File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
//
//                    partFile.transferTo(destFile);
//
//                    Image image = Image.builder()
//                            .origFilename(fileName)
//                            .fileSize((int) partFile.getSize())
//                            .filename(fileName)
//                            .filePath(uploadFolder + "/" + savingFileName)
//                            .build();
//
//                    imageRepository.save(image);
//                    PersonalProtectedDogImage personalProtectedDogImage = PersonalProtectedDogImage.builder()
//                            .imageId(image.getImageId())
//                            .ppDogId(ppDogId)
//                            .build();
//
//                    personalProtectedDogImageRepository.save(personalProtectedDogImage);
//                }
//            }
            responseMessage.setMessage("SUCCESS");

        }catch (Exception e){
            e.printStackTrace();
            responseMessage.setMessage("FAIL");
        }

        return responseMessage;
    }

    @Override
    @Transactional
    public ResponseMessage insertReport(ReportDto reportDto, List<MultipartFile> uploadFile) {
        ResponseMessage responseMessage = new ResponseMessage();
        /**
         * 유저 정보 들어오는거 생기면 다시하기
         */
//        UserDto userDto = userService.getUserWithAuthorities();
        Optional<User> user = SecurityUtil.getCurrentEmail().flatMap(userRepository::findOneWithAuthoritiesByEmail);
        Breed breed = breedRepository.findByBreedId(reportDto.getBreedId());
        System.out.println(uploadFile);
        try{
            List<Image> images = new ArrayList<>();
            if(uploadFile != null){
                for (MultipartFile file:
                        uploadFile) {
                    images.add(fileService.insertFile(file,"report"));
                }
            }
            List<DogColor> colors = new ArrayList<>();
            for (String c:
                    reportDto.getCategoryColor()) {
                colors.add(DogColor.builder()
                        .colorCode(c)
                        .build());
            }
            /** Color 처리 로직 */
            /** 프론트에게 다시 줘야하는 #333333 형식의 컬러 코드 리스트를 엔티티리스트에 저장 **/
            for(String color : reportDto.getCategoryColor()){
                colors.add(
                        DogColor.builder()
                                .colorCode(color)
                                .build()
                );
            }
            /** 유사도 비교시 사용할 백엔드에서만 사용하는 컬러코드 저장 **/
            Color color = colorService.getColorCode(reportDto.getCategoryColor());
            if(reportDto.getTypeCode() == 1) {

                MissingDog missingDog = MissingDog.builder()
                        .missingDate(reportDto.getDate())
                        .breed(breed)
                        .age(reportDto.getAge())
                        .gender(Gender.of(reportDto.getGenderCode()))
                        .neutered(Neutered.of(reportDto.getNeuteredCode()))
                        .categoryCloth(Cloth.of(reportDto.getCategoryClothCode()))
                        .categoryPattern(Pattern.of(reportDto.getCategoryPatternCode()))
                        .categoryTail(Tail.of(reportDto.getCategoryTailCode()))
                        .categoryEar(Ear.of(reportDto.getCategoryEarCode()))
                        .categoryColor(color)
                        .colors(colors)
                        .latitude(new BigDecimal(reportDto.getLatitude()))
                        .longitude(new BigDecimal(reportDto.getLongitude()))
                        .name(reportDto.getName())
                        .title(reportDto.getTitle())
                        .weight(reportDto.getKg())
                        .user(user.get())
                        .feature(reportDto.getContent())
                        .images(images)
                        .registDate(LocalDate.now())
                        .build();

                missingDogRepository.save(missingDog);

                /** 임보,보호동물 조회 후 추천견종 엔티티에 집어 넣는다. **/
                insertRecommend(user.get(),missingDog);

//                File uploadDir = new File(uploadPath + File.separator + uploadFolder);
//                if (!uploadDir.exists()) uploadDir.mkdir();
//                if (uploadFile != null) {
//                    for (MultipartFile partFile : uploadFile) {
//                        int missingId = missingDog.getMissingId();
//                        String fileName = partFile.getOriginalFilename();
//
//                        UUID uuid = UUID.randomUUID();
//
//                        String extension = FilenameUtils.getExtension(fileName);
//
//                        String savingFileName = uuid + "." + extension;
//
//                        File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
//
//                        partFile.transferTo(destFile);
//
//                        Image image = Image.builder()
//                                .origFilename(fileName)
//                                .fileSize((int) partFile.getSize())
//                                .filename(fileName)
//                                .filePath(uploadFolder + "/" + savingFileName)
//                                .build();
//
//                        imageRepository.save(image);
//                        MissingDogImage missingDogImage = MissingDogImage.builder()
//                                .imageId(image.getImageId())
//                                .missingId(missingId)
//                                .build();
//
//                        missingDogImageRepository.save(missingDogImage);
//                    }
//                }
            }else{

                PersonalProtectedDog personalProtectedDog = PersonalProtectedDog.builder()
                        .findDate(reportDto.getDate())
                        .breed(breed)
                        .age(reportDto.getAge())
                        .gender(Gender.of(reportDto.getGenderCode()))
                        .neutered(Neutered.of(reportDto.getNeuteredCode()))
                        .categoryCloth(Cloth.of(reportDto.getCategoryClothCode()))
                        .categoryPattern(Pattern.of(reportDto.getCategoryPatternCode()))
                        .categoryTail(Tail.of(reportDto.getCategoryTailCode()))
                        .categoryEar(Ear.of(reportDto.getCategoryEarCode()))
                        .categoryColor(color)
                        .colors(colors)
                        .latitude(new BigDecimal(reportDto.getLatitude()))
                        .longitude(new BigDecimal(reportDto.getLongitude()))
                        .name(reportDto.getName())
                        .title(reportDto.getTitle())
                        .weight(reportDto.getKg())
                        .user(user.get())
                        .feature(reportDto.getContent())
                        .registDate(LocalDate.now())
                        .images(images)
                        .build();

            personalProtectedDogRepository.save(personalProtectedDog);

//                File uploadDir = new File(uploadPath + File.separator + uploadFolder);
//                if (!uploadDir.exists()) uploadDir.mkdir();
//                if (uploadFile != null) {
//                    for (MultipartFile partFile : uploadFile) {
//                        int ppDogId = personalProtectedDog.getPpDogId();
//                        String fileName = partFile.getOriginalFilename();
//
//                        UUID uuid = UUID.randomUUID();
//
//                        String extension = FilenameUtils.getExtension(fileName);
//
//                        String savingFileName = uuid + "." + extension;
//
//                        File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
//
//                        partFile.transferTo(destFile);
//
//                        Image image = Image.builder()
//                                .origFilename(fileName)
//                                .fileSize((int) partFile.getSize())
//                                .filename(fileName)
//                                .filePath(uploadFolder + "/" + savingFileName)
//                                .build();
//
//                        imageRepository.save(image);
//                        PersonalProtectedDogImage personalProtectedDogImage = PersonalProtectedDogImage.builder()
//                                .imageId(image.getImageId())
//                                .ppDogId(ppDogId)
//                                .build();
//
//                        personalProtectedDogImageRepository.save(personalProtectedDogImage);
//                    }
//                }
            }
            responseMessage.setMessage("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            responseMessage.setMessage("FAIL");
        }
        return responseMessage;
    }
    @Override
    public void insertRecommend(User user, MissingDog missingDog) {
        List<PersonalProtectedDog> pProtectList = personalProtectedDogRepository.selectBydistance(missingDog.getLatitude(),missingDog.getLongitude(),missingDog.getLatitude(),missingDog.getRegistDate());
        List<ShelterProtectedDog>  sProtectList = shelterProtectedDogRepository.selectBydistance(missingDog.getLatitude(),missingDog.getLongitude(),missingDog.getLatitude(),missingDog.getRegistDate());
        List<Recommend> recommendList = user.getRecommends();

        for(PersonalProtectedDog p : pProtectList){
            int count = 0;
            if(missingDog.getBreed().equals(p.getBreed())) count++;
            if(missingDog.getGender().equals(p.getGender())) count++;
            if(missingDog.getCategoryCloth().equals(p.getCategoryCloth())) count++;
            if(missingDog.getCategoryColor().equals(p.getCategoryColor())) count++;
            if(missingDog.getCategoryEar().equals(p.getCategoryEar())) count++;
            if(missingDog.getCategoryPattern().equals(p.getCategoryPattern())) count++;
            if(missingDog.getCategoryTail().equals(p.getCategoryTail())) count++;
            if(count >= 4){
                recommendList.add(
                        Recommend.builder()
                                .dogId(p.getPpDogId())
                                .dogType(DogType.개인보호견)
                                .user(missingDog.getUser())
                                .build()
                );
            }
        }

        for(ShelterProtectedDog s : sProtectList){
            int count = 0;
            if(missingDog.getBreed().equals(s.getBreed())) count++;
            if(missingDog.getGender().equals(s.getGender())) count++;
            if(missingDog.getCategoryCloth().equals(s.getCategoryCloth())) count++;
            if(missingDog.getCategoryColor().equals(s.getCategoryColor())) count++;
            if(missingDog.getCategoryEar().equals(s.getCategoryEar())) count++;
            if(missingDog.getCategoryPattern().equals(s.getCategoryPattern())) count++;
            if(missingDog.getCategoryTail().equals(s.getCategoryTail())) count++;
            if(count >= 4){
                recommendList.add(
                        Recommend.builder()
                                .dogId(s.getSpDogId())
                                .dogType(DogType.보호소보호견)
                                .user(missingDog.getUser())
                                .build()
                );
            }
        }
        userRepository.save(user);
    }

    @Override
    public List<RecommendDto> selectRecommendList(RequestReportDto requestReportDto) {
        List<RecommendDto> recommendDtoList = new ArrayList<>();
        Optional<User> user = SecurityUtil.getCurrentEmail().flatMap(userRepository::findOneWithAuthoritiesByEmail);
        List<Recommend> recommendList = user.get().getRecommends();
        for(Recommend r : recommendList){
            RecommendDto recommendDto = new RecommendDto();
            if(r.getDogType().getCode()==1){
                PersonalProtectedDog personalProtectedDog = personalProtectedDogRepository.findByPpDogId(r.getDogId());
                recommendDto.setPersonalProtectionId(personalProtectedDog.getPpDogId());
                recommendDto.setKg(personalProtectedDog.getWeight());
                recommendDto.setAge(personalProtectedDog.getAge());
                recommendDto.setName(personalProtectedDog.getName());
                recommendDto.setBreedId(personalProtectedDog.getBreed().getBreedId());
                recommendDto.setBreedName(personalProtectedDog.getBreed().getName());
                recommendDto.setThumbnail(
                        FileDto.builder()
                                .filePath(personalProtectedDog.getImages().get(0).getFilePath())
                                .build()
                );
                recommendDto.setNeuteredCode(personalProtectedDog.getNeutered().getCode());
                recommendDto.setNeutered(personalProtectedDog.getNeutered().name());
            }else if(r.getDogType().getCode()==2){
                ShelterProtectedDog shelterProtectedDog = shelterProtectedDogRepository.findBySpDogId(r.getDogId());
                recommendDto.setProtectId(shelterProtectedDog.getSpDogId());
                recommendDto.setKg(shelterProtectedDog.getWeight());
                recommendDto.setAge(shelterProtectedDog.getAge());
                recommendDto.setName(shelterProtectedDog.getName());
                recommendDto.setBreedId(shelterProtectedDog.getBreed().getBreedId());
                recommendDto.setBreedName(shelterProtectedDog.getBreed().getName());
                recommendDto.setThumbnail(
                        FileDto.builder()
                                .filePath(shelterProtectedDog.getImages().get(0).getFilePath())
                                .build()
                );
                recommendDto.setNeuteredCode(shelterProtectedDog.getNeutered().getCode());
                recommendDto.setNeutered(shelterProtectedDog.getNeutered().name());
            }
            recommendDtoList.add(recommendDto);
        }
        return recommendDtoList;
    }

    @Override
    public HashMap<String, Integer> selectRecommendCount(RequestReportDto requestReportDto) {
        return null;
    }
}
