package com.ssafy.patpat.board.controller;

import com.ssafy.patpat.board.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
public class BoardController {

    /**
     * 내가 쓴 게시판 리스트를 리턴한다.
     * @return
     */
    @GetMapping()
    public ResponseEntity<Object> selectUserBoardList(RequestBoardDto dto){
        //service 호출
        if(true){
            System.out.println("여기");
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new BoardDto());
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
    public ResponseEntity<Object> selectBoardList(RequestBoardDto dto){
        //service 호출
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new BoardDto());
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
    public ResponseEntity<Object> detailBoard(@PathVariable int boardId){
        //현재 userId
        //service 호출
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new BoardDto());
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
    public ResponseEntity<Object> insertBoard(BoardDto dto){
        //service 호출
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage("SUCCESS"));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 게시판 수정하기
     * @return
     */
    @PutMapping("/{boardId}")
    public ResponseEntity<Object> updateBoard(BoardDto dto){
        //service 호출
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage("SUCCESS"));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 게시판 삭제하기
     * @return
     */
    @DeleteMapping("/{boardId}")
    public ResponseEntity<Object> deleteBoard(@PathVariable int boardId){
        //service 호출
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage("SUCCESS"));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 댓글 등록하기
     * @return
     */
    @PostMapping("/comment")
    public ResponseEntity<Object> insertComment(CommentDto dto){
        //service 호출
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage("SUCCESS"));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 댓글 수정하기
     * @return
     */
    @PutMapping("/comment/{commentId}")
    public ResponseEntity<Object> updateComment(@PathVariable int commentId){
        //service 호출
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage("SUCCESS"));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 댓글 삭제하기
     * @return
     */
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<Object> deleteComment(@PathVariable int commentId){
        //service 호출
        if(true){
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
    public ResponseEntity<Object> insertReply(ReplyDto dto){
        //service 호출
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage("SUCCESS"));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 대댓글 수정하기
     * @return
     */
    @PutMapping("/reply/{replyId}")
    public ResponseEntity<Object> updateReply(@PathVariable int replyId){
        //service 호출
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage("SUCCESS"));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
    /**
     * 대댓글 삭제하기
     * @return
     */
    @DeleteMapping("/reply/{replyId}")
    public ResponseEntity<Object> deleteReply(@PathVariable int replyId){
        //service 호출
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseMessage("SUCCESS"));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("FAIL"));
        }
    }
}
