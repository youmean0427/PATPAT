package com.ssafy.patpat.board.service;

import com.ssafy.patpat.board.dto.BoardDto;
import com.ssafy.patpat.board.dto.CommentDto;
import com.ssafy.patpat.board.dto.ReplyDto;
import com.ssafy.patpat.board.dto.RequestBoardDto;
import com.ssafy.patpat.board.entity.*;
import com.ssafy.patpat.board.repository.*;
import com.ssafy.patpat.common.dto.FileDto;
import com.ssafy.patpat.common.dto.ResponseMessage;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @Value("${app.fileupload.uploadPath}")
    String uploadPath;

    @Value("${app.fileupload.uploadDir}")
    String uploadFolder;

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
                            .filePath(entity.getOrigFilename())
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
    @Transactional
    public ResponseMessage insertBoard(BoardDto boardDto, List<MultipartFile> uploadFile) {
        ResponseMessage responseMessage = new ResponseMessage();
        /**
         * 유저 정보 들어오는거 생기면 다시하기
         */
        try{
            Board board = Board.builder()
                    .title(boardDto.getTitle())
                    .content(boardDto.getContent())
                    .postCode(boardDto.getTypeCode())
                    .userId(0)
                    .nickName("dd")
                    .dateTime(LocalDateTime.now())
                    .build();
            boardRepository.save(board);

            File uploadDir = new File(uploadPath +File.separator+uploadFolder);
            if(!uploadDir.exists()) uploadDir.mkdir();
            System.out.println(uploadFile.size());

            for(MultipartFile partFile : uploadFile){
                int boardId = board.getBoardId();
                String fileName = partFile.getOriginalFilename();

                UUID uuid = UUID.randomUUID();

                String extension = FilenameUtils.getExtension(fileName);

                String savingFileName = uuid+"."+extension;

                File destFile = new File(uploadPath+File.separator+uploadFolder+File.separator+savingFileName);

                partFile.transferTo(destFile);

                Image image = Image.builder()
                        .origFilename(fileName)
                        .fileSize((int) partFile.getSize())
                        .filename(fileName)
                        .filePath(uploadFolder+"/"+savingFileName)
                        .build();

                imageRepository.save(image);

                PostImage postImage = PostImage.builder()
                        .imageId(image.getImageId())
                        .boardId(board.getBoardId())
                        .build();

                postImageRepository.save(postImage);

            }
            responseMessage.setMessage("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            responseMessage.setMessage("FAIL");
        }
        return responseMessage;
    }

    /**
     * 게시판 수정하기
     * @return
     */
    @Override
    @Transactional
    public ResponseMessage updateBoard(int boardId, BoardDto boardDto, @RequestPart List<MultipartFile> uploadFile) {
        ResponseMessage responseMessage = new ResponseMessage();
        /**
         * 유저 정보 들어오는거 생기면 다시하기
         */
        try{
            Board board = boardRepository.findByBoardId(boardId);
            board.update(boardDto.getTitle(),boardDto.getContent());


            File uploadDir = new File(uploadPath +File.separator+uploadFolder);
            if(!uploadDir.exists()) uploadDir.mkdir();

            List<PostImage> postImageList = postImageRepository.findByBoardId(boardId);
            List<Integer> list = new ArrayList<>();
            for(PostImage i : postImageList){
                list.add(i.getImageId());
            }
            List<Image> imageList = imageRepository.findByImageIdIn(list);
            for(Image i : imageList){
                File file = new File(uploadPath+File.separator+i.getFilePath());
                if(file.exists()) file.delete();
            }

            imageRepository.deleteByImageIdIn(list);
            postImageRepository.deleteByBoardId(boardId);

            for(MultipartFile partFile : uploadFile){
                String fileName = partFile.getOriginalFilename();

                UUID uuid = UUID.randomUUID();

                String extension = FilenameUtils.getExtension(fileName);

                String savingFileName = uuid+"."+extension;

                File destFile = new File(uploadPath+File.separator+uploadFolder+File.separator+savingFileName);

                partFile.transferTo(destFile);

                Image image = Image.builder()
                        .origFilename(fileName)
                        .fileSize((int) partFile.getSize())
                        .filename(fileName)
                        .filePath(uploadFolder+"/"+savingFileName)
                        .build();

                imageRepository.save(image);

                PostImage postImage = PostImage.builder()
                        .imageId(image.getImageId())
                        .boardId(board.getBoardId())
                        .build();

                postImageRepository.save(postImage);

            }
            responseMessage.setMessage("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            responseMessage.setMessage("FAIL");
        }
        return responseMessage;
    }

    /**
     * 게시판 삭제하기
     * @return
     */
    @Override
    @Transactional
    public ResponseMessage deleteBoard(int boardId) {
        ResponseMessage responseMessage = new ResponseMessage();
        try{
            boardRepository.deleteByBoardId(boardId);
            List<Comment> commentList = commentRepository.findByboardId(boardId);
            List<Integer> integerList = new ArrayList<>();
            for(Comment c : commentList){
                integerList.add(c.getCommentId());
            }
            nestedCommentRepository.deleteByCommentIdIn(integerList);
            commentRepository.deleteByBoardId(boardId);

            File uploadDir = new File(uploadPath +File.separator+uploadFolder);
            if(!uploadDir.exists()) uploadDir.mkdir();

            List<PostImage> postImageList = postImageRepository.findByBoardId(boardId);
            List<Integer> list = new ArrayList<>();
            for(PostImage i : postImageList){
                list.add(i.getImageId());
            }
            List<Image> imageList = imageRepository.findByImageIdIn(list);
            for(Image i : imageList){
                File file = new File(uploadPath+File.separator+i.getFilePath());
                if(file.exists()) file.delete();
            }
            responseMessage.setMessage("SUCCESS");
        }catch(Exception e){
            e.printStackTrace();
            responseMessage.setMessage("FAIL");
        }
        return responseMessage;
    }

    /**
     * 댓글 등록하기
     * @return
     */
    @Override
    public ResponseMessage insertComment(CommentDto commentDto) {
        ResponseMessage responseMessage = new ResponseMessage();
        Comment comment = Comment.builder()
                .content(commentDto.getContent())
                .regTime(commentDto.getRegDt())
                .boardId(commentDto.getBoardId())
                .userId(0)
                .nickName("aa")
                .build();
        Comment save = commentRepository.save(comment);
        if(save==null){
            responseMessage.setMessage("FAIL");
        }
        else{
            responseMessage.setMessage("SUCCESS");
        }
        return responseMessage;
    }

    /**
     * 댓글 수정하기
     * @return
     */
    @Override
    @Transactional
    public ResponseMessage updateComment(int commentId, CommentDto commentDto) {
        ResponseMessage responseMessage = new ResponseMessage();

        Comment comment = commentRepository.findByCommentId(commentId);
        comment.updateComment(commentDto.getContent());

        Comment save = commentRepository.save(comment);

        if(save==null){
            responseMessage.setMessage("FAIL");
        }
        else{
            responseMessage.setMessage("SUCCESS");
        }
        return responseMessage;
    }

    /**
     * 댓글 삭제하기
     * @return
     */
    @Override
    @Transactional
    public ResponseMessage deleteComment(int commentId) {
        ResponseMessage responseMessage = new ResponseMessage();
        try{
            nestedCommentRepository.deleteByCommentId(commentId);
            commentRepository.deleteByCommentId(commentId);
            responseMessage.setMessage("SUCCESS");
        }catch (Exception e ){
            responseMessage.setMessage("FAIL");
        }
        return responseMessage;
    }

    /**
     * 대댓글 등록하기
     * @return
     */
    @Override
    public ResponseMessage insertReply(ReplyDto replyDto) {
        ResponseMessage responseMessage = new ResponseMessage();
        NestedComment nestedComment = NestedComment.builder()
                .content(replyDto.getContent())
                .regTime(replyDto.getRegDt())
                .commentId(replyDto.getCommentId())
                .userId(0)
                .nickName("aa")
                .build();
        NestedComment save = nestedCommentRepository.save(nestedComment);
        if(save==null){
            responseMessage.setMessage("FAIL");
        }
        else{
            responseMessage.setMessage("SUCCESS");
        }
        return responseMessage;
    }

    /**
     * 대댓글 수정하기
     * @return
     */
    @Override
    public ResponseMessage updateReply(int replyId, ReplyDto replyDto) {
        ResponseMessage responseMessage = new ResponseMessage();

        NestedComment nestedComment = nestedCommentRepository.findByNestedCommentId(replyId);
        nestedComment.updateNestedComment(replyDto.getContent());

        NestedComment save = nestedCommentRepository.save(nestedComment);

        if(save==null){
            responseMessage.setMessage("FAIL");
        }
        else{
            responseMessage.setMessage("SUCCESS");
        }
        return responseMessage;
    }

    /**
     * 대댓글 삭제하기
     * @return
     */
    @Override
    @Transactional
    public ResponseMessage deleteReply(int replyId) {
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            nestedCommentRepository.deleteByNestedCommentId(replyId);
            responseMessage.setMessage("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            responseMessage.setMessage("FAIL");
        }
        return responseMessage;
    }
}
