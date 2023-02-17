package com.ssafy.patpat.board.repository;

import com.ssafy.patpat.board.entity.Board;
import com.ssafy.patpat.common.code.BoardCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long>  {
    Board findByBoardId(long boardId);
    Page<Board> findByUserUserId(long userId, PageRequest pageRequest);
    Page<Board> findByUserUserIdAndBoardCode(long userId, BoardCode postCode, PageRequest pageRequest);
    Page<Board> findByBoardCode(BoardCode postCode, PageRequest pageRequest);

    void deleteByBoardId(Long boardId);
}
