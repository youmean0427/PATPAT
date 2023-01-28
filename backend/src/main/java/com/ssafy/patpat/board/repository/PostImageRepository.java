package com.ssafy.patpat.board.repository;

import com.ssafy.patpat.board.entity.Board;
import com.ssafy.patpat.board.entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostImageRepository extends JpaRepository<PostImage,String>  {
    List<PostImage> findByBoardId(int boardId);
}
