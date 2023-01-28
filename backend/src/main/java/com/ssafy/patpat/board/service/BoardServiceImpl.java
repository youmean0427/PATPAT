package com.ssafy.patpat.board.service;

import com.ssafy.patpat.board.dto.BoardDto;
import com.ssafy.patpat.board.dto.CommentDto;
import com.ssafy.patpat.board.dto.ReplyDto;
import com.ssafy.patpat.board.dto.RequestBoardDto;
import com.ssafy.patpat.board.entity.*;
import com.ssafy.patpat.board.repository.*;
import com.ssafy.patpat.common.dto.FileDto;
import com.ssafy.patpat.common.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    NestedCommentRepository nestedCommentRepository;
    @Autowired
    PostImageRepository postImageRepository;

    /**
     * 내가 쓴 게시판 리스트를 리턴한다.
     * @return
     */
    @Override
    public List<BoardDto> selectUserBoardList(RequestBoardDto requestBoardDto) {
        PageRequest pageRequest = PageRequest.of(requestBoardDto.getOffSet(),requestBoardDto.getLimit());
        /**
         * JWT  구현되면 유저 정보 가져오는거 수정해야함
         */
        int userId = 0;
        List<Board> entityList = boardRepository.findByUserIdAndPostCode(userId,requestBoardDto.getTypeCode(),pageRequest);
        List<BoardDto> dtoList = new ArrayList<>();
        for(Board entity : entityList){
            dtoList.add(
                    BoardDto.builder()
                            .boardId(entity.getBoardId())
                            .title(entity.getContent())
                            .author(entity.getNickName())
                            .registDate(entity.getDateTime().toLocalDate())
                            .count(entity.getCount())
                            .build()
            );
        }
        return dtoList;
    }

    /**
     * 전체 게시판 리스트를 리턴한다.(카테고리별)
     * @return
     */
    @Override
    public List<BoardDto> selectBoardList(RequestBoardDto requestBoardDto) {
        PageRequest pageRequest = PageRequest.of(requestBoardDto.getOffSet(),requestBoardDto.getLimit());
        List<Board> entityList = boardRepository.findByPostCode(requestBoardDto.getTypeCode(),pageRequest);
        List<BoardDto> dtoList = new ArrayList<>();
        for(Board entity : entityList){
            dtoList.add(
                    BoardDto.builder()
                            .boardId(entity.getBoardId())
                            .title(entity.getContent())
                            .author(entity.getNickName())
                            .registDate(entity.getDateTime().toLocalDate())
                            .count(entity.getCount())
                            .build()
            );
        }
        return dtoList;
    }
    /**
     * 게시판 상세 화면
     * @return
     */
    @Override
    public BoardDto deatilBoard(int boardId) {
        Board board = boardRepository.findByBoardId(boardId);
        List<Comment> commentList = commentRepository.findByboardId(boardId);
        List<CommentDto> commentDtoList = new ArrayList<>();
        for(Comment entity : commentList){
            List<NestedComment> nestedComments = nestedCommentRepository.findByCommentId(entity.getCommentId());
            List<ReplyDto> replyDtoList = new ArrayList<>();
            for(NestedComment nc : nestedComments){
                replyDtoList.add(
                        ReplyDto.builder()
                        .replyId(nc.getNestedCommentId())
                        .regDt(nc.getRegTime())
                        .author(nc.getNickName())
                        .content(nc.getContent())
                        .build()
                );
            }

            commentDtoList.add(
                    CommentDto.builder()
                            .commentId(entity.getCommentId())
                            .content(entity.getContent())
                            .regDt(entity.getRegTime())
                            .author(entity.getNickName())
                            .replyList(replyDtoList)
                            .build()
            );
        }
        List<PostImage> postImageList = postImageRepository.findByBoardId(boardId);
        List<Image> imageList = new ArrayList<>();
        for(PostImage entity : postImageList){
            imageList.add(imageRepository.findByImageId(entity.getImageId()));
        }
        List<FileDto> fileDtoList = new ArrayList<>();
        for(Image entity : imageList){
            fileDtoList.add(
                    FileDto.builder()
                            .filePath(entity.getImagePath())
                            .build()
            );
        }
        BoardDto boardDto = BoardDto.builder()
                .boardId(board.getBoardId())
                .author(board.getNickName())
                .registDate(board.getDateTime().toLocalDate())
                .title(board.getTitle())
                .count(board.getCount())
                .content(board.getContent())
                .commentList(commentDtoList)
                .fileUrlList(fileDtoList)
                .build();

        return boardDto;
    }

    /**
     * 게시판 등록하기
     * @return
     */
    @Override
    public ResponseMessage insertBoard(BoardDto boardDto, MultipartFile[] uploadFile) {
        return null;
    }

    /**
     * 게시판 수정하기
     * @return
     */
    @Override
    public ResponseMessage updateBoard(int boardId, BoardDto boardDto, MultipartFile[] uploadFile) {
        return null;
    }

    /**
     * 게시판 삭제하기
     * @return
     */
    @Override
    public ResponseMessage deleteBoard(int boardId) {
        return null;
    }

    /**
     * 댓글 등록하기
     * @return
     */
    @Override
    public ResponseMessage insertComment(CommentDto commentDto) {
        return null;
    }

    /**
     * 댓글 수정하기
     * @return
     */
    @Override
    public ResponseMessage updateComment(int commentId, CommentDto commentDto) {
        return null;
    }

    /**
     * 댓글 삭제하기
     * @return
     */
    @Override
    public ResponseMessage deleteComment(int commentId) {
        return null;
    }

    /**
     * 대댓글 등록하기
     * @return
     */
    @Override
    public ResponseMessage insertReply(ReplyDto replyDto) {
        return null;
    }

    /**
     * 대댓글 수정하기
     * @return
     */
    @Override
    public ResponseMessage updateReply(int replyId, ReplyDto replyDto) {
        return null;
    }

    /**
     * 대댓글 삭제하기
     * @return
     */
    @Override
    public ResponseMessage deleteReply(int replyId) {
        return null;
    }
}
