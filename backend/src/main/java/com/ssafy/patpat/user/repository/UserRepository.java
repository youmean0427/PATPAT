package com.ssafy.patpat.user.repository;

import com.ssafy.patpat.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
