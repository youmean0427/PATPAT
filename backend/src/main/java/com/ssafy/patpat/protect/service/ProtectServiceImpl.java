package com.ssafy.patpat.protect.service;

import com.ssafy.patpat.board.entity.PostImage;
import com.ssafy.patpat.common.code.ProtectState;
import com.ssafy.patpat.common.dto.FileDto;
import com.ssafy.patpat.common.entity.Image;
import com.ssafy.patpat.common.repository.ImageRepository;
import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.common.service.FileService;
import com.ssafy.patpat.protect.dto.ProtectDto;
import com.ssafy.patpat.protect.dto.RequestProtectDto;
import com.ssafy.patpat.protect.entity.ShelterProtectedDog;
//import com.ssafy.patpat.protect.repository.ShelterDogImageRepository;
import com.ssafy.patpat.protect.repository.ShelterProtectedDogRepository;
import com.ssafy.patpat.shelter.entity.Breed;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.shelter.repository.BreedRepository;
import com.ssafy.patpat.shelter.repository.ShelterRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
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
//    @Autowired
//    ShelterDogImageRepository shelterDogImageRepository;

    @Autowired
    ShelterRepository shelterRepository;
    @Autowired
    BreedRepository breedRepository;

    @Autowired
    FileService fileService;

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
                List<Image> shelterDogImageList = s.getImages();
                //만약 파일이 있다면
                List<FileDto> fileDtoList = new ArrayList<>();
                String thumbnail = "noProfile";
                if(shelterDogImageList.size() > 0) {
                    //이미지를 한개 받아온다.
//                    Image image = imageRepository.findByImageId(shelterDogImageList.get(0).getImageId());
                    Image image = shelterDogImageList.get(0);
                    //파일 디티오에 넣는다.
                    FileDto fileDto = FileDto.builder()
                            .origFilename(image.getOrigFilename())
                            .build();
                    thumbnail=fileDto.getFilePath();
                }
                Breed breed = breedRepository.findByBreedId(s.getBreedId());
                protectDtoList.add(
                        ProtectDto.builder()
                                .protectId(s.getSpDogId())
                                .protectName(s.getName())
                                .stateCode(s.getStateCode().getCode())
                                .state(s.getStateCode().name())
                                .gender(s.getGender())
                                .protectName(s.getName())
                                .thumbnail(thumbnail)
                                .isNeutered(s.getNeutered()==0?false:true)
                                .age(s.getAge())
                                .breedName(breed.getName())
                                .breedId(breed.getBreedId())
                                .kg(s.getWeight())
                                .build()

                );
            }
            return  protectDtoList;
        }catch(Exception e){
            return null;
        }
    }
    @Override
    public List<ProtectDto> selectProtectListByShelter(RequestProtectDto requestProtectDto) {
        try{
            PageRequest pageRequest;
            List<ShelterProtectedDog> shelterProtectedDogList;
            List<Integer> filterList = new ArrayList<>();
            filterList.add(4);
            filterList.add(5);
            filterList.add(6);
            pageRequest = PageRequest.of(requestProtectDto.getOffSet(),requestProtectDto.getLimit());
            shelterProtectedDogList = shelterProtectedDogRepository.findByShelterIdAndStateCodeNotIn(requestProtectDto.getShelterId(), filterList,pageRequest);
            List<ProtectDto> protectDtoList = new ArrayList<>();
            for(ShelterProtectedDog s : shelterProtectedDogList){
                //파일 담을 객체 생성 후 받아오기
                List<Image> shelterDogImageList = s.getImages();
                //만약 파일이 있다면
                String thumbnail = "noProfile";
                if(shelterDogImageList.size() > 0) {
                    //이미지를 한개 받아온다.
                    Image image = imageRepository.findByImageId(shelterDogImageList.get(0).getImageId());
                    //파일 디티오에 넣는다.
                    FileDto fileDto = FileDto.builder()
                            .origFilename(image.getOrigFilename())
                            .build();
                    thumbnail=fileDto.getFilePath();
                }

                Breed breed = breedRepository.findByBreedId(s.getBreedId());
                protectDtoList.add(
                        ProtectDto.builder()
                                .protectId(s.getSpDogId())
                                .stateCode(s.getStateCode().getCode())
                                .state(s.getStateCode().name())
                                .protectName(s.getName())
                                .thumbnail(thumbnail)
                                .gender(s.getGender())
                                .isNeutered(s.getNeutered()==0?false:true)
                                .age(s.getAge())
                                .breedName(breed.getName())
                                .breedId(breed.getBreedId())
                                .kg(s.getWeight())
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
            List<Image> shelterDogImageList = shelterProtectedDog.getImages();
//            List<Integer> iList = new ArrayList<>();
//            for(Image s : shelterDogImageList){
//                iList.add(s.getImageId());
//            }
//            List<Image> imageList = imageRepository.findByImageIdIn(iList);
            List<FileDto> fileDtoList = new ArrayList<>();
            for(Image i : shelterDogImageList){
                fileDtoList.add(
                        FileDto.builder()
                                .origFilename(i.getOrigFilename())
                                .build()
                );
            }
            Breed breed = breedRepository.findByBreedId(shelterProtectedDog.getBreedId());
            ProtectDto protectDto = ProtectDto.builder()
                    .protectName(shelterProtectedDog.getName())
                    .protectId(shelterProtectedDog.getSpDogId())
                    .shelterId(shelterProtectedDog.getShelterId())
                    .breedId(shelterProtectedDog.getBreedId())
                    .breedName(breed.getName())
                    .breedId(breed.getBreedId())
                    .stateCode(shelterProtectedDog.getStateCode().getCode())
                    .state(shelterProtectedDog.getStateCode().name())
                    .gender(shelterProtectedDog.getGender())
                    .isNeutered((shelterProtectedDog.getNeutered()==0)?true:false)
                    .infoContent(shelterProtectedDog.getFeature())
                    .kg(shelterProtectedDog.getWeight())
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
    public ResponseMessage insertProtect(ProtectDto protectDto, @RequestPart()  List<MultipartFile> uploadFile) {
        ResponseMessage responseMessage = new ResponseMessage();
        Shelter shelter = shelterRepository.findByShelterId(protectDto.getShelterId());
        System.out.println(protectDto);
        try {
            List<Image> images = new ArrayList<>();
            for (MultipartFile partFile : uploadFile) {
                Image image = fileService.insertFile(partFile);
                images.add(image);
            }
            ShelterProtectedDog shelterProtectedDog = ShelterProtectedDog.builder()
                    .age(protectDto.getAge())
                    .breedId(protectDto.getBreedId())
                    .shelterId(protectDto.getShelterId())
                    .feature(protectDto.getInfoContent())
                    .gender(protectDto.getGender())
                    .images(images)
                    .registDate(LocalDateTime.now().toLocalDate())
                    //.findingDate(protectDto.getFindingDate())
                    .findingDate(LocalDate.now())
                    .latitude(shelter.getLatitude())
                    .longitude(shelter.getLongitude())
                    .name(protectDto.getProtectName())
                    .weight(protectDto.getKg())
                    .stateCode(ProtectState.of(protectDto.getStateCode()))
                    .sidoCode(shelter.getSidoCode     ())
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


//                int spDogId = shelterProtectedDog.getSpDogId();
//                String fileName = partFile.getOriginalFilename();
//
//                UUID uuid = UUID.randomUUID();
//
//                String extension = FilenameUtils.getExtension(fileName);
//
//                String savingFileName = uuid + "." + extension;
//
//                File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
//
//                partFile.transferTo(destFile);
//
//                Image image = Image.builder()
//                        .origFilename(fileName)
//                        .fileSize((int) partFile.getSize())
//                        .filename(fileName)
//                        .filePath(uploadFolder + "/" + savingFileName)
//                        .build();
//
//                imageRepository.save(image);



//                shelterDogImageRepository.save(shelterDogImage);
//            }
            responseMessage.setMessage("SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            responseMessage.setMessage("FAIL");
        }
        return responseMessage;
    }

    @Override
    @Transactional
    public ResponseMessage updateProtect(int protectId, List<MultipartFile> uploadFile,ProtectDto protectDto) {
        ResponseMessage responseMessage = new ResponseMessage();
        try{

            ShelterProtectedDog shelterProtectedDog = shelterProtectedDogRepository.findBySpDogId(protectId);

            File uploadDir = new File(uploadPath +File.separator+uploadFolder);
            if(!uploadDir.exists()) uploadDir.mkdir();

            List<Image> shelterDogImageList = shelterProtectedDog.getImages();
//            List<Integer> list = new ArrayList<>();
//            for(Image i : shelterDogImageList){
//                list.add(i.getImageId());
//            }
//            List<Image> imageList = imageRepository.findByImageIdIn(list);
            for(Image i : shelterDogImageList){
//                File file = new File(uploadPath+File.separator+i.getFilePath());
//                if(file.exists()) file.delete();
                fileService.deleteFile(i);
            }

            List<Image> images = new ArrayList<>();
            for (MultipartFile partFile : uploadFile) {
                Image image = fileService.insertFile(partFile);
                images.add(image);
            }

            shelterProtectedDog.setImages(images);
            shelterProtectedDog.setWeight(protectDto.getKg());
            shelterProtectedDog.setFeature(protectDto.getInfoContent());

            shelterProtectedDogRepository.save(shelterProtectedDog);
//            shelterProtectedDog.updateShelterProtectedDog(protectDto.getKg(), protectDto.getInfoContent());

//            shelterDogImageRepository.deleteBySpDogId(protectId);
//            for (MultipartFile partFile : uploadFile) {
//                int spDogId = shelterProtectedDog.getSpDogId();
//                String fileName = partFile.getOriginalFilename();
//
//                UUID uuid = UUID.randomUUID();
//
//                String extension = FilenameUtils.getExtension(fileName);
//
//                String savingFileName = uuid + "." + extension;
//
//                File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
//
//                partFile.transferTo(destFile);
//
//                Image image = Image.builder()
//                        .origFilename(fileName)
//                        .fileSize((int) partFile.getSize())
//                        .filename(fileName)
//                        .filePath(uploadFolder + "/" + savingFileName)
//                        .build();
//
//                imageRepository.save(image);
//                ShelterDogImage shelterDogImage = ShelterDogImage.builder()
//                        .imageId(image.getImageId())
//                        .spDogId(shelterProtectedDog.getSpDogId())
//                        .build();
//                shelterDogImageRepository.save(shelterDogImage);
//            }
            responseMessage.setMessage("SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
            responseMessage.setMessage("FAIL");
        }
        return responseMessage;
    }

}
