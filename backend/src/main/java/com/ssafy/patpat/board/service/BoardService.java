package com.ssafy.patpat.board.service;

import com.ssafy.patpat.board.dto.BoardDto;
import com.ssafy.patpat.board.dto.CommentDto;
import com.ssafy.patpat.board.dto.ReplyDto;
import com.ssafy.patpat.board.dto.RequestBoardDto;
import com.ssafy.patpat.common.dto.ResponseMessage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardService {

    List<BoardDto> selectUserBoardList(RequestBoardDto requestBoardDto);

    List<BoardDto> selectBoardList(RequestBoardDto requestBoardDto);

    BoardDto deatilBoard(int boardId);

    ResponseMessage insertBoard(BoardDto boardDto, List<MultipartFile> uploadFile);

    ResponseMessage updateBoard(int boardId, BoardDto boardDto, List<MultipartFile> uploadFile);

    ResponseMessage deleteBoard(int boardId);

    ResponseMessage insertComment(CommentDto commentDto);

    ResponseMessage updateComment(int commentId, CommentDto commentDto);

    ResponseMessage deleteComment(int commentId);

    ResponseMessage insertReply(ReplyDto replyDto);

    ResponseMessage updateReply(int replyId, ReplyDto replyDto);

    ResponseMessage deleteReply(int replyId);
}
