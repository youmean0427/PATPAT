package com.ssafy.patpat.board.service;

import com.ssafy.patpat.board.dto.*;
import com.ssafy.patpat.common.dto.ResponseListDto;
import com.ssafy.patpat.common.dto.ResponseMessage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardService {

    ResponseListDto selectUserBoardList(RequestBoardDto requestBoardDto);

    ResponseListDto selectBoardList(RequestBoardDto requestBoardDto);

    BoardDto detailBoard(Long boardId);

    ResponseMessage insertBoard(BoardDto boardDto, List<MultipartFile> uploadFile);

    ResponseMessage updateBoard(Long boardId, BoardDto boardDto, List<MultipartFile> uploadFile);

    ResponseMessage deleteBoard(Long boardId);

    ResponseMessage insertComment(CommentDto commentDto);

    ResponseMessage updateComment(Long commentId, CommentDto commentDto);

    ResponseMessage deleteComment(Long commentId);

    ResponseMessage insertReply(ReplyDto replyDto);

    ResponseMessage updateReply(Long replyId, ReplyDto replyDto);

    ResponseMessage deleteReply(Long replyId);
}
