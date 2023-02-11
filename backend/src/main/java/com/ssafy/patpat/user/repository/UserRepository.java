package com.ssafy.patpat.user.repository;

import com.ssafy.patpat.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Entity;
import java.math.BigInteger;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByEmail(String email);

    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByUserId(Long userId);

//    Optional<User> findWithShelterProtectedDogByUserId(Long userId);

    @Query(nativeQuery = true, value = "select count(*) from user u, user_favorite f where u.user_id = f.user_id and u.user_id = ? and f.sp_dog_id = ?")
    Integer countByFavorite(Long userId, Long spDogId);

    @Query(nativeQuery = true, value = "select f.sp_dog_id from user u, user_favorite f where u.user_id = f.user_id and u.user_id = ?")
    Page<BigInteger> findByFavorite(Long userId, PageRequest pageRequest);
}
