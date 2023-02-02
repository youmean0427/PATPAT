package com.ssafy.patpat.consulting.repository;

import com.ssafy.patpat.consulting.entity.Consulting;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConsultingRepository extends JpaRepository<Consulting, String> {
    List<Consulting> findByUserIdAndRegistDateGreaterThanEqual(int userId, PageRequest pageRequest);
    List<Consulting> findByShelterIdAndRegistDateGreaterThanEqual(int shelterId, LocalDate localDate, PageRequest pageRequest);
    Consulting findByConsultingId(int consultingId);
    List<Consulting> findByShelterIdAndRegistDate(int shelterId, LocalDate localDate);


}
