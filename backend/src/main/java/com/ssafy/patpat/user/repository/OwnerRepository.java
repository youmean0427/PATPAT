package com.ssafy.patpat.user.repository;

import com.ssafy.patpat.user.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
