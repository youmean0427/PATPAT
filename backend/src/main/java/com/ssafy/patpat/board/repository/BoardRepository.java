package com.ssafy.patpat.board.repository;

import com.ssafy.patpat.board.entity.Board;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board,String>  {
    Board findByBoardId(int boardId);
    List<Board> findByUserId(long userId, PageRequest pageRequest);
    List<Board> findByUserIdAndPostCode(long userId, int postCode, PageRequest pageRequest);
    List<Board> findByPostCode(int postCode, PageRequest pageRequest);

    void deleteByBoardId(int boardId);
}
