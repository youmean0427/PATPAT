package com.ssafy.patpat.board.repository;

import com.ssafy.patpat.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long>  {
    Board findByBoardId(long boardId);
    Page<Board> findByUserId(long userId, PageRequest pageRequest);
    Page<Board> findByUserIdAndPostCode(long userId, int postCode, PageRequest pageRequest);
    Page<Board> findByPostCode(int postCode, PageRequest pageRequest);

    void deleteByBoardId(Long boardId);
}
