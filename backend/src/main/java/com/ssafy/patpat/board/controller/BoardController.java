package com.ssafy.patpat.board.controller;

import com.ssafy.patpat.board.dto.*;
import com.ssafy.patpat.common.dto.FileDto;
import com.ssafy.patpat.common.dto.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

    /**
     * 내가 쓴 게시판 리스트를 리턴한다.
     * @return
     */
    @GetMapping()
    public ResponseEntity<Object> selectUserBoardList(RequestBoardDto requestBoardDto){
        //service 호출
        //dummy data
        List<BoardDto> boardDtoList = new ArrayList<>();
        FileDto fileDto = new FileDto(1L,"asd","파일","sd/sd/sd.png");
        List<FileDto> fileUrlList = new ArrayList<>();
        fileUrlList.add(fileDto);

        boardDtoList.add(BoardDto.builder()
                .fileUrlList(fileUrlList)
                .boardId(0)
                .title("제목")
                .author("정경훈")
                .registDate(LocalDate.now())
                .count(1102).build());

        boardDtoList.add(BoardDto.builder()
                .fileUrlList(fileUrlList)
                .boardId(1)
                .title("제목2")
                .author("이재혁")
                .registDate(LocalDate.now())
                .count(12).build());

        if(true){
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
    public ResponseEntity<Object> selectBoardList(RequestBoardDto requestBoardDto){
        //service 호출
        //dummy data
        List<BoardDto> boardDtoList = new ArrayList<>();
        FileDto fileDto = new FileDto(1L,"asd","파일","sd/sd/sd.png");
        List<FileDto> fileUrlList = new ArrayList<>();
        fileUrlList.add(fileDto);

        boardDtoList.add(BoardDto.builder()
                .fileUrlList(fileUrlList)
                .boardId(0)
                .title("제목")
                .author("정경훈")
                .registDate(LocalDate.now())
                .count(1102).build());

        boardDtoList.add(BoardDto.builder()
                .fileUrlList(fileUrlList)
                .boardId(1)
                .title("제목2")
                .author("이재혁")
                .registDate(LocalDate.now())
                .count(12).build());

        if(true){
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
    public ResponseEntity<Object> detailBoard(@PathVariable int boardId){
        //현재 userId
        //service 호출
        FileDto fileDto = new FileDto(1L,"asd","파일","sd/sd/sd.png");
        FileDto fileDto1 = new FileDto(2L,"asdf","파일2","sd/sd/sssd.png");

        List<FileDto> fileUrlList = new ArrayList<>();
        fileUrlList.add(fileDto);
        fileUrlList.add(fileDto1);

        List<CommentDto> comment = new ArrayList<>();
        CommentDto commentDto = CommentDto.builder()
                .commentId(1)
                .author("문석환")
                .content("좋은 정보 감사요").build();
        comment.add(commentDto);

        List<ReplyDto> reply = new ArrayList<>();
        ReplyDto replyDto = ReplyDto.builder()
                .commentId(1)
                .author("정경훈")
                .content("감사합니다").build();
        ReplyDto replyDto1 = ReplyDto.builder()
                .commentId(1)
                .author("문석환")
                .content("아닙니다").build();
        reply.add(replyDto);
        reply.add(replyDto1);

        BoardDto boardDto = BoardDto.builder()
                .fileUrlList(fileUrlList)
                .boardId(0)
                .title("제목")
                .author("정경훈")
                .registDate(LocalDate.now())
                .count(1102)
                .comment(comment)
                .reply(reply).build();
        if(true){
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
    public ResponseEntity<Object> insertBoard(BoardDto boardDto, MultipartFile[] uploadFile){
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
    public ResponseEntity<Object> updateBoard(BoardDto boardDto, MultipartFile[] uploadFile){
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
    public ResponseEntity<Object> insertComment(CommentDto commentDto){
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
    public ResponseEntity<Object> insertReply(ReplyDto replyDto){
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
