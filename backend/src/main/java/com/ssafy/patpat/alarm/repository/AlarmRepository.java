package com.ssafy.patpat.alarm.repository;

import com.ssafy.patpat.alarm.entity.Alarm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    Page<Alarm> findByUserUserId(Long userId, PageRequest pageRequest);
}
