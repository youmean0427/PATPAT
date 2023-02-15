package com.ssafy.patpat.shelter.repository;
import com.ssafy.patpat.report.entity.MissingDog;
import com.ssafy.patpat.common.code.Reservation;
import com.ssafy.patpat.shelter.mapping.ShelterDistanceMapping;
import com.ssafy.patpat.shelter.entity.Shelter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter,Long> {

    List<Shelter> findByGugunCode(String gugunCode);
    List<Shelter> findByShelterIdIn(List<Long> list);
    Page<Shelter> findByShelterIdIn(List<Long> list, PageRequest pageRequest);
    Page<Shelter> findBySidoCodeAndGugunCode(String sidoCode, String gugunCode, PageRequest pageRequest);
    Page<Shelter>  findBySidoCode(String sidoCode,PageRequest pageRequest);
    Shelter findByShelterId(Long shelterId);
    Shelter findByNameAndRegNumber(String name, String regNumber);

//    List<Shelter> findByShelterIdInAndVolunteerNoticeReservationStateCodeAndVolunteerNoticeVolunteerDateBetween(List<Long> shelterIds, Reservation reservation, String date1, String date2);
    @Query(value = "SELECT shelter_id as shelterId, (6371 * acos ( cos ( radians(?) )* cos( radians( latitude ) )* cos( radians( longitude ) - radians(?) )+ sin ( radians(?) ) * sin( radians( latitude )))) AS distance FROM shelter HAVING distance < ? ORDER BY distance asc",nativeQuery = true)
    List<ShelterDistanceMapping> findAllShelter(BigDecimal a, BigDecimal b , BigDecimal c, Integer dist);

    @Query(nativeQuery = true,
            value = "select * , (6371 * acos ( cos ( radians(:a) )* cos( radians( latitude ) )* cos( radians( longitude )" +
                    " - radians(:b) )+ sin ( radians(:c) ) * sin( radians( latitude ))))" +
                    " as distance from shelter group by shelter_id having distance < 15"
    )
    List<Shelter> selectBydistance(BigDecimal a, BigDecimal b , BigDecimal c);

}
