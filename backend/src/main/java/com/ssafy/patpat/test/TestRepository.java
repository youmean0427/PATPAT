package com.ssafy.patpat.test;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

public interface TestRepository extends JpaRepository<TestDistance,String> {
    @Query(value = "SELECT * , (6371 * acos ( cos ( radians(?) )* cos( radians( lat ) )* cos( radians( log) - radians(?) )+ sin ( radians(?) ) * sin( radians( lat )))) AS distance FROM test",nativeQuery = true)
    public Page<TestMapping> selectAllSQL(BigDecimal a, BigDecimal b , BigDecimal c, PageRequest pageRequest);
}

// HAVING distance < 2 ORDER BY distance LIMIT 0 , 20"
