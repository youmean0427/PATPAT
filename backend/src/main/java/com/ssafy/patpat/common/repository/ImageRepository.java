package com.ssafy.patpat.common.repository;

import com.ssafy.patpat.common.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ImageRepository extends JpaRepository<Image,Integer>  {
    Image findByImageId(int imageId);
    List<Image> findByImageIdIn(List<Integer> list);
    void deleteByImageIdIn(List<Integer> list);
}
