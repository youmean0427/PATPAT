package com.ssafy.patpat.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

public interface TestRepository extends JpaRepository<TestDistance,String> {
    @Query(value = "SELECT * , (6371 * acos ( cos ( radians(?) )* cos( radians( lat ) )* cos( radians( log) - radians(?) )+ sin ( radians(?) ) * sin( radians( lat )))) AS distance FROM test HAVING distance < 2 ORDER BY distance LIMIT 0 , 20",nativeQuery = true)
    public List<TestDistance> selectAllSQL(BigDecimal a, BigDecimal b , BigDecimal c);
}
