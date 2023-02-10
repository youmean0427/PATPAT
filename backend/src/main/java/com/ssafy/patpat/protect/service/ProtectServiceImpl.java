package com.ssafy.patpat.protect.service;

//import com.ssafy.patpat.board.entity.PostImage;
import com.ssafy.patpat.common.code.category.Neutered;
import com.ssafy.patpat.common.code.ProtectState;
import com.ssafy.patpat.common.code.category.*;
import com.ssafy.patpat.common.code.category.Color;
import com.ssafy.patpat.common.dto.FileDto;
import com.ssafy.patpat.common.dto.ResponseListDto;
import com.ssafy.patpat.common.entity.Image;
import com.ssafy.patpat.common.repository.ImageRepository;
import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.common.service.FileService;
import com.ssafy.patpat.protect.dto.ProtectDto;
import com.ssafy.patpat.protect.dto.RequestProtectDto;
import com.ssafy.patpat.protect.entity.ShelterProtectedDog;
//import com.ssafy.patpat.protect.repository.ShelterDogImageRepository;
import com.ssafy.patpat.protect.repository.ShelterProtectedDogRepository;
import com.ssafy.patpat.shelter.dto.ShelterDto;
import com.ssafy.patpat.shelter.entity.Breed;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.shelter.repository.BreedRepository;
import com.ssafy.patpat.shelter.repository.ShelterRepository;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


@Service
public class ProtectServiceImpl implements ProtectService{
    @Value("${app.fileupload.uploadPath}")
    String uploadPath;

    @Value("${app.fileupload.uploadDir}")
    String uploadFolder;

    @Value("${app.filecall.url}")
    String callUrl;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProtectServiceImpl.class);
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
    @Override
    public ResponseListDto selectProtectList(RequestProtectDto requestProtectDto) {
        try{
            ResponseListDto responseListDto = new ResponseListDto();
            PageRequest pageRequest;
            Page<ShelterProtectedDog> shelterProtectedDogList;
            List<ProtectState> filterList = new ArrayList<>();
            filterList.add(ProtectState.입양);
            filterList.add(ProtectState.자연사);
            filterList.add(ProtectState.안락사);
            LOGGER.info("여기와? {} : ",requestProtectDto);
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
                String thumbnail = null;
                if(shelterDogImageList.isEmpty()){
                    thumbnail= fileService.getFileUrl(fileService.getDefaultImage());
                }
                if(shelterDogImageList.size() > 0) {
                    //이미지를 한개 받아온다.
//                    Image image = imageRepository.findByImageId(shelterDogImageList.get(0).getImageId());
                    Image image = shelterDogImageList.get(0);
//                    //파일 디티오에 넣는다.
//                    FileDto fileDto = FileDto.builder()
//                            .origFilename(image.getOrigFilename())
//                            .build();
                    thumbnail=fileService.getFileUrl(image);
                }
//                Breed breed = breedRepository.findByBreedId(s.getBreedId());
                protectDtoList.add(
                        ProtectDto.builder()
                                .protectId(s.getSpDogId())
                                .protectName(s.getName())
                                .stateCode(s.getStateCode().getCode())
                                .state(s.getStateCode().name())
                                .gender(s.getGender().name())
                                .genderCode(s.getGender().getCode())
                                .protectName(s.getName())
                                .thumbnail(thumbnail)
                                .neuteredCode(s.getNeutered())
                                .neutered(Neutered.values()[s.getNeutered()].name())
                                .age(s.getAge())
                                .breedName(s.getBreed().getName())
                                .breedId(s.getBreed().getBreedId())
                                .kg(s.getWeight())
                                .build()

                );
            }
            responseListDto.setList(protectDtoList);
            responseListDto.setTotalCount(shelterProtectedDogList.getTotalElements());
            responseListDto.setTotalPage(shelterProtectedDogList.getTotalPages());
            return  responseListDto;
        }catch(Exception e){
            return null;
        }
    }
    @Override
    public ResponseListDto selectProtectListByShelter(RequestProtectDto requestProtectDto) {
        try{
            ResponseListDto responseListDto = new ResponseListDto();
            PageRequest pageRequest;
            Page<ShelterProtectedDog> shelterProtectedDogList;
            List<ProtectState> filterList = new ArrayList<>();
            filterList.add(ProtectState.입양);
            filterList.add(ProtectState.자연사);
            filterList.add(ProtectState.안락사);
            pageRequest = PageRequest.of(requestProtectDto.getOffSet(),requestProtectDto.getLimit(), Sort.by("registDate").descending());
            shelterProtectedDogList = shelterProtectedDogRepository.findByShelterShelterIdAndStateCodeNotIn(requestProtectDto.getShelterId(), filterList,pageRequest);
            List<ProtectDto> protectDtoList = new ArrayList<>();
            for(ShelterProtectedDog s : shelterProtectedDogList){
                //파일 담을 객체 생성 후 받아오기
                List<Image> shelterDogImageList = s.getImages();
                //만약 파일이 있다면
                String thumbnail = null;
                if(shelterDogImageList.isEmpty()){
                    thumbnail= fileService.getFileUrl(fileService.getDefaultImage());
                }
                if(shelterDogImageList.size() > 0) {
                    //이미지를 한개 받아온다.
//                    Image image = imageRepository.findByImageId(shelterDogImageList.get(0).getImageId());
                    Image image = s.getImages().get(0);
//                    //파일 디티오에 넣는다.
//                    FileDto fileDto = FileDto.builder()
//                            .origFilename(image.getOrigFilename())
//                            .build();
                    thumbnail=fileService.getFileUrl(image);
                }

//                Breed breed = breedRepository.findByBreedId(s.getBreedId());
                protectDtoList.add(
                        ProtectDto.builder()
                                .protectId(s.getSpDogId())
                                .stateCode(s.getStateCode().getCode())
                                .state(s.getStateCode().name())
                                .protectName(s.getName())
                                .thumbnail(thumbnail)
                                .gender(s.getGender().name())
                                .genderCode(s.getGender().getCode())
                                .neuteredCode(s.getNeutered())
                                .neutered(Neutered.values()[s.getNeutered()].name())
                                .age(s.getAge())
                                .breedName(s.getBreed().getName())
                                .breedId(s.getBreed().getBreedId())
                                .kg(s.getWeight())
                                .build()

                );
            }
            responseListDto.setList(protectDtoList);
            responseListDto.setTotalCount(shelterProtectedDogList.getTotalElements());
            responseListDto.setTotalPage(shelterProtectedDogList.getTotalPages());
            return  responseListDto;
        }catch(Exception e){
            return null;
        }

    }
    @Override
    public ProtectDto detailProtect(Long protectId) {
        try{
            ShelterProtectedDog shelterProtectedDog = shelterProtectedDogRepository.findBySpDogId(protectId);
            List<Image> shelterDogImageList = shelterProtectedDog.getImages();
//            List<Integer> iList = new ArrayList<>();
//            for(Image s : shelterDogImageList){
//                iList.add(s.getImageId());
//            }
//            List<Image> imageList = imageRepository.findByImageIdIn(iList);
            List<FileDto> fileDtoList = new ArrayList<>();
            if(!shelterDogImageList.isEmpty()){
                for(Image i : shelterDogImageList){
                    fileDtoList.add(
                            FileDto.builder()
                                    .filePath(fileService.getFileUrl(i))
                                    .build()
                    );
                }
            }

//            Breed breed = breedRepository.findByBreedId(shelterProtectedDog.getBreed().getBreedId());
            ProtectDto protectDto = ProtectDto.builder()
                    .age(shelterProtectedDog.getAge())
                    .protectName(shelterProtectedDog.getName())
                    .protectId(shelterProtectedDog.getSpDogId())
                    .shelterId(shelterProtectedDog.getShelter().getShelterId())
                    .breedId(shelterProtectedDog.getBreed().getBreedId())
                    .breedName(shelterProtectedDog.getBreed().getName())
                    .breedId(shelterProtectedDog.getBreed().getBreedId())
                    .stateCode(shelterProtectedDog.getStateCode().getCode())
                    .state(shelterProtectedDog.getStateCode().name())
                    .gender(shelterProtectedDog.getGender().name())
                    .genderCode(shelterProtectedDog.getGender().getCode())
                    .neuteredCode(shelterProtectedDog.getNeutered())
                    .neutered(Neutered.values()[shelterProtectedDog.getNeutered()].name())
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
    public ResponseMessage insertProtect(ProtectDto protectDto,List<MultipartFile> uploadFile) {
        ResponseMessage responseMessage = new ResponseMessage();
        Shelter shelter = shelterRepository.findByShelterId(protectDto.getShelterId());
        Breed breed = breedRepository.findByBreedId(protectDto.getBreedId());
        System.out.println(protectDto);
        try {
            List<Image> images = new ArrayList<>();
            if(!uploadFile.isEmpty()){
                for (MultipartFile partFile : uploadFile) {
                    Image image = fileService.insertFile(partFile, "protect");
                    images.add(image);
                }
            }

            ShelterProtectedDog shelterProtectedDog = ShelterProtectedDog.builder()
                    .age(protectDto.getAge())
                    .breed(breed)
                    .shelter(shelter)
                    .feature(protectDto.getInfoContent())
                    .gender(Gender.of(protectDto.getGenderCode()))
                    .neutered(protectDto.getNeuteredCode())
                    .images(images)
                    .registDate(LocalDateTime.now().toLocalDate())
                    //.findingDate(protectDto.getFindingDate())
                    .findingDate(LocalDate.now())
                    .latitude(shelter.getLatitude())
                    .longitude(shelter.getLongitude())
                    .name(protectDto.getProtectName())
                    .weight(protectDto.getKg())
//                    .stateCode(ProtectState.of(protectDto.getStateCode()))
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
//            File uploadDir = new File(uploadPath + File.separator + uploadFolder);
//            if (!uploadDir.exists()) uploadDir.mkdir();
//            System.out.println(uploadFile.size());


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
    public ResponseMessage insertBatchesProtect(ShelterDto shelterDto, MultipartFile uploadFile) throws IOException {
        ResponseMessage responseMessage = new ResponseMessage();
        HashMap<String, String> map = new HashMap<>();
        map.put("적색", "R");
        map.put("녹색", "G");
        map.put("청색", "B");
        map.put("자색", "V");
        map.put("황색", "Y");
        map.put("청록", "E");
        map.put("흰색", "W");
        map.put("회색", "H");
        map.put("검정", "K");
        Workbook workbook = null;
        try {
            //보호소 정보 불러오기
            Shelter shelter = shelterRepository.findByShelterId(shelterDto.getShelterId());
            long shelterId = shelter.getShelterId();
            BigDecimal lat = shelter.getLatitude();
            BigDecimal log = shelter.getLongitude();
            String extension = FilenameUtils.getExtension(uploadFile.getOriginalFilename()); // 3
            if (!extension.equals("xlsx") && !extension.equals("xls") && !extension.equals("xlsm")) {
                return new ResponseMessage("엑셀파일만 업로드 해주세요");
            }

            if (extension.equals("xlsx")) {
                workbook = new XSSFWorkbook(uploadFile.getInputStream());
                System.out.println("xlsx");
            } else if (extension.equals("xls")) {
                workbook = new XSSFWorkbook(uploadFile.getInputStream());
                System.out.println("xls");
            }
            else if (extension.equals("xlsm")) {
                workbook = new XSSFWorkbook(uploadFile.getInputStream());
                System.out.println("xlsm");
            }

            Sheet sheet = workbook.getSheetAt(0);
            System.out.println(sheet.getSheetName());
            List<ShelterProtectedDog> list = new ArrayList<>();
            for(Row row : sheet){
                //실제 값이 들어있는 로우
                if(row.getRowNum() > 7){
                    Iterator<Cell> cellIterator =row.cellIterator();
                    int col = 0;
                    ShelterProtectedDog shelterProtectedDog = new ShelterProtectedDog();
                    ProtectDto protectDto = new ProtectDto();
                    ArrayList<String> strList = new ArrayList<>();
                    while(cellIterator.hasNext()){
                        Cell cell = cellIterator.next();
                        switch (col){
                            case 0 :
                                Breed breed = breedRepository.findByName(cell.getStringCellValue());
                                shelterProtectedDog.setBreed(breed);
                                break;
                            case 1 :
                                shelterProtectedDog.setName(cell.getStringCellValue());
                                break;
                            case 2 :
                                shelterProtectedDog.setAge((int)cell.getNumericCellValue());
                                break;
                            case 3 :
                                shelterProtectedDog.setGender(Gender.valueOf(cell.getStringCellValue()));
                                break;
                            case 4 :
                                shelterProtectedDog.setWeight(cell.getNumericCellValue());
                                break;
                            case 5 :
                                shelterProtectedDog.setNeutered(Neutered.valueOf(cell.getStringCellValue()).ordinal());
                                break;
                            case 6 :
                                if(cell.getStringCellValue().equals("없음")){
                                    break;
                                }
                                else{
                                    strList.add(map.get(cell.getStringCellValue()));
                                }
                                break;
                            case 7 :
                                if(cell.getStringCellValue().equals("없음")){
                                    break;
                                }
                                else{
                                    strList.add(map.get(cell.getStringCellValue()));
                                }
                                break;
                            case 8 :
                                if(cell.getStringCellValue().equals("없음")){
                                    break;
                                }
                                else{
                                    strList.add(map.get(cell.getStringCellValue()));
                                }
                                break;
                            case 9 :
                                shelterProtectedDog.setCategoryPattern(Pattern.valueOf(cell.getStringCellValue()).ordinal());
                                break;
                            case 10 :
                                shelterProtectedDog.setCategoryEar(Ear.valueOf(cell.getStringCellValue()).ordinal());
                                break;
                            case 11 :
                                shelterProtectedDog.setCategoryTail(Tail.valueOf(cell.getStringCellValue()).ordinal());
                                break;
                            case 12 :
                                shelterProtectedDog.setCategoryCloth(Cloth.valueOf(cell.getStringCellValue()).ordinal());
                                break;
                            case 13 :
                                shelterProtectedDog.setFeature(cell.getStringCellValue());
                                break;
                        }
                        col++;
                    }
                    List<Image> images = new ArrayList<>();
                    shelterProtectedDog.setSidoCode(shelter.getSidoCode());
                    shelterProtectedDog.setGugunCode(shelter.getGugunCode());
                    shelterProtectedDog.setLatitude(lat);
                    shelterProtectedDog.setLongitude(log);
                    shelterProtectedDog.setShelter(shelter);
                    shelterProtectedDog.setStateCode(ProtectState.공고중);
                    shelterProtectedDog.setRegistDate(LocalDate.now());
                    shelterProtectedDog.setImages(images);
                    Collections.sort(strList);
                    StringBuilder sb = new StringBuilder();
                    for(int i=0; i<strList.size(); i++){
                        sb.append(strList.get(i));
                    }
                    int code = Color.valueOf(sb.toString()).getCode();
                    shelterProtectedDog.setCategoryColor(code);
                    list.add(shelterProtectedDog);
                }
            }
            //리스트에 동물이 들어간 이후 등록
            for(ShelterProtectedDog dog : list){
                shelterProtectedDogRepository.save(dog);
            }
            //만약 10번에 등록된 강아지다 ~ 8개 등록되어있는 상황이면 18번까지있음
            long startIdx = list.get(0).getSpDogId();
            XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch(); // I know it is ugly, actually you get the actual instance here

            for (XSSFShape shape : drawing.getShapes()) {
                if (shape instanceof XSSFPicture) {
                    XSSFPicture picture = (XSSFPicture) shape;

                    if (picture.getPictureData() == null) {
                        System.out.println("사진 Path 사용");
                        continue;
                    }
                    XSSFPictureData xssfPictureData = picture.getPictureData();
                    ClientAnchor anchor = picture.getPreferredSize();
                    int row1 = anchor.getRow1();
                    int row2 = anchor.getRow2();
                    int col1 = anchor.getCol1();
                    int col2 = anchor.getCol2();

                    long dogId = startIdx+row1-8;
                    String ext = xssfPictureData.suggestFileExtension();
                    byte[] data = xssfPictureData.getData();
                    int size = data.length;

                    UUID uuid = UUID.randomUUID();

                    String savingFileName = uuid+"."+ext;
                    String source = "protect";
                    String pathName = uploadPath + File.separator + uploadFolder + File.separator + source;
                    File uploadDir = new File(pathName);
                    if (!uploadDir.exists()) uploadDir.mkdir();
                    String filePath = pathName + File.separator + savingFileName;
                    FileOutputStream out = new FileOutputStream(filePath);
                    out.write(data);
                    out.close();
                    Image image = Image.builder()
                            .filename(sheet.getSheetName())
                            .fileSize(size)
                            .filePath(uploadFolder + "/" + source + "/" + savingFileName)
                            .origFilename(sheet.getSheetName()+row1+col1)
                            .build();
                    imageRepository.save(image);
                    Optional<ShelterProtectedDog> shelterProtectedDog = shelterProtectedDogRepository.findById(dogId);
                    if(shelterProtectedDog.isPresent()){
                        List<Image> images = shelterProtectedDog.get().getImages();
                        images.add(image);
                        shelterProtectedDogRepository.save(shelterProtectedDog.get());
                    }
                }
            }
            workbook.close();
            responseMessage.setMessage("SUCCESS");

            return responseMessage;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            workbook.close();
            return responseMessage;
        }
    }

        @Override
    @Transactional
    public ResponseMessage updateProtect(Long protectId, List<MultipartFile> uploadFile,ProtectDto protectDto) {
        ResponseMessage responseMessage = new ResponseMessage();
        try{

            ShelterProtectedDog shelterProtectedDog = shelterProtectedDogRepository.findBySpDogId(protectId);

            List<Image> images = shelterProtectedDog.getImages();

//            File uploadDir = new File(uploadPath +File.separator+uploadFolder);
//            if(!uploadDir.exists()) uploadDir.mkdir();
//            List<Integer> list = new ArrayList<>();
//            for(Image i : shelterDogImageList){
//                list.add(i.getImageId());
//            }
//            List<Image> imageList = imageRepository.findByImageIdIn(list);
            for(Image i : images){
//                File file = new File(uploadPath+File.separator+i.getFilePath());
//                if(file.exists()) file.delete();
                fileService.deleteFile(i);
            }
            images.removeAll(images);

            for (MultipartFile partFile : uploadFile) {
                Image image = fileService.insertFile(partFile, "protect");
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
