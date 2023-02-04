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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
                            .gender(missingDog.getGender())
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
                            .gender(missingDog.getGender())
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
                            .gender(personalProtectedDog.getGender())
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
        return null;
    }

    @Override
    public ResponseMessage insertReport(ReportDto reportDto, List<MultipartFile> uploadFile) {
        return null;
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
