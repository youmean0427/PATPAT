package com.ssafy.patpat.board.repository;

import com.ssafy.patpat.board.entity.Board;
import com.ssafy.patpat.board.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ImageRepository extends JpaRepository<Image,String>  {
    Image findByImageId(int imageId);
    List<Image> findByImageIdIn(List<Integer> list);
    void deleteByImageIdIn(List<Integer> list);
}
