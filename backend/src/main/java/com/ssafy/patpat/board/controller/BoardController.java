package com.ssafy.patpat.board.controller;

import com.ssafy.patpat.board.dto.*;
import com.ssafy.patpat.board.service.BoardService;
import com.ssafy.patpat.common.dto.ResponseListDto;
import com.ssafy.patpat.common.dto.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/boards")
@Api(tags = {"01. Board"},description = "게시판 관련 서비스")
@ApiResponses({
        @ApiResponse(code=200, message = "성공"),
        @ApiResponse(code=400, message = "에러")
})
public class BoardController {
    @Autowired
    BoardService service;
    /**
     * 전체 게시판 리스트를 리턴한다.(카테고리)
     * @return
     */
    @GetMapping()
    @ApiOperation(value = "게시판 리스트", notes = "게시판 리스트를 조회한다.")
    public ResponseEntity<Object> selectUserBoardList(RequestBoardDto requestBoardDto){
        //service 호출
//        System.out.println(requestBoardDto);
        ResponseListDto boardDtoList = service.selectBoardList(requestBoardDto);

        return ResponseEntity.status(HttpStatus.OK).body(boardDtoList);

    }

    /**
     * 내가 쓴 게시판 리스트를 리턴한다.
     * @return
     */
    @GetMapping("/me")
    @ApiOperation(value = "게시판 리스트", notes = "내가 쓴 게시판 리스트")
    public ResponseEntity<Object> selectBoardList(RequestBoardDto requestBoardDto){
        //service 호출
        ResponseListDto boardDtoList = service.selectUserBoardList(requestBoardDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(boardDtoList);
    }
    /**
     * 게시판 상세 화면 (카테고리별)
     * @return
     */
    @GetMapping("/{boardId}")
    @ApiOperation(value = "게시판 상세", notes = "게시판 상세를 조회한다.")
    public ResponseEntity<Object> detailBoard(@PathVariable Long boardId){
        //service 호출
        BoardDto boardDto = service.detailBoard(boardId);
        if(boardDto!=null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(boardDto);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 게시판 등록하기
     * @return
     */
    @PostMapping()
    @ApiOperation(value = "게시판 등록", notes = "게시판 등록.")
    public ResponseEntity<ResponseMessage> insertBoard(BoardDto boardDto, @RequestPart(required = false)  List<MultipartFile> uploadFile){
        //service 호출
        System.out.println(boardDto);
        ResponseMessage responseMessage = service.insertBoard(boardDto,uploadFile);

        if(responseMessage.getMessage().equals("SUCCESS")){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseMessage);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 게시판 수정하기
     * @return
     */
    @PostMapping("/{boardId}")
    @ApiOperation(value = "게시판 수정", notes = "게시판 수정한다.")
    public ResponseEntity<ResponseMessage> updateBoard(@PathVariable Long boardId, BoardDto boardDto, @RequestPart(required = false)  List<MultipartFile> uploadFile){
        //service 호출
        ResponseMessage responseMessage = service.updateBoard(boardId,boardDto,uploadFile);

        if(responseMessage.getMessage().equals("SUCCESS")){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseMessage);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(responseMessage);
        }
    }
    /**
     * 게시판 삭제하기
     * @return
     */
    @DeleteMapping("/{boardId}")
    @ApiOperation(value = "게시판 삭제", notes = "게시판 삭제한다.")
    public ResponseEntity<ResponseMessage> deleteBoard(@PathVariable Long boardId){
        //service 호출
        ResponseMessage responseMessage = service.deleteBoard(boardId);

        if(responseMessage.getMessage().equals("SUCCESS")){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseMessage);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(responseMessage);
        }
    }
    /**
     * 댓글 등록하기
     * @return
     */
    @PostMapping("/comments")
    @ApiOperation(value = "댓글 등록", notes = "댓글을 등록한다.")
    public ResponseEntity<ResponseMessage> insertComment(@RequestBody CommentDto commentDto){
        //service 호출
        ResponseMessage responseMessage = service.insertComment(commentDto);

        if(responseMessage.getMessage().equals("SUCCESS")){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseMessage);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(responseMessage);
        }
    }
    /**
     * 댓글 수정하기
     * @return
     */
    @PutMapping("/comments/{commentId}")
    @ApiOperation(value = "댓글 수정", notes = "댓글을 수정한다.")
    public ResponseEntity<ResponseMessage> updateComment(@PathVariable Long commentId, @RequestBody CommentDto commentDto){
        //service 호출
        ResponseMessage responseMessage = service.updateComment(commentId,commentDto);

        if(responseMessage.getMessage().equals("SUCCESS")){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseMessage);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(responseMessage);
        }
    }
    /**
     * 댓글 삭제하기
     * @return
     */
    @DeleteMapping("/comments/{commentId}")
    @ApiOperation(value = "댓글 삭제", notes = "댓글을 삭제한다.")
    public ResponseEntity<ResponseMessage> deleteComment(@PathVariable Long commentId){
        //service 호출
        ResponseMessage responseMessage = service.deleteComment(commentId);

        if(responseMessage.getMessage().equals("SUCCESS")){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage("SUCCESS"));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 대댓글 등록하기
     * @return
     */
    @PostMapping("/replies")
    @ApiOperation(value = "대댓글 등록", notes = "대댓글을 등록한다.")
    public ResponseEntity<ResponseMessage> insertReply(@RequestBody ReplyDto replyDto){
        //service 호출
        ResponseMessage responseMessage = service.insertReply(replyDto);

        if(responseMessage.getMessage().equals("SUCCESS")){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseMessage);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(responseMessage);
        }
    }
    /**
     * 대댓글 수정하기
     * @return
     */
    @PutMapping("/replies/{replyId}")
    @ApiOperation(value = "대댓글 수정", notes = "대댓글을 수정한다.")
    public ResponseEntity<ResponseMessage> updateReply(@PathVariable Long replyId,@RequestBody ReplyDto replyDto){
        //service 호출
        ResponseMessage responseMessage = service.updateReply(replyId,replyDto);

        if(responseMessage.getMessage().equals("SUCCESS")){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseMessage);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(responseMessage);
        }
    }
    /**
     * 대댓글 삭제하기
     * @return
     */
    @DeleteMapping("/replies/{replyId}")
    @ApiOperation(value = "대댓글 삭제", notes = "대댓글을 삭제한다.")
    public ResponseEntity<ResponseMessage> deleteReply(@PathVariable Long replyId){
        //service 호출
        ResponseMessage responseMessage = service.deleteReply(replyId);
        if(responseMessage.getMessage().equals("SUCCESS")){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseMessage);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(responseMessage);
        }
    }

}
