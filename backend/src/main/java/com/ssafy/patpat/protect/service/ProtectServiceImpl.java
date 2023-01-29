package com.ssafy.patpat.protect.service;

import com.ssafy.patpat.common.dto.FileDto;
import com.ssafy.patpat.common.entity.Image;
import com.ssafy.patpat.common.repository.ImageRepository;
import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.protect.dto.ProtectDto;
import com.ssafy.patpat.protect.dto.RequestProtectDto;
import com.ssafy.patpat.protect.entity.ShelterDogImage;
import com.ssafy.patpat.protect.entity.ShelterProtectedDog;
import com.ssafy.patpat.protect.repository.ShelterDogImageRepository;
import com.ssafy.patpat.protect.repository.ShelterProtectedDogRepository;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.shelter.repository.ShelterRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class ProtectServiceImpl implements ProtectService{
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    ShelterProtectedDogRepository shelterProtectedDogRepository;
    @Autowired
    ShelterDogImageRepository shelterDogImageRepository;

    @Autowired
    ShelterRepository shelterRepository;

    @Value("${app.fileupload.uploadPath}")
    String uploadPath;

    @Value("${app.fileupload.uploadDir}")
    String uploadFolder;

    @Override
    public List<ProtectDto> selectProtectList(RequestProtectDto requestProtectDto) {
        try{
            PageRequest pageRequest;
            List<ShelterProtectedDog> shelterProtectedDogList;
            List<Integer> filterList = new ArrayList<>();
            filterList.add(4);
            filterList.add(5);
            filterList.add(6);

            if(requestProtectDto.getCode() == 0){
                pageRequest = PageRequest.of(requestProtectDto.getOffSet(),requestProtectDto.getLimit(), Sort.by("registDate").ascending());
                shelterProtectedDogList = shelterProtectedDogRepository.findByStateCodeNotIn(filterList,pageRequest);
            }
            else if(requestProtectDto.getCode() == 1){
                pageRequest = PageRequest.of(requestProtectDto.getOffSet(),requestProtectDto.getLimit(), Sort.by("registDate").descending());
                shelterProtectedDogList = shelterProtectedDogRepository.findByStateCodeNotIn(filterList,pageRequest);
            }
            else{
                return null;
            }
            List<ProtectDto> protectDtoList = new ArrayList<>();
            for(ShelterProtectedDog s : shelterProtectedDogList){
                //파일 담을 객체 생성 후 받아오기
                List<ShelterDogImage> shelterDogImageList = shelterDogImageRepository.findByspDogId(s.getSpDogId());
                //만약 파일이 있다면
                List<FileDto> fileDtoList = new ArrayList<>();
                if(shelterDogImageList.size() > 0) {
                    //이미지를 한개 받아온다.
                    Image image = imageRepository.findByImageId(shelterDogImageList.get(0).getImageId());
                    //파일 디티오에 넣는다.
                    FileDto fileDto = FileDto.builder()
                            .origFilename(image.getOrigFilename())
                            .build();
                    fileDtoList.add(fileDto);
                }

                protectDtoList.add(
                        ProtectDto.builder()
                                .protectId(s.getSpDogId())
                                .stateCode(s.getStateCode())
                                .protectName(s.getName())
                                .fileUrlList(fileDtoList)
                                .build()

                );
            }
            return  protectDtoList;
        }catch(Exception e){
            return null;
        }
    }
    @Override
    public List<ProtectDto> selectProtectListByShelter(int shelterId , RequestProtectDto requestProtectDto) {
        try{
            PageRequest pageRequest;
            List<ShelterProtectedDog> shelterProtectedDogList;
            List<Integer> filterList = new ArrayList<>();
            filterList.add(4);
            filterList.add(5);
            filterList.add(6);
            pageRequest = PageRequest.of(requestProtectDto.getOffSet(),requestProtectDto.getLimit());
            shelterProtectedDogList = shelterProtectedDogRepository.findByShelterIdAndStateCodeNotIn(shelterId,filterList,pageRequest);
            List<ProtectDto> protectDtoList = new ArrayList<>();
            for(ShelterProtectedDog s : shelterProtectedDogList){
                //파일 담을 객체 생성 후 받아오기
                List<ShelterDogImage> shelterDogImageList = shelterDogImageRepository.findByspDogId(s.getSpDogId());
                //만약 파일이 있다면
                List<FileDto> fileDtoList = new ArrayList<>();
                if(shelterDogImageList.size() > 0) {
                    //이미지를 한개 받아온다.
                    Image image = imageRepository.findByImageId(shelterDogImageList.get(0).getImageId());
                    //파일 디티오에 넣는다.
                    FileDto fileDto = FileDto.builder()
                            .origFilename(image.getOrigFilename())
                            .build();
                    fileDtoList.add(fileDto);
                }

                protectDtoList.add(
                        ProtectDto.builder()
                                .protectId(s.getSpDogId())
                                .stateCode(s.getStateCode())
                                .protectName(s.getName())
                                .fileUrlList(fileDtoList)
                                .build()

                );
            }
            return  protectDtoList;
        }catch(Exception e){
            return null;
        }

    }

    @Override
    public ProtectDto detailProtect(int protectId) {
        try{
            ShelterProtectedDog shelterProtectedDog = shelterProtectedDogRepository.findBySpDogId(protectId);
            List<ShelterDogImage> shelterDogImageList = shelterDogImageRepository.findByspDogId(protectId);
            List<Integer> iList = new ArrayList<>();
            for(ShelterDogImage s : shelterDogImageList){
                iList.add(s.getImageId());
            }
            List<Image> imageList = imageRepository.findByImageIdIn(iList);
            List<FileDto> fileDtoList = new ArrayList<>();
            for(Image i : imageList){
                fileDtoList.add(
                        FileDto.builder()
                                .origFilename(i.getOrigFilename())
                                .build()
                );
            }

            ProtectDto protectDto = ProtectDto.builder()
                    .protectName(shelterProtectedDog.getName())
                    .protectId(shelterProtectedDog.getSpDogId())
                    .shelterId(shelterProtectedDog.getShelterId())
                    .breedId(shelterProtectedDog.getBreedId())
                    .stateCode(shelterProtectedDog.getStateCode())
                    .gender(shelterProtectedDog.getGender())
                    .neutered(shelterProtectedDog.getNeutered())
                    .infoContent(shelterProtectedDog.getFeature())
                    .weight(shelterProtectedDog.getWeight())
                    .categoryCloth(shelterProtectedDog.getCategoryCloth())
                    .categoryClothColor(shelterProtectedDog.getCategoryClothColor())
                    .categoryColor(shelterProtectedDog.getCategoryColor())
                    .categoryEar(shelterProtectedDog.getCategoryEar())
                    .categoryPattern(shelterProtectedDog.getCategoryPattern())
                    .categoryTail(shelterProtectedDog.getCategoryTail())
                    .fileUrlList(fileDtoList)
                    .build();
            return  protectDto;
        }catch(Exception e){
            return null;
        }
    }

    @Override
    @Transactional
    public ResponseMessage insertProtect(ProtectDto protectDto, List<MultipartFile> uploadFile) {
        ResponseMessage responseMessage = new ResponseMessage();
        Shelter shelter = shelterRepository.findByShelterId(protectDto.getShelterId());
        System.out.println(protectDto);
        try {
            ShelterProtectedDog shelterProtectedDog = ShelterProtectedDog.builder()
                    .age(protectDto.getAge())
                    .breedId(protectDto.getBreedId())
                    .shelterId(protectDto.getShelterId())
                    .feature(protectDto.getInfoContent())
                    .gender(protectDto.getGender())
                    .registDate(LocalDateTime.now().toLocalDate())
                    //.findingDate(protectDto.getFindingDate())
                    .findingDate(LocalDate.now())
                    .latitude(shelter.getLatitude())
                    .longitude(shelter.getLongitude())
                    .name(protectDto.getProtectName())
                    .weight(protectDto.getWeight())
                    .stateCode(protectDto.getStateCode())
                    .sidoCode(shelter.getSidoCode())
                    .gugunCode(shelter.getGugunCode())
                    .categoryCloth(protectDto.getCategoryCloth())
                    .categoryTail(protectDto.getCategoryTail())
                    .categoryPattern(protectDto.getCategoryPattern())
                    .categoryEar(protectDto.getCategoryEar())
                    .categoryClothColor(protectDto.getCategoryClothColor())
                    .categoryColor(protectDto.getCategoryColor())
                    .build();
            shelterProtectedDogRepository.save(shelterProtectedDog);
            File uploadDir = new File(uploadPath + File.separator + uploadFolder);
            if (!uploadDir.exists()) uploadDir.mkdir();
            System.out.println(uploadFile.size());

            for (MultipartFile partFile : uploadFile) {
                int spDogId = shelterProtectedDog.getSpDogId();
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
                ShelterDogImage shelterDogImage = ShelterDogImage.builder()
                        .imageId(image.getImageId())
                        .spDogId(shelterProtectedDog.getSpDogId())
                        .build();
                shelterDogImageRepository.save(shelterDogImage);
            }
            responseMessage.setMessage("SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            responseMessage.setMessage("FAIL");
        }
        return responseMessage;
    }

    @Override
    public ResponseMessage updateProtect(int protectId, List<MultipartFile> uploadFile) {
        return null;
    }

}
