package com.ssafy.patpat;

import com.ssafy.patpat.consulting.dto.ConsultingDto;
import com.ssafy.patpat.consulting.dto.TimeDto;
import com.ssafy.patpat.consulting.entity.Consulting;
import com.ssafy.patpat.consulting.entity.Time;
import com.ssafy.patpat.consulting.repository.ConsultingRepository;
import com.ssafy.patpat.protect.entity.ShelterProtectedDog;
import com.ssafy.patpat.protect.repository.ShelterProtectedDogRepository;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.shelter.repository.GugunRepository;
import com.ssafy.patpat.shelter.repository.ShelterRepository;
import com.ssafy.patpat.shelter.repository.SidoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

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
//		String shelterNm ="(사)하얀비둘기";
//		ResultDto dto = new ResultDto();
//		try{
//			StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1543061/animalShelterSrvc/shelterInfo");
//			urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=4YzbaAQ76Mr8ENklHJNGymdysODMSkne%2Bmi9616VcdzI4KuXMA7ugRh5rvN7HLAgjV1qetFWKEHGzR7XhH4mEA%3D%3D");
//			urlBuilder.append("&" + URLEncoder.encode("care_nm","UTF-8") + "=" + URLEncoder.encode(shelterNm, "UTF-8"));
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
//				dto.setResult(0);
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
//				dto.setResult(0);
//			}
//			else{
//				dto.setResult(0);
//				dto.setAuthCode(passwordEncoder.encode(list.get(0).getRegNumber()));
//			}
//		}catch (Exception e){
//			dto.setResult(0);
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


	}
}
