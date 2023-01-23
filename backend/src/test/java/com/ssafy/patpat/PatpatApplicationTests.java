package com.ssafy.patpat;

import com.ssafy.patpat.shelter.Breed;
import com.ssafy.patpat.shelter.dto.RequestParamMbtiDto;
import com.ssafy.patpat.shelter.entity.Gugun;
import com.ssafy.patpat.shelter.entity.Shelter;
import com.ssafy.patpat.shelter.entity.ShelterDog;
import com.ssafy.patpat.shelter.entity.Sido;
import com.ssafy.patpat.shelter.repository.GugunRepository;
import com.ssafy.patpat.shelter.repository.ShelterDogRepository;
import com.ssafy.patpat.shelter.repository.ShelterRepository;
import com.ssafy.patpat.shelter.repository.SidoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
	@Test
	void contextLoads() {
	}

	@Test
	void test(){
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
		//int to Enum
//		List<Breed> breedList = new ArrayList<>();
//		for(Integer i : set){
//			breedList.add(Breed.values()[i]);
//		}

	}
}
