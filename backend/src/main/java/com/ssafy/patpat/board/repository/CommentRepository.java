package com.ssafy.patpat.board.repository;

import com.ssafy.patpat.board.entity.Board;
import com.ssafy.patpat.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,String> {
    List<Comment> findByboardId(int boardId);
    void deleteByBoardId(int boardId);
    Comment findByCommentId(int commentId);
    void deleteByCommentId(int commentId);
}
