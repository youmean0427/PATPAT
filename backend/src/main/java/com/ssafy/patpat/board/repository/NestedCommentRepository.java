package com.ssafy.patpat.board.repository;

import com.ssafy.patpat.board.entity.Board;
import com.ssafy.patpat.board.entity.Comment;
import com.ssafy.patpat.board.entity.NestedComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NestedCommentRepository extends JpaRepository<NestedComment,Long> {
    List<NestedComment> findByCommentCommentId(long commentId);
    void deleteByCommentCommentIdIn(List<Long> list);
    void deleteByCommentCommentId(long commentId);
    void deleteByNestedCommentId(long nestedCommentId);
    NestedComment findByNestedCommentId(long nestedCommentId);
}
