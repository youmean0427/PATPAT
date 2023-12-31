package com.ssafy.patpat.consulting.repository;

import com.ssafy.patpat.common.code.ConsultingState;
import com.ssafy.patpat.common.code.TimeCode;
import com.ssafy.patpat.consulting.entity.Consulting;
import com.ssafy.patpat.consulting.mapping.TimeCodeMapping;
import com.ssafy.patpat.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConsultingRepository extends JpaRepository<Consulting, Long> {
    Page<Consulting> findByUserAndConsultingDateGreaterThanEqual(User user, LocalDate consultingDate, PageRequest pageRequest);
    Page<Consulting> findByUserAndConsultingDateGreaterThanEqualAndConsultingState(User user, LocalDate consultingDate, ConsultingState consultingState, PageRequest pageRequest);
    Page<Consulting> findByShelterShelterIdAndConsultingDateGreaterThanEqual(Long shelterId, LocalDate consultingDate, PageRequest pageRequest);
    Page<Consulting> findByShelterShelterIdAndConsultingDateGreaterThanEqualAndConsultingState(Long shelterId, LocalDate consultingDate, ConsultingState consultingState, PageRequest pageRequest);
    Consulting findByConsultingId(Long consultingId);
    List<TimeCodeMapping> findByShelterShelterIdAndConsultingDateAndConsultingStateIn(Long shelterId, LocalDate localDate, List<ConsultingState> consultingStates);


}
