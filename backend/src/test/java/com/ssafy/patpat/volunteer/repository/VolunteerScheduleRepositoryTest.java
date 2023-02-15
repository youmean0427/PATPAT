package com.ssafy.patpat.volunteer.repository;

import com.ssafy.patpat.board.repository.BoardRepository;
import com.ssafy.patpat.common.repository.ImageRepository;
import com.ssafy.patpat.consulting.repository.ConsultingRepository;
import com.ssafy.patpat.consulting.repository.TimeRepository;
import com.ssafy.patpat.shelter.mapping.ShelterDistanceMapping;
import com.ssafy.patpat.shelter.repository.ShelterRepository;
import com.ssafy.patpat.test.TestMapping;
import com.ssafy.patpat.test.TestRepository;
import com.ssafy.patpat.user.entity.User;
import com.ssafy.patpat.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class VolunteerScheduleRepositoryTest {

    @Autowired
    private VolunteerScheduleRepository volunteerScheduleRepository;

    @Autowired
    private VolunteerNoticeRepository volunteerNoticeRepository;

    @Autowired
    private VolunteerReservationRepository volunteerReservationRepository;

    @Autowired
    private ShelterRepository shelterRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private ConsultingRepository consultingRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @Transactional
    void find(){

//
//        List<Integer> ids = new ArrayList<>();
//        ids.add(1);
//        ids.add(2);
//        ids.add(3);
//        ids.add(4);

//        List<VolunteerShelterIdMapping> vl = volunteerScheduleRepository.findShelter(0, "11110");
//// 2 3 1 4 2
//        for (VolunteerShelterIdMapping v:
//             vl) {
//            System.out.println(v.getShelterId());
//            System.out.println(v.getVolunteerDate());
//            System.out.println(v.getName());
//        }
//        PageRequest pageRequest = PageRequest.of(0,4, Sort.by("volunteerDate").ascending());
//        List<VolunteerNotice> list = volunteerNoticeRepository.findWithShelterByShelterShelterIdAndVolunteerDateLikeOrderByVolunteerDateAsc(2,"2023-02%");
//
//        for (VolunteerNotice v:
//             list) {
//            System.out.println(v.getNoticeId());
//        }

//        PageRequest pageRequest = PageRequest.of(0,3);
//        Page<VolunteerNotice> vn = volunteerNoticeRepository.findWithShelterByShelterGugunCodeAndReservationStateCodeAndVolunteerDateGreaterThanOrderByVolunteerDate("11110", Reservation.대기중, LocalDate.now().toString(), pageRequest);
//        PageRequest pageRequest = PageRequest.of(0,3, Sort.by("volunteerDate").ascending());
//        Page<VolunteerReservation> vr = volunteerReservationRepository.findWithUserByUserUserIdAndReservationStateCode(5L, pageRequest);
//        System.out.println(vr.getTotalPages());
//        System.out.println(vr.getTotalElements());
//        for (VolunteerReservation v:
//                vr.toList()) {
//            System.out.println(v.getVolunteerDate() +" "+ v.getReservationId());
//        }
//        List<Integer> t = new ArrayList<>();
//        t.add(2);
//        t.add(3);
//        int hour = LocalDateTime.now().getHour();
//        List<Time> time = timeRepository.findWithShelterByShelterShelterIdAndTimeCodeGreaterThanEqualAndStateNotIn(5, hour, t);
//
//        for (Time ti:
//             time) {
//            System.out.println(ti.getTimeCode());
//        }
//        Optional<Shelter> s = shelterRepository.findById(291);
//        List<Image> images = s.get().getImages();
//
//        for (Image i:
//                images) {
//            System.out.println(i.getImageId());
////            imageRepository.delete(i);
//        }
//
//        List<Image> imageList = new ArrayList<>();
//        for (Image image:
//             images) {
//            Image i = Image.builder()
//                    .filename("d")
//                    .fileSize(2)
//                    .origFilename("d")
//                    .filePath("d")
//                    .build();
//            i = imageRepository.save(i);
//
//            imageList.add(i);
//        }
//        s.get().setImages(imageList);
//        Shelter shelter = shelterRepository.save(s.get());
//        imageList = shelter.getImages();
//
//        for (Image i:
//             imageList) {
//            System.out.println(i.getImageId());
////            imageRepository.delete(i);
//        }

//        Board board = boardRepository.findByBoardId(10L);
//        System.out.println(board);
//        List<ConsultingState> list = new ArrayList<>();
//        list.add(ConsultingState.승인);
//        list.add(ConsultingState.거절);
//        List<TimeCodeMapping> timeCodes = consultingRepository.findByShelterShelterIdAndConsultingDateAndConsultingStateIn(5L, LocalDate.parse("2023-02-02", DateTimeFormatter.ISO_DATE), list);
//        List<Time> times = timeRepository.findByShelterShelterIdAndActiveTrue(291L);
//        Shelter shelter = shelterRepository.findByNameAndRegNumber("경주동물사랑보호센터","20");
//        List<Shelter> list = shelterRepository.findAll();
//        for (Shelter s : list){
//            System.out.println(s.getName());
//        }

//        PageRequest pageRequest = PageRequest.of(0,3, Sort.by("distance").descending());
//        BigDecimal a = new BigDecimal(37.5152937);
//        BigDecimal b = new BigDecimal(126.9013676);
////        Page<ShelterDistanceMapping> test = shelterRepository.findAllShelter(a,b,a, pageRequest);
//        List<TestMapping> test = testRepository.selectAllSQL(a,b,a);
//        System.out.println("here");
//        String d = test.stream()
//                .filter(s -> s.getId() == 1)
//                .findAny()
//                .map(ds -> ds.getDistance())
//                .get();
//        System.out.println(d);
        System.out.println(passwordEncoder.encode("68"));
//        System.out.println(passwordEncoder.encode("18"));
//        System.out.println(passwordEncoder.encode("9"));

//        List<User> users = new ArrayList<>();
//        for (int i = 1; i <= 200 ; i++) {
//            users.add(
//                    User.builder()
//                            .ageRange("20~29")
//                            .email("user"+i+"@patpat.com")
//                            .nickname("user"+i)
//                            .password("user"+i)
//                            .providerId("user")
//                            .provider("user")
//                            .build()
//            );
//        }
//        userRepository.saveAll(users);

//        for (TestMapping t:
//                test) {
////            System.out.println(t.getShelter().getShelterId());
//            System.out.println(t.getId());
//            System.out.println(t.getDistance());
//        }
//        int cnt = userRepository.countByFavorite(2L,3L);
//        System.out.println(cnt);
//

//        System.out.println(shelter.getShelterId());
//        for (Time t:
//                times) {
//            System.out.println(t.getTimeCode().name());
//        }
//        Optional<User> user = userRepository.findWithFavoriteDogsByUserId(3L);
//        List<ShelterProtectedDog> list = user.get().getFavoriteDogs();
//        for (ShelterProtectedDog d:
//                list) {
//            System.out.println(d.getBreedId());
//        }
    }

}