package com.ssafy.patpat.common.repository;

import com.ssafy.patpat.common.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ImageRepository extends JpaRepository<Image,Long>  {
    Image findByImageId(Long imageId);
    List<Image> findByImageIdIn(List<Long> list);
    void deleteByImageIdIn(List<Long> list);
}
