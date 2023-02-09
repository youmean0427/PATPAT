package com.ssafy.patpat;

import com.ssafy.patpat.common.code.category.Neutered;
import com.ssafy.patpat.common.code.ProtectState;
import com.ssafy.patpat.common.code.category.*;
import com.ssafy.patpat.common.entity.Image;
import com.ssafy.patpat.common.repository.ImageRepository;
import com.ssafy.patpat.consulting.repository.ConsultingRepository;
//import com.ssafy.patpat.protect.entity.ShelterDogImage;
import com.ssafy.patpat.protect.entity.ShelterProtectedDog;
//import com.ssafy.patpat.protect.repository.ShelterDogImageRepository;
import com.ssafy.patpat.protect.repository.ShelterProtectedDogRepository;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.shelter.repository.BreedRepository;
import com.ssafy.patpat.shelter.repository.GugunRepository;
import com.ssafy.patpat.shelter.repository.ShelterRepository;
import com.ssafy.patpat.shelter.repository.SidoRepository;
import com.ssafy.patpat.test.TestDistance;
import com.ssafy.patpat.test.TestRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

@SpringBootTest
class PatpatApplicationTests {
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
	ConsultingRepository consultingRepository;
	@Autowired
	TestRepository testRepository;
	@Autowired
	BreedRepository breedRepository;
//	@Autowired
//	PostImageRepository postImageRepository;
	@Autowired
	ImageRepository imageRepository;
//	@Autowired
//	ShelterDogImageRepository shelterDogImageRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void test() throws IOException {
		//총 리스트 뽑기
//		List<Shelter> list = shelterRepository.findAll();
//		for(Shelter s : list) {
//			System.out.println(s);
//		}
		//총 갯수
//		Long count = shelterRepository.count();
//		System.out.println(count);
		//시도별
//		List<Shelter> list = shelterRepository.findBySidoCode("02");
//		for(Shelter s : list) {
//			System.out.println(s);
//		}
		//시도,구군별
//		List<Shelter> list = shelterRepository.findBySidoCodeAndGugunCode("02","17");
//		for(Shelter s : list) {
//			System.out.println(s);
//		}
		//시도 리스트
//		List<Sido> list = sidoRepository.findAll();
//		for(Sido s : list){
//			System.out.println(s);
//		}

		//구군 리스트 (시도 선택)
//		List<Gugun> list = gugunRepository.findBySidoCode("11");
//		for(Gugun g : list){
//			System.out.println(g);
//		}

		//시도, 구군, 견종 받아서 해당 위치 가져오기
//		List<ShelterDog> list = shelterDogRepository.findByBreedIdAndSidoCodeAndGugunCode(1,"26","26230");
//		HashSet<Integer> set = new HashSet<>();
//		for(ShelterDog s : list){
//			set.add(s.getShelterId());
//		}
//		List<Shelter> list2 = shelterRepository.findByShelterIdIn(set);
//		for(Shelter s :list2){
//			System.out.println(s);
//		}

		//enum 사용법
//		RequestParamMbtiDto dto = new RequestParamMbtiDto("믹스견","47","47113");
//		Breed breed = Breed.valueOf(dto.getBreedName());
//		System.out.println(breed.ordinal());

		//시도별 견종 리스트 받아오기
//		List<ShelterDog> list = shelterDogRepository.findBySidoCode("47");
//		HashSet<Integer> set = new HashSet<>();
//		for(ShelterDog s : list){
//			set.add(s.getBreedId());
//		}
//		for(Integer i : set){
//			System.out.println(i);
//		}

		//int to Enum 사용법
//		List<Breed> breedList = new ArrayList<>();
//		for(Integer i : set){
//			breedList.add(Breed.values()[i]);
//		}

		//보호소 등록 샘플
		//String shelterNm ="(사)하얀비둘기";
//		ResultDto dto = new ResultDto();
//		try{
//			StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1543061/animalShelterSrvc/shelterInfo");
//			urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=4YzbaAQ76Mr8ENklHJNGymdysODMSkne%2Bmi9616VcdzI4KuXMA7ugRh5rvN7HLAgjV1qetFWKEHGzR7XhH4mEA%3D%3D");
//			urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("300", "UTF-8"));
//			urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
//			URL url = new URL(urlBuilder.toString());
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setRequestMethod("GET");
//			conn.setRequestProperty("Content-type", "application/json");
//			System.out.println("Response code: " + conn.getResponseCode());
//			BufferedReader rd = null;
//			if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//				rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
//			} else {
//				dto.setResult("ㅇ");
//			}
//			StringBuilder sb = new StringBuilder();
//			String line;
//			while ((line = rd.readLine()) != null) {
//				sb.append(line);
//			}
//			ObjectMapper objectMapper = new ObjectMapper();
//			Map<String, Object> map = new HashMap<>();
//			map = objectMapper.readValue(sb.toString(), new TypeReference<Map<String, Object>>() {});
//			Map<String, Object> response = (Map<String,Object>) map.get("response");
//			Map<String, Object> body = (Map<String,Object>) response.get("body");
//			Map<String, Object> items = (Map<String,Object>) body.get("items");
//			List<Map<String,Object>> itemList = (List<Map<String, Object>>) items.get("item");
//			List<Shelter> list = new ArrayList<>();
//			String str = null;
//			for(Map<String,Object> item:itemList){
//				System.out.println(item);
//				Shelter s = Shelter.builder()
//						.address(item.get("careAddr").toString())
//						.latitude(item.get("lat").toString())
//						.longitude(item.get("lng").toString())
//						.phoneNum(item.get("careTel").toString())
//						.name(item.get("careNm").toString()).build();
//				str = (item.get("jibunAddr").toString());
//				list.add(s);
//			}
//			String[] arr = str.split(" ");
//			System.out.println(Arrays.toString(arr));
//
//			Sido sido = sidoRepository.findByName(arr[0]);
//			list.get(0).setSidoCode(sido.getCode());
//			Gugun gugun = gugunRepository.findBySidoCodeAndName(sido.getCode(),arr[1]);
//			list.get(0).setGugunCode(gugun.getCode());
//			list.get(0).setRegNumber(LocalDateTime.now().toString());
//			Shelter shelter = shelterRepository.save(list.get(0));
//			rd.close();
//			conn.disconnect();
//			if(shelter.equals(null)){
//				//dto.setResult(0);
//			}
//			else{
//				dto.setResult("0");
//				//dto.setAuthCode(passwordEncoder.encode(list.get(0).getRegNumber()));
//			}
//		}catch (Exception e){
//			e.printStackTrace();
//			dto.setResult("0");
//		}
		//랜덤뽑기
//		Breed[] arr = Breed.values();
//		System.out.println(Arrays.toString(arr));
//		Random random = new Random();
//		int n = random.nextInt(arr.length);
//		Breed breed = Breed.values()[n];
//		System.out.println(breed);
//		List<Integer> list = new ArrayList<>();
//		list.add(4);
//		list.add(5);
//		list.add(6);
//
//		//List<ShelterProtectedDog> test = repository.findByStateCodeNotIn(list, PageRequest.of(0, 1));
//		//System.out.println("test = " + test);
//		List<ShelterProtectedDog> test = repository.findByShelterIdAndStateCodeNotIn(1,list, PageRequest.of(0, 1));
//		System.out.println("test = " + test);

	}
	@Test
	void dummy(){
		//보호소 집어넣기
//		int sido = 50;
//		String str = "제추특별자치도";
//		for(int i=1; i<12; i++){
//			shelterRepository.save(
//					Shelter.builder()
//							.name("테스트보호소"+i)
//							.address(str)
//							.sidoCode(String.valueOf(sido))
//							.gugunCode(sido+"120")
//							.latitude("33.222323")
//							.longitude("34.23232")
//							.regNumber("22-2222323")
//							.phoneNum("000-0000")
//							.build()
//			);
//		}
		//강아지 집어넣기
		//견종 하나씩 집어넣자 각 보호소 마다
//		Random random = new Random();
//			for(int i=1; i<316; i++){
//				Shelter s = shelterRepository.findByShelterId(i);
//				for(int j=1; j<17; j++) {
//					shelterProtectedDogRepository.save(
//									ShelterProtectedDog.builder()
//											.name("견훈"+(i*j))
//											.latitude(s.getLatitude())
//											.longitude(s.getLongitude())
//											.age(2)
//											.breedId(j)
//											.shelterId(i)
//											.weight(1.2)
//											.sidoCode(s.getSidoCode())
//											.gugunCode(s.getGugunCode())
//											.gender(random.nextInt(1))
//											.neutered(random.nextInt(1))
//											.registDate(LocalDate.now())
//											.feature("테스트 강아지 입니당")
//											.stateCode(0)
//											.categoryEar(0)
//											.categoryCloth(0)
//											.categoryClothColor(0)
//											.categoryTail(0)
//											.categoryColor(0)
//											.categoryPattern(0)
//											.findingDate(LocalDate.now())
//											.build()
//
//					);
//				}
//			}

		}
	@Test
	@Transactional
	void consulting() {
//		Consulting consulting = Consulting.builder()
//				.userId(1)
//				.spDogId(2)
//				.shelterId(2)
//				.stateCode(0)
//				.registDate(LocalDate.now())
//				.timeCode(0)
//				.build();
//		consultingRepository.save(consulting);
//

//		Shelter shelter = shelterRepository.findByShelterId(5);
//		List<Integer> list = new ArrayList<>();
//
//		for(Time t : shelter.getTimeList()){
//			if(t.getState() == 1){
//				list.add(t.timeCode);
//			}
//		}
//		List<Consulting> consultings = consultingRepository.findByShelterIdAndRegistDate(shelter.getShelterId(),LocalDate.now());
//		int hour = LocalDateTime.now().getHour();
//
//		for(Consulting c : consultings){
//			if(!(c.getStateCode()==2 || c.getStateCode()==3)){
//				list.remove(Integer.valueOf(c.getTimeCode()));
//			}
//		}
//		System.out.println(list);
//		for(int i=0; i<list.size(); i++){
//			if(list.get(i)==0){
//				if(list.get(i)+10 <= hour) {
//					list.remove(Integer.valueOf(list.get(i)));
//					i--;
//				}
//			}
//			else{
//
//				if(list.get(i)+13 <= hour){
//					list.remove(Integer.valueOf(list.get(i)));
//					i--;
//				}
//			}
//		}
//		System.out.println(list);
//		List<TimeDto> timeDtoList = new ArrayList<>();
//		for(Integer i : list){
//			timeDtoList.add(new TimeDto(i));
//		}
//		System.out.println(timeDtoList);
		BigDecimal a = new BigDecimal(37.5152937);
		BigDecimal b = new BigDecimal(126.9013676);
		List<TestDistance> test = testRepository.selectAllSQL(a,b,a);
		System.out.println("here");
		System.out.println(test);

		System.out.println(passwordEncoder.matches("175","$2a$10$VOy/116s7ztl6fcGsh.C7.pYAinRybRqy4B8Q9OSm5fHQnvwNwH2G"));

	}

	@Test
	public void excelTest() throws Exception{
		HashMap<String,String> map = new HashMap<>();
		map.put("적색","R");
		map.put("녹색","G");
		map.put("청색","B");
		map.put("자색","V");
		map.put("황색","Y");
		map.put("청록","E");
		map.put("흰색","W");
		map.put("회색","H");
		map.put("검정","K");
		try {
			FileInputStream inputStream = new FileInputStream("C:\\test\\PATPAT_일괄등록_양식_폼.xlsx");
			System.out.println(inputStream);
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			//0번 시트 열기
			XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
			//보호견 리스트 생성
			List<ShelterProtectedDog> list = new ArrayList<>();
			//보호소 불러오기
			Shelter shelter = shelterRepository.findByShelterId(299L);
			long shelterId = shelter.getShelterId();
			BigDecimal lat = shelter.getLatitude();
			BigDecimal log = shelter.getLongitude();
			//로우마다
			for(Row row : sheet){
				//실제 값이 들어있는 로우
				if(row.getRowNum() > 7){
					Iterator<Cell> cellIterator =row.cellIterator();
					int col = 0;
					ShelterProtectedDog shelterProtectedDog = new ShelterProtectedDog();
					ArrayList<String> strList = new ArrayList<>();
					while(cellIterator.hasNext()){
						Cell cell = cellIterator.next();
						switch (col){
							case 0 :
								System.out.println(cell.getStringCellValue());
								shelterProtectedDog.setBreedId(breedRepository.findByName(cell.getStringCellValue()).getBreedId());
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
								System.out.println(shelterProtectedDog.getCategoryPattern());
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
					shelterProtectedDog.setSidoCode(shelter.getSidoCode());
					shelterProtectedDog.setGugunCode(shelter.getGugunCode());
					shelterProtectedDog.setLatitude(lat);
					shelterProtectedDog.setLongitude(log);
//					shelterProtectedDog.setShelterId(shelterId);
					shelterProtectedDog.setStateCode(ProtectState.공고중);
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
			System.out.println(list.size());
			for(ShelterProtectedDog dog : list){
				shelterProtectedDogRepository.save(dog);
			}
			long startIdx = list.get(0).getSpDogId();
			XSSFDrawing drawing = sheet.createDrawingPatriarch(); // I know it is ugly, actually you get the actual instance here
			for (XSSFShape shape : drawing.getShapes()) {
				System.out.println("dd");
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
					System.out.println("Row1: " + row1 + " Row2: " + row2);
					System.out.println("Column1: " + col1 + " Column2: " + col2);

					//보호동물 id
					//int protectId = startIdx+row1-5;
					//Image image = Image.builder();


					// Saving the file
					String ext = xssfPictureData.suggestFileExtension();
					byte[] data = xssfPictureData.getData();

					FileOutputStream out = new FileOutputStream(String.format("%s\\%s_%d_%d.%s", "C:\\test", sheet.getSheetName(), row1, col1, ext));
					out.write(data);
					out.close();
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		}

		String ext = xssfPictureData.suggestFileExtension();
		byte[] data = xssfPictureData.getData();
		int size = data.length;


		String s = String.format("\\%s\\%s_%d_%d.%s", uploadPath,uploadFolder, sheet.getSheetName(), row1, col1, ext);
		FileOutputStream out = new FileOutputStream(s);
		out.write(data);
		out.close();
		Image image = Image.builder()
				.filename(sheet.getSheetName())
				.fileSize(size)
				.filePath(s)
				.build();
	}
}
