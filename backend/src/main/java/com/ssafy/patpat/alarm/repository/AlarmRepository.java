package com.ssafy.patpat.alarm.repository;

import com.ssafy.patpat.alarm.entity.Alarm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    Page<Alarm> findByUserUserId(Long userId, PageRequest pageRequest);
    List<Alarm> findByUserUserId(Long userId);

    Integer countByUserUserIdAndCheckRead(Long userId, Boolean checkRead);
}
