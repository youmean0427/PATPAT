package com.ssafy.patpat.report.service;

import com.ssafy.patpat.common.dto.FileDto;
import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.common.entity.Image;
import com.ssafy.patpat.common.repository.ImageRepository;
import com.ssafy.patpat.protect.dto.ProtectDto;
import com.ssafy.patpat.report.dto.ReportDto;
import com.ssafy.patpat.report.dto.RequestReportDto;
import com.ssafy.patpat.report.entity.MissingDog;
import com.ssafy.patpat.report.entity.MissingDogImage;
import com.ssafy.patpat.report.entity.PersonalProtectedDog;
import com.ssafy.patpat.report.repository.MissingDogImageRepository;
import com.ssafy.patpat.report.repository.MissingDogRepository;
import com.ssafy.patpat.report.entity.PersonalProtectedDogImage;
import com.ssafy.patpat.report.repository.PersonalProtectedDogImageRepository;
import com.ssafy.patpat.report.repository.PersonalProtectedDogRepository;
import com.ssafy.patpat.shelter.entity.Breed;
import com.ssafy.patpat.shelter.repository.BreedRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
@Service
public class ReportServiceImpl implements ReportService{

    @Autowired
    MissingDogRepository missingDogRepository;
    @Autowired
    PersonalProtectedDogRepository personalProtectedDogRepository;
    @Autowired
    MissingDogImageRepository missingDogImageRepository;
    @Autowired
    PersonalProtectedDogImageRepository personalProtectedDogImageRepository;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    BreedRepository breedRepository;
    @Value("${app.fileupload.uploadPath}")
    String uploadPath;

    @Value("${app.fileupload.uploadDir}")
    String uploadFolder;
    @Override
    public List<ReportDto> selectMissingList(RequestReportDto requestReportDto) {
        int gender = requestReportDto.getGender();
        int breedId = requestReportDto.getBreedId();
        int limit = requestReportDto.getLimit();
        int offSet = requestReportDto.getLimit();
        PageRequest pageRequest = PageRequest.of(offSet,limit);

        List<MissingDog> missingDogList = null;
        if(gender == 0 && breedId == 0){
            Pageable pageable =  PageRequest.of(limit,offSet);
            Page<MissingDog> pageList = missingDogRepository.findAll(pageRequest);
            missingDogList = pageList.toList();
        }
        else if(gender == 0 && breedId > 0){
            missingDogList = missingDogRepository.findByGender(gender,pageRequest);
        }
        else if(gender>0 && breedId==0){
            missingDogList = missingDogRepository.findByBreedId(breedId,pageRequest);
        }
        else if(gender > 0 && breedId > 0){
            missingDogList = missingDogRepository.findByGenderAndBreedId(gender, breedId, pageRequest);
        }

        List<ReportDto> reportDtoList = new ArrayList<>();

        for(MissingDog missingDog : missingDogList){
            //이미지 FK 가져오기
            List<MissingDogImage> missingDogImageList = missingDogImageRepository.findByMissingId(missingDog.getMissingId());
            //실제 이미지 가져오기
            List<Integer> imageIdList = new ArrayList<>();
            for(MissingDogImage missingDogImage : missingDogImageList){
                imageIdList.add(missingDogImage.getImageId());
            }
            List<Image> imageList = imageRepository.findByImageIdIn(imageIdList);
            //이미지 담을 파일 객체 리스트 생성
            FileDto thumbnail = new FileDto();
            if(imageList.size() > 0){
                FileDto.builder()
                        .filePath(imageList.get(0).getFilePath())
                        .build();
            }
            Breed breed = breedRepository.findByBreedId(missingDog.getBreedId());
            reportDtoList.add(
                    ReportDto.builder()
                            .name(missingDog.getName())
                            .missingId(missingDog.getMissingId())
                            .gender(missingDog.getGender())
                            .isNeutered(missingDog.getNeutered()==1?true:false)
                            .age(missingDog.getAge())
                            .breedId(missingDog.getBreedId())
                            .breedName(breed.getName())
                            .kg(missingDog.getWeight())
                            .thumbnail(thumbnail)
                            .build()
            );
        }
        return reportDtoList;
    }

    @Override
    public List<ReportDto> selectMissingListByUser(int userId, RequestReportDto requestReportDto) {
        int limit = requestReportDto.getLimit();
        int offSet = requestReportDto.getLimit();
        PageRequest pageRequest = PageRequest.of(offSet,limit);
        List<MissingDog> missingDogList = missingDogRepository.findByUserId(userId,pageRequest);
        List<ReportDto> reportDtoList = new ArrayList<>();

        for(MissingDog missingDog : missingDogList){
            //이미지 FK 가져오기
            List<MissingDogImage> missingDogImageList = missingDogImageRepository.findByMissingId(missingDog.getMissingId());
            //실제 이미지 가져오기
            List<Integer> imageIdList = new ArrayList<>();
            for(MissingDogImage missingDogImage : missingDogImageList){
                imageIdList.add(missingDogImage.getImageId());
            }
            List<Image> imageList = imageRepository.findByImageIdIn(imageIdList);
            //이미지 담을 파일 객체 리스트 생성
            FileDto thumbnail = new FileDto();
            if(imageList.size() > 0){
                FileDto.builder()
                        .filePath(imageList.get(0).getFilePath())
                        .build();
            }
            Breed breed = breedRepository.findByBreedId(missingDog.getBreedId());
            reportDtoList.add(
                    ReportDto.builder()
                            .name(missingDog.getName())
                            .missingId(missingDog.getMissingId())
                            .gender(missingDog.getGender())
                            .isNeutered(missingDog.getNeutered()==1?true:false)
                            .age(missingDog.getAge())
                            .breedId(missingDog.getBreedId())
                            .breedName(breed.getName())
                            .kg(missingDog.getWeight())
                            .thumbnail(thumbnail)
                            .build()
            );
        }
        return reportDtoList;
    }

    @Override
    public List<ReportDto> selectPersonalProtectionList(RequestReportDto requestReportDto) {
        int gender = requestReportDto.getGender();
        int breedId = requestReportDto.getBreedId();
        int limit = requestReportDto.getLimit();
        int offSet = requestReportDto.getLimit();
        PageRequest pageRequest = PageRequest.of(offSet,limit);

        List<PersonalProtectedDog> personalProtectedDogList = null;
        if(gender == 0 && breedId == 0){
            Pageable pageable =  PageRequest.of(limit,offSet);
            Page<PersonalProtectedDog> pageList = personalProtectedDogRepository.findAll(pageRequest);
            personalProtectedDogList = pageList.toList();
        }
        else if(gender == 0 && breedId > 0){
            personalProtectedDogList = personalProtectedDogRepository.findByGender(gender,pageRequest);
        }
        else if(gender>0 && breedId==0){
            personalProtectedDogList = personalProtectedDogRepository.findByBreedId(breedId,pageRequest);
        }
        else if(gender > 0 && breedId > 0){
            personalProtectedDogList = personalProtectedDogRepository.findByGenderAndBreedId(gender, breedId, pageRequest);
        }

        List<ReportDto> reportDtoList = new ArrayList<>();

        for(PersonalProtectedDog personalProtectedDog : personalProtectedDogList){
            //이미지 FK 가져오기
            List<PersonalProtectedDogImage> personalProtectedDogImageList = personalProtectedDogImageRepository.findByPpDogId(personalProtectedDog.getPpDogId());
            //실제 이미지 가져오기
            List<Integer> imageIdList = new ArrayList<>();
            for(PersonalProtectedDogImage personalProtectedDogImage : personalProtectedDogImageList){
                imageIdList.add(personalProtectedDogImage.getImageId());
            }
            List<Image> imageList = imageRepository.findByImageIdIn(imageIdList);
            //이미지 담을 파일 객체 리스트 생성
            FileDto thumbnail = new FileDto();
            if(imageList.size() > 0){
                FileDto.builder()
                        .filePath(imageList.get(0).getFilePath())
                        .build();
            }
            Breed breed = breedRepository.findByBreedId(personalProtectedDog.getBreedId());
            reportDtoList.add(
                    ReportDto.builder()
                            .name(personalProtectedDog.getName())
                            .personalProtectionId(personalProtectedDog.getPpDogId())
                            .gender(personalProtectedDog.getGender())
                            .isNeutered(personalProtectedDog.getNeutered()==1?true:false)
                            .age(personalProtectedDog.getAge())
                            .breedId(personalProtectedDog.getBreedId())
                            .breedName(breed.getName())
                            .kg(personalProtectedDog.getWeight())
                            .thumbnail(thumbnail)
                            .build()
            );
        }
        return reportDtoList;
    }

    @Override
    public ReportDto detailMissing(int missingId) {
        MissingDog missingDog = missingDogRepository.findByMissingId(missingId);
        List<MissingDogImage> missingDogImageList = missingDogImageRepository.findByMissingId(missingId);
        //실제 이미지 가져오기
        List<Integer> imageIdList = new ArrayList<>();
        for(MissingDogImage missingDogImage : missingDogImageList) {
            imageIdList.add(missingDogImage.getImageId());
        }
        List<Image> imageList = imageRepository.findByImageIdIn(imageIdList);
        //이미지 dto에 넣기
        List<FileDto> fileDtoList = new ArrayList<>();

        for(Image i : imageList){
            fileDtoList.add(
                    FileDto.builder()
                            .filePath(i.getFilePath())
                            .build()
            );
        }

        ReportDto reportDto = ReportDto.builder()
                .missingId(missingDog.getMissingId())
                .breedName(missingDog.getName())
                .breedId(missingDog.getBreedId())
                .kg(missingDog.getWeight())
                .isNeutered(missingDog.getNeutered()==0?false:true)
                .age(missingDog.getAge())
                .gender(missingDog.getGender())
                .name(missingDog.getName())
                .categoryCloth(missingDog.getCategoryCloth())
                .categoryColor(missingDog.getCategoryColor())
                .categoryEar(missingDog.getCategoryEar())
                .categoryPattern(missingDog.getCategoryPattern())
                .categoryTail(missingDog.getCategoryTail())
                .categoryClothColor(missingDog.getCategoryClothColor())
                .content(missingDog.getFeature())
                .title(missingDog.getTitle())
                .fileUrlList(fileDtoList)
                .build();


        return reportDto;
    }

    @Override
    public ReportDto detailPersonalProtection(int personalProtectId) {
        PersonalProtectedDog personalProtectedDog = personalProtectedDogRepository.findByPpDogId(personalProtectId);
        List<PersonalProtectedDogImage> personalProtectedDogImageList = personalProtectedDogImageRepository.findByPpDogId(personalProtectId);
        //실제 이미지 가져오기
        List<Integer> imageIdList = new ArrayList<>();
        for(PersonalProtectedDogImage personalProtected : personalProtectedDogImageList) {
            imageIdList.add(personalProtected.getImageId());
        }
        List<Image> imageList = imageRepository.findByImageIdIn(imageIdList);
        //이미지 dto에 넣기
        List<FileDto> fileDtoList = new ArrayList<>();
        if(imageList.size() > 0){
            for(Image i : imageList){
                fileDtoList.add(
                        FileDto.builder()
                                .filePath(i.getFilePath())
                                .build()
                );
            }
        }

        ReportDto reportDto = ReportDto.builder()
                .personalProtectionId(personalProtectedDog.getPpDogId())
                .breedName(personalProtectedDog.getName())
                .breedId(personalProtectedDog.getBreedId())
                .kg(personalProtectedDog.getWeight())
                .isNeutered(personalProtectedDog.getNeutered()==0?false:true)
                .age(personalProtectedDog.getAge())
                .gender(personalProtectedDog.getGender())
                .name(personalProtectedDog.getName())
                .categoryCloth(personalProtectedDog.getCategoryCloth())
                .categoryColor(personalProtectedDog.getCategoryColor())
                .categoryEar(personalProtectedDog.getCategoryEar())
                .categoryPattern(personalProtectedDog.getCategoryPattern())
                .categoryTail(personalProtectedDog.getCategoryTail())
                .categoryClothColor(personalProtectedDog.getCategoryClothColor())
                .content(personalProtectedDog.getFeature())
                .title(personalProtectedDog.getTitle())
                .fileUrlList(fileDtoList)
                .build();


        return reportDto;
    }

    @Override
    public ResponseMessage updateReport(ReportDto reportDto, List<MultipartFile> uploadFile) {
        ResponseMessage responseMessage = new ResponseMessage();
        try{
            if(reportDto.getTypeCode() == 1) {
                MissingDog missingDog = missingDogRepository.findByMissingId(reportDto.getMissingId());
                missingDog.update(
                        reportDto.getStateCode(), reportDto.getContent(), reportDto.getGender(), reportDto.getBreedId(),
                        reportDto.getGender(), reportDto.isNeutered() ? 1 : 0, reportDto.getCategoryEar(), reportDto.getCategoryTail(),
                        reportDto.getCategoryColor(), reportDto.getCategoryPattern(), reportDto.getCategoryCloth(), reportDto.getCategoryClothColor());
                File uploadDir = new File(uploadPath + File.separator + uploadFolder);
                if (!uploadDir.exists()) uploadDir.mkdir();
                List<MissingDogImage> missingDogImageList = missingDogImageRepository.findByMissingId(reportDto.getMissingId());

                List<Integer> list = new ArrayList<>();
                for (MissingDogImage i : missingDogImageList) {
                    list.add(i.getImageId());
                }
                List<Image> imageList = imageRepository.findByImageIdIn(list);
                for (Image i : imageList) {
                    File file = new File(uploadPath + File.separator + i.getFilePath());
                    if (file.exists()) file.delete();
                }

                imageRepository.deleteByImageIdIn(list);
                missingDogImageRepository.deleteByMissingId(reportDto.getMissingId());

                for (MultipartFile partFile : uploadFile) {
                    int missingId = missingDog.getMissingId();
                    String fileName = partFile.getOriginalFilename();

                    UUID uuid = UUID.randomUUID();

                    String extension = FilenameUtils.getExtension(fileName);

                    String savingFileName = uuid + "." + extension;

                    File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);

                    partFile.transferTo(destFile);

                    Image image = Image.builder()
                            .origFilename(fileName)
                            .fileSize((int) partFile.getSize())
                            .filename(fileName)
                            .filePath(uploadFolder + "/" + savingFileName)
                            .build();

                    imageRepository.save(image);
                    MissingDogImage missingDogImage = MissingDogImage.builder()
                            .imageId(image.getImageId())
                            .missingId(missingId)
                            .build();

                    missingDogImageRepository.save(missingDogImage);
                }
            }
            else {
                PersonalProtectedDog personalProtectedDog = personalProtectedDogRepository.findByPpDogId(reportDto.getPersonalProtectionId());
                personalProtectedDog.update(
                        reportDto.getStateCode(), reportDto.getContent(), reportDto.getGender(), reportDto.getBreedId(),
                        reportDto.getGender(), reportDto.isNeutered() ? 1 : 0, reportDto.getCategoryEar(), reportDto.getCategoryTail(),
                        reportDto.getCategoryColor(), reportDto.getCategoryPattern(), reportDto.getCategoryCloth(), reportDto.getCategoryClothColor());
                File uploadDir = new File(uploadPath + File.separator + uploadFolder);
                if (!uploadDir.exists()) uploadDir.mkdir();
                List<PersonalProtectedDogImage> personalProtectedDogImageList = personalProtectedDogImageRepository.findByPpDogId(reportDto.getPersonalProtectionId());

                List<Integer> list = new ArrayList<>();
                for (PersonalProtectedDogImage i : personalProtectedDogImageList) {
                    list.add(i.getImageId());
                }
                List<Image> imageList = imageRepository.findByImageIdIn(list);
                for (Image i : imageList) {
                    File file = new File(uploadPath + File.separator + i.getFilePath());
                    if (file.exists()) file.delete();
                }

                imageRepository.deleteByImageIdIn(list);
                personalProtectedDogImageRepository.deleteByPdImageId(reportDto.getPersonalProtectionId());

                for (MultipartFile partFile : uploadFile) {
                    int ppDogId = personalProtectedDog.getPpDogId();
                    String fileName = partFile.getOriginalFilename();

                    UUID uuid = UUID.randomUUID();

                    String extension = FilenameUtils.getExtension(fileName);

                    String savingFileName = uuid + "." + extension;

                    File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);

                    partFile.transferTo(destFile);

                    Image image = Image.builder()
                            .origFilename(fileName)
                            .fileSize((int) partFile.getSize())
                            .filename(fileName)
                            .filePath(uploadFolder + "/" + savingFileName)
                            .build();

                    imageRepository.save(image);
                    PersonalProtectedDogImage personalProtectedDogImage = PersonalProtectedDogImage.builder()
                            .imageId(image.getImageId())
                            .ppDogId(ppDogId)
                            .build();

                    personalProtectedDogImageRepository.save(personalProtectedDogImage);
                }
            }
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
        try{
            if(reportDto.getTypeCode() == 1) {
                MissingDog missingDog = MissingDog.builder()
                        .missingDate(reportDto.getFindDate())
                        .breedId(reportDto.getBreedId())
                        .age(reportDto.getAge())
                        .gender(reportDto.getGender())
                        .categoryCloth(reportDto.getCategoryCloth())
                        .categoryPattern(reportDto.getCategoryPattern())
                        .categoryTail(reportDto.getCategoryTail())
                        .categoryEar(reportDto.getCategoryEar())
                        .categoryClothColor(reportDto.getCategoryClothColor())
                        .categoryColor(reportDto.getCategoryColor())
                        .latitude(reportDto.getLatitude())
                        .longitude(reportDto.getLongitude())
                        .name(reportDto.getName())
                        .neutered(reportDto.isNeutered() ? 1 : 0)
                        .title(reportDto.getTitle())
                        .weight(reportDto.getKg())
                        .userId(reportDto.getUserId())
                        .feature(reportDto.getContent())
                        .stateCode(0)
                        .registDate(LocalDate.now())
                        .build();

                missingDogRepository.save(missingDog);

                File uploadDir = new File(uploadPath + File.separator + uploadFolder);
                if (!uploadDir.exists()) uploadDir.mkdir();
                if (uploadFile != null) {
                    for (MultipartFile partFile : uploadFile) {
                        int missingId = missingDog.getMissingId();
                        String fileName = partFile.getOriginalFilename();

                        UUID uuid = UUID.randomUUID();

                        String extension = FilenameUtils.getExtension(fileName);

                        String savingFileName = uuid + "." + extension;

                        File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);

                        partFile.transferTo(destFile);

                        Image image = Image.builder()
                                .origFilename(fileName)
                                .fileSize((int) partFile.getSize())
                                .filename(fileName)
                                .filePath(uploadFolder + "/" + savingFileName)
                                .build();

                        imageRepository.save(image);
                        MissingDogImage missingDogImage = MissingDogImage.builder()
                                .imageId(image.getImageId())
                                .missingId(missingId)
                                .build();

                        missingDogImageRepository.save(missingDogImage);
                    }
                }
            }else{
                System.out.println(reportDto);
                PersonalProtectedDog personalProtectedDog = PersonalProtectedDog.builder()
                        .breedId(reportDto.getBreedId())
                        .age(reportDto.getAge())
                        .gender(reportDto.getGender())
                        .categoryCloth(reportDto.getCategoryCloth())
                        .categoryPattern(reportDto.getCategoryPattern())
                        .categoryTail(reportDto.getCategoryTail())
                        .categoryEar(reportDto.getCategoryEar())
                        .categoryClothColor(reportDto.getCategoryClothColor())
                        .categoryColor(reportDto.getCategoryColor())
                        .latitude(reportDto.getLatitude())
                        .longitude(reportDto.getLongitude())
                        .name(reportDto.getName())
                        .neutered(reportDto.isNeutered() ? 1 : 0)
                        .title(reportDto.getTitle())
                        .weight(reportDto.getKg())
                        .userId(reportDto.getUserId())
                        .feature(reportDto.getContent())
                        .stateCode(0)
                        .registDate(LocalDate.now())
                        .build();

            personalProtectedDogRepository.save(personalProtectedDog);

                File uploadDir = new File(uploadPath + File.separator + uploadFolder);
                if (!uploadDir.exists()) uploadDir.mkdir();
                if (uploadFile != null) {
                    for (MultipartFile partFile : uploadFile) {
                        int ppDogId = personalProtectedDog.getPpDogId();
                        String fileName = partFile.getOriginalFilename();

                        UUID uuid = UUID.randomUUID();

                        String extension = FilenameUtils.getExtension(fileName);

                        String savingFileName = uuid + "." + extension;

                        File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);

                        partFile.transferTo(destFile);

                        Image image = Image.builder()
                                .origFilename(fileName)
                                .fileSize((int) partFile.getSize())
                                .filename(fileName)
                                .filePath(uploadFolder + "/" + savingFileName)
                                .build();

                        imageRepository.save(image);
                        PersonalProtectedDogImage personalProtectedDogImage = PersonalProtectedDogImage.builder()
                                .imageId(image.getImageId())
                                .ppDogId(ppDogId)
                                .build();

                        personalProtectedDogImageRepository.save(personalProtectedDogImage);
                    }
                }
            }
            responseMessage.setMessage("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            responseMessage.setMessage("FAIL");
        }
        return responseMessage;
    }

    @Override
    public List<ProtectDto> selectRecommendList(RequestReportDto requestReportDto) {
        return null;
    }

    @Override
    public HashMap<String, Integer> selectRecommendCount(RequestReportDto requestReportDto) {
        return null;
    }
}
