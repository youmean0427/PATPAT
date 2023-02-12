package com.ssafy.patpat.test;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

public interface TestRepository extends JpaRepository<TestDistance,String> {
    @Query(value = "select * , (6371 * acos ( cos ( radians(:a) )* cos( radians( lat ) )* cos( radians( log) - radians(:b) )+ sin ( radians(:c) ) * sin( radians( lat )))) as distance from test having distance < 2",nativeQuery = true)
    public List<TestMapping> selectAllSQL(BigDecimal a, BigDecimal b , BigDecimal c);
}

//, PageRequest pageRequest
// HAVING distance < 2 ORDER BY distance LIMIT 0 , 20"
