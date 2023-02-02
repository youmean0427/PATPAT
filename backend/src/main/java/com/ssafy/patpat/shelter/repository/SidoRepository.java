package com.ssafy.patpat.shelter.repository;

import com.ssafy.patpat.shelter.entity.Gugun;
import com.ssafy.patpat.shelter.entity.Sido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SidoRepository  extends JpaRepository<Sido,String> {
    @Override
    List<Sido> findAll();
    Sido findByName(String name);
}
