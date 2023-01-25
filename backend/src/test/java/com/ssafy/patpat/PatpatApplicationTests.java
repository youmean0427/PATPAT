package com.ssafy.patpat;

import com.ssafy.patpat.shelter.Breed;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.shelter.repository.GugunRepository;
import com.ssafy.patpat.shelter.repository.ShelterDogRepository;
import com.ssafy.patpat.shelter.repository.ShelterRepository;
import com.ssafy.patpat.shelter.repository.SidoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.sql.SQLOutput;
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
	ShelterDogRepository shelterDogRepository;

	@Autowired
	PasswordEncoder passwordEncoder;
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
		Breed[] arr = Breed.values();
		System.out.println(Arrays.toString(arr));
		Random random = new Random();
		int n = random.nextInt(arr.length);
		Breed breed = Breed.values()[n];
		System.out.println(breed);
	}
}
