package com.ssafy.patpat.board.controller;

import com.ssafy.patpat.board.dto.*;
import com.ssafy.patpat.board.service.BoardService;
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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/board")
@Api(tags = {"01. Board"},description = "게시판 관련 서비스")
@ApiResponses({
        @ApiResponse(code=200, message = "성공"),
        @ApiResponse(code=400, message = "에러")
})
public class BoardController {
    @Autowired
    BoardService service;
    /**
     * 내가 쓴 게시판 리스트를 리턴한다.
     * @return
     */
    @GetMapping()
    @ApiOperation(value = "게시판 리스트", notes = "내가 쓴 게시판 리스트를 조회한다.")
    public ResponseEntity<Object> selectUserBoardList(RequestBoardDto requestBoardDto){
        //service 호출
        List<BoardDto> boardDtoList = service.selectUserBoardList(requestBoardDto);

        if(boardDtoList!=null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(boardDtoList);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }

    /**
     * 전체 게시판 리스트를 리턴한다.(카테고리별)
     * @return
     */
    @GetMapping("/all")
    @ApiOperation(value = "게시판 리스트", notes = "전체 게시판 리스트를 조회한다.")
    public ResponseEntity<Object> selectBoardList(RequestBoardDto requestBoardDto){
        //service 호출
        List<BoardDto> boardDtoList = service.selectBoardList(requestBoardDto);

        if(boardDtoList!=null){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(boardDtoList);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 게시판 상세 화면 (카테고리별)
     * @return
     */
    @GetMapping("/{boardId}")
    @ApiOperation(value = "게시판 상세", notes = "게시판 상세를 조회한다.")
    public ResponseEntity<Object> detailBoard(@PathVariable int boardId){
        //service 호출
        BoardDto boardDto = service.deatilBoard(boardId);
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
    public ResponseEntity<ResponseMessage> insertBoard(BoardDto boardDto, @RequestPart List<MultipartFile> uploadFile){
        //service 호출
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
    public ResponseEntity<ResponseMessage> updateBoard(@PathVariable int boardId, BoardDto boardDto, @RequestPart List<MultipartFile> uploadFile){
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
    public ResponseEntity<ResponseMessage> deleteBoard(@PathVariable int boardId){
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
    @PostMapping("/comment")
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
    @PutMapping("/comment/{commentId}")
    @ApiOperation(value = "댓글 수정", notes = "댓글을 수정한다.")
    public ResponseEntity<ResponseMessage> updateComment(@PathVariable int commentId, @RequestBody CommentDto commentDto){
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
    @DeleteMapping("/comment/{commentId}")
    @ApiOperation(value = "댓글 삭제", notes = "댓글을 삭제한다.")
    public ResponseEntity<ResponseMessage> deleteComment(@PathVariable int commentId){
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
    @PostMapping("/reply")
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
    @PutMapping("/reply/{replyId}")
    @ApiOperation(value = "대댓글 수정", notes = "대댓글을 수정한다.")
    public ResponseEntity<ResponseMessage> updateReply(@PathVariable int replyId,@RequestBody ReplyDto replyDto){
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
    @DeleteMapping("/reply/{replyId}")
    @ApiOperation(value = "대댓글 삭제", notes = "대댓글을 삭제한다.")
    public ResponseEntity<ResponseMessage> deleteReply(@PathVariable int replyId){
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
