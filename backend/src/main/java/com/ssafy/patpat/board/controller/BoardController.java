package com.ssafy.patpat.board.controller;

import com.ssafy.patpat.board.dto.*;
import com.ssafy.patpat.common.code.Board;
import com.ssafy.patpat.common.dto.FileDto;
import com.ssafy.patpat.common.dto.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
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

    /**
     * 내가 쓴 게시판 리스트를 리턴한다.
     * @return
     */
    @GetMapping()
    @ApiOperation(value = "게시판 리스트", notes = "내가 쓴 게시판 리스트를 조회한다.")
    public ResponseEntity<List<BoardDto>> selectUserBoardList(RequestBoardDto requestBoardDto){
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

        if(true) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ArrayList<BoardDto>());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
//        }else{
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(new ResponseMessage("FAIL"));
//        }

    }

    /**
     * 전체 게시판 리스트를 리턴한다.(카테고리별)
     * @return
     */
    @GetMapping("/all")
    @ApiOperation(value = "게시판 리스트", notes = "전체 게시판 리스트를 조회한다.")
    public ResponseEntity<List<BoardDto>> selectBoardList(RequestBoardDto requestBoardDto){
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
                    .body(new ArrayList<BoardDto>() {
                    });
        }
    }
    /**
     * 게시판 상세 화면 (카테고리별)
     * @return
     */
    @GetMapping("/{boardId}")
    @ApiOperation(value = "게시판 상세", notes = "게시판 상세를 조회한다.")
    public ResponseEntity<BoardDto> detailBoard(@PathVariable int boardId){
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
                .commentList(comment)
                .replyList(reply).build();
        if(true){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new BoardDto());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BoardDto());
        }
    }
    /**
     * 게시판 등록하기
     * @return
     */
    @PostMapping()
    @ApiOperation(value = "게시판 등록", notes = "게시판 등록.")
    public ResponseEntity<ResponseMessage> insertBoard(BoardDto boardDto, MultipartFile[] uploadFile){
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
    @PostMapping("/{boardId}")
    @ApiOperation(value = "게시판 수정", notes = "게시판 수정한다.")
    public ResponseEntity<ResponseMessage> updateBoard(BoardDto boardDto, MultipartFile[] uploadFile){
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
    @ApiOperation(value = "게시판 삭제", notes = "게시판 삭제한다.")
    public ResponseEntity<ResponseMessage> deleteBoard(@PathVariable int boardId){
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
    @ApiOperation(value = "댓글 등록", notes = "댓글을 등록한다.")
    public ResponseEntity<ResponseMessage> insertComment(@RequestBody CommentDto commentDto){
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
    @ApiOperation(value = "댓글 수정", notes = "댓글을 수정한다.")
    public ResponseEntity<ResponseMessage> updateComment(@PathVariable int commentId, @RequestBody CommentDto commentDto){
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
    @ApiOperation(value = "댓글 삭제", notes = "댓글을 삭제한다.")
    public ResponseEntity<ResponseMessage> deleteComment(@PathVariable int commentId){
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
    @ApiOperation(value = "대댓글 등록", notes = "대댓글을 등록한다.")
    public ResponseEntity<ResponseMessage> insertReply(@RequestBody ReplyDto replyDto){
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
    @ApiOperation(value = "대댓글 수정", notes = "대댓글을 수정한다.")
    public ResponseEntity<ResponseMessage> updateReply(@PathVariable int replyId,@RequestBody ReplyDto replyDto){
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
    @ApiOperation(value = "대댓글 삭제", notes = "대댓글을 삭제한다.")
    public ResponseEntity<ResponseMessage> deleteReply(@PathVariable int replyId){
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
