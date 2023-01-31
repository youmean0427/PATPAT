package com.ssafy.patpat.consulting.repository;

import com.ssafy.patpat.consulting.entity.Consulting;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultingRepository extends JpaRepository<Consulting, String> {
    List<Consulting> findByUserId(int userId, PageRequest pageRequest);
    List<Consulting> findByShelterId(int shelterId, PageRequest pageRequest);
    Consulting findByConsultingId(int consultingId);

}
