package com.ssafy.patpat.board.service;

import com.ssafy.patpat.board.dto.*;
import com.ssafy.patpat.board.entity.*;
import com.ssafy.patpat.board.repository.*;
import com.ssafy.patpat.common.code.BoardCode;
import com.ssafy.patpat.common.dto.FileDto;
import com.ssafy.patpat.common.dto.ResponseListDto;
import com.ssafy.patpat.common.dto.ResponseMessage;
import com.ssafy.patpat.common.entity.Image;
import com.ssafy.patpat.common.repository.ImageRepository;
import com.ssafy.patpat.common.service.FileService;
import com.ssafy.patpat.common.util.SecurityUtil;
import com.ssafy.patpat.user.dto.UserDto;
import com.ssafy.patpat.user.entity.User;
import com.ssafy.patpat.user.repository.UserRepository;
import com.ssafy.patpat.user.service.UserService;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BoardServiceImpl implements BoardService{

    private static final Logger LOGGER = LoggerFactory.getLogger(BoardServiceImpl.class);
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    NestedCommentRepository nestedCommentRepository;
//    @Autowired
//    PostImageRepository postImageRepository;

    @Autowired
    FileService fileService;
    @Autowired
    UserService userService;

    /**
     * 내가 쓴 게시판 리스트를 리턴한다.
     * @return
     */
    @Override
    @Transactional
    public ResponseListDto selectUserBoardList(RequestBoardDto requestBoardDto) {
        PageRequest pageRequest = PageRequest.of(requestBoardDto.getOffSet(),requestBoardDto.getLimit(), Sort.by("boardId").descending());
        /**
         * JWT  구현되면 유저 정보 가져오는거 수정해야함
         */
        ResponseListDto boardDto = new ResponseListDto();
        UserDto userDto = userService.getUserWithAuthorities();
        Long userId = userDto.getUserId();
//        System.out.println(requestBoardDto);
        Page<Board> entityList = boardRepository.findByUserUserIdAndBoardCode(userId,BoardCode.of(requestBoardDto.getTypeCode()),pageRequest);
//        System.out.println(entityList);
//        System.out.println(requestBoardDto);

        List<BoardDto> dtoList = new ArrayList<>();
        for(Board entity : entityList.toList()){
            dtoList.add(
                    BoardDto.builder()
                            .boardId(entity.getBoardId())
                            .title(entity.getTitle())
                            .author(entity.getUser().getNickname())
                            .registDate(entity.getDateTime().toLocalDate())
                            .typeCode(entity.getBoardCode().getCode())
                            .type(entity.getBoardCode().name())
                            .count(entity.getCount())
                            .build()
            );
        }
        boardDto.setList(dtoList);
        boardDto.setTotalCount(entityList.getTotalElements());
        boardDto.setTotalPage(entityList.getTotalPages());
        return boardDto;
    }

    /**
     * 전체 게시판 리스트를 리턴한다.(카테고리별)
     * @return
     */
    @Override
    @Transactional
    public ResponseListDto selectBoardList(RequestBoardDto requestBoardDto) {
        PageRequest pageRequest = PageRequest.of(requestBoardDto.getOffSet(),requestBoardDto.getLimit(),Sort.by("boardId").descending());
        Page<Board> entityList = boardRepository.findByBoardCode(BoardCode.of(requestBoardDto.getTypeCode()),pageRequest);
//        System.out.println(entityList);

        ResponseListDto boardDto = new ResponseListDto();
        List<BoardDto> dtoList = new ArrayList<>();
        //보드 하나 골라서
        for(Board board : entityList.toList()){
            //0번 상태인 경우 썸네일을 넣는다.
            LOGGER.info("board좀 보자 {}", board);
            FileDto thumbnail = null;
            if(requestBoardDto.getTypeCode() == 0){
//                List<PostImage> postImageList = postImageRepository.findByBoardId(board.getBoardId());

                List<Image> imageList = board.getImages();
//                for(Image post : postImageList){
//                    imageList.add(imageRepository.findByImageId(post.getImageId()));
//                }

                if(imageList.isEmpty()){
                    thumbnail = FileDto.builder()
                            .filePath(fileService.getFileUrl(fileService.getDefaultImage()))
                            .build();
                }else{
                    thumbnail = FileDto.builder()
                            .filePath(fileService.getFileUrl(imageList.get(0)))
                            .build();
                }
            }
            dtoList.add(
                    BoardDto.builder()
                            .boardId(board.getBoardId())
                            .title(board.getTitle())
                            .author(board.getUser().getNickname())
                            .registDate(board.getDateTime().toLocalDate())
                            .count(board.getCount())
                            .content(board.getContent())
                            .thumbnail(thumbnail)
                            .typeCode(board.getBoardCode().getCode())
                            .type(board.getBoardCode().name())
                            .build()
            );
        }
        boardDto.setList(dtoList);
        boardDto.setTotalPage(entityList.getTotalPages());
        boardDto.setTotalCount(entityList.getTotalElements());
        return boardDto;
    }
    /**
     * 게시판 상세 화면
     * @return
     */
    @Override
    @Transactional
    public BoardDto detailBoard(Long boardId) {
        Board board = boardRepository.findByBoardId(boardId);
        List<Comment> commentList = commentRepository.findByBoardBoardId(boardId);
        List<CommentDto> commentDtoList = new ArrayList<>();
        for(Comment entity : commentList){
            List<NestedComment> nestedComments = nestedCommentRepository.findByCommentCommentId(entity.getCommentId());
            List<ReplyDto> replyDtoList = new ArrayList<>();
            for(NestedComment nc : nestedComments){
                replyDtoList.add(
                        ReplyDto.builder()
                        .replyId(nc.getNestedCommentId())
                        .regDt(nc.getRegTime())
                        .author(nc.getUser().getNickname())
                        .content(nc.getContent())
                        .build()
                );
            }

            commentDtoList.add(
                    CommentDto.builder()
                            .commentId(entity.getCommentId())
                            .content(entity.getContent())
                            .regDt(entity.getRegTime())
                            .author(entity.getUser().getNickname())
                            .replyList(replyDtoList)
                            .build()
            );
        }
//        List<PostImage> postImageList = postImageRepository.findByBoardId(boardId);
        List<Image> postImageList =board.getImages();
//        List<Image> imageList = new ArrayList<>();
//
//        for(PostImage entity : postImageList){
//            imageList.add(imageRepository.findByImageId(entity.getImageId()));
//        }
        List<FileDto> fileDtoList = new ArrayList<>();
        if(postImageList.isEmpty()){
            fileDtoList.add(FileDto.builder()
                    .filePath(fileService.getFileUrl(fileService.getDefaultImage()))
                    .build());
        }
        for(Image entity : postImageList){
            fileDtoList.add(
                    FileDto.builder()
                            .id(entity.getImageId())
                            .filePath(fileService.getFileUrl(entity))
                            .build()
            );
        }
        /** 조회수 증가 */
        int count = board.getCount();
        count++;
        board.setCount(count);
        boardRepository.save(board);
        BoardDto boardDto = BoardDto.builder()
                .boardId(board.getBoardId())
                .userId(board.getUser().getUserId())
                .author(board.getUser().getNickname())
                .registDate(board.getDateTime().toLocalDate())
                .title(board.getTitle())
                .count(count)
                .content(board.getContent())
                .commentList(commentDtoList)
                .fileUrlList(fileDtoList)
                .build();
//        System.out.println(boardDto);
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
         * 유저 정보 들어오는거 생기면 다시하기 - 했음
         */
//        UserDto userDto = userService.getUserWithAuthorities();
        Optional<User> user = SecurityUtil.getCurrentEmail().flatMap(userRepository::findOneWithAuthoritiesByEmail);
//        Optional<User> user = userRepository.findById(boardDto.getUserId());
        List<Image> images = new ArrayList<>();
        try{
            if(uploadFile != null) {
                for (MultipartFile partFile : uploadFile) {
                    images.add(fileService.insertFile(partFile, "board"));
                }
            }
            Board board = Board.builder()
                    .title(boardDto.getTitle())
                    .content(boardDto.getContent())
                    .boardCode(BoardCode.of(boardDto.getTypeCode()))
                    .user(user.get())
                    .dateTime(LocalDateTime.now())
                    .images(images)
                    .build();
            boardRepository.save(board);
            user.get().updateExp(user.get().getExp()+1);
            userRepository.save(user.get());

//            File uploadDir = new File(uploadPath +File.separator+uploadFolder);
//            if(!uploadDir.exists()) uploadDir.mkdir();
//            if(uploadFile!=null) {
//                for (MultipartFile partFile : uploadFile) {
//                    Long boardId = board.getBoardId();
//                    String fileName = partFile.getOriginalFilename();
//
//                    UUID uuid = UUID.randomUUID();
//
//                    String extension = FilenameUtils.getExtension(fileName);
//
//                    String savingFileName = uuid + "." + extension;
//
////                    File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
//
//                    String FilePath = uploadPath + File.separator + uploadFolder + File.separator + savingFileName;
//                    Path path = Paths.get(FilePath).toAbsolutePath();
//
//                    partFile.transferTo(path.toFile());
//
//                    Image image = Image.builder()
//                            .origFilename(fileName)
//                            .fileSize((int) partFile.getSize())
//                            .filename(fileName)
//                            .filePath(uploadFolder + "/" + savingFileName)
//                            .build();
//
//                    imageRepository.save(image);
//
//                    PostImage postImage = PostImage.builder()
//                            .imageId(image.getImageId())
//                            .boardId(board.getBoardId())
//                            .build();
//
//                    postImageRepository.save(postImage);
//
//                }
//            }
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
    public ResponseMessage updateBoard(Long boardId, BoardDto boardDto,  List<MultipartFile> uploadFile) {
        ResponseMessage responseMessage = new ResponseMessage();
        /**
         * 유저 정보 들어오는거 생기면 다시하기
         */
        try{
            Board board = boardRepository.findByBoardId(boardId);
            board.update(boardDto.getTitle(),boardDto.getContent());



//            List<Image> newImages = new ArrayList<>();
            if(uploadFile != null) {
                List<Image> images = board.getImages();


                List<Image> removeImage = new ArrayList<>();
                images.stream().forEach(i->{
                    if(boardDto.getDeleteFileList().contains(i.getImageId())){
                        fileService.deleteFile(i);
                        removeImage.add(i);
                    }
                });
                images.removeAll(removeImage);

                for(MultipartFile partFile : uploadFile){
                    images.add(fileService.insertFile(partFile, "board"));
                }
                board.setImages(images);
            }

            boardRepository.save(board);

//            File uploadDir = new File(uploadPath +File.separator+uploadFolder);
//            if(!uploadDir.exists()) uploadDir.mkdir();
//
//            List<PostImage> postImageList = postImageRepository.findByBoardId(boardId);
//            List<Integer> list = new ArrayList<>();
//            for(PostImage i : postImageList){
//                list.add(i.getImageId());
//            }
//            List<Image> imageList = imageRepository.findByImageIdIn(list);
//            for(Image i : imageList){
//                File file = new File(uploadPath+File.separator+i.getFilePath());
//                if(file.exists()) file.delete();
//            }
//
//            imageRepository.deleteByImageIdIn(list);
//            postImageRepository.deleteByBoardId(boardId);

//            for(MultipartFile partFile : uploadFile){
//                String fileName = partFile.getOriginalFilename();
//
//                UUID uuid = UUID.randomUUID();
//
//                String extension = FilenameUtils.getExtension(fileName);
//
//                String savingFileName = uuid+"."+extension;
//
//                File destFile = new File(uploadPath+File.separator+uploadFolder+File.separator+savingFileName);
//
//                partFile.transferTo(destFile);
//
//                Image image = Image.builder()
//                        .origFilename(fileName)
//                        .fileSize((int) partFile.getSize())
//                        .filename(fileName)
//                        .filePath(uploadFolder+"/"+savingFileName)
//                        .build();
//
//                imageRepository.save(image);
//
//                PostImage postImage = PostImage.builder()
//                        .imageId(image.getImageId())
//                        .boardId(board.getBoardId())
//                        .build();
//
//                postImageRepository.save(postImage);
//
//            }

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
    public ResponseMessage deleteBoard(Long boardId) {
        ResponseMessage responseMessage = new ResponseMessage();
        try{
            Optional<Board> board = boardRepository.findById(boardId);
            if(!board.isPresent()){
                return new ResponseMessage("FAIL");
            }
            List<Image> images = board.get().getImages();
            for(Image image : images){
                fileService.deleteFile(image);
            }
            images.removeAll(images);
            boardRepository.deleteByBoardId(boardId);
            List<Comment> commentList = commentRepository.findByBoardBoardId(boardId);
            List<Long> integerList = new ArrayList<>();
            for(Comment c : commentList){
                integerList.add(c.getCommentId());
            }
            nestedCommentRepository.deleteByCommentCommentIdIn(integerList);
            commentRepository.deleteByBoardBoardId(boardId);

//            File uploadDir = new File(uploadPath +File.separator+uploadFolder);
//            if(!uploadDir.exists()) uploadDir.mkdir();
//
//            List<PostImage> postImageList = postImageRepository.findByBoardId(boardId);
//            List<Integer> list = new ArrayList<>();
//            for(PostImage i : postImageList){
//                list.add(i.getImageId());
//            }
//            List<Image> imageList = imageRepository.findByImageIdIn(list);
//            for(Image i : imageList){
//                File file = new File(uploadPath+File.separator+i.getFilePath());
//                if(file.exists()) file.delete();
//            }
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
    @Transactional
    public ResponseMessage insertComment(CommentDto commentDto) {
        ResponseMessage responseMessage = new ResponseMessage();
//        UserDto userDto = userService.getUserWithAuthorities();
        Optional<User> user = SecurityUtil.getCurrentEmail().flatMap(userRepository::findOneWithAuthoritiesByEmail);
        Board board = boardRepository.findByBoardId(commentDto.getBoardId());
        try{
            Comment comment = Comment.builder()
                    .content(commentDto.getContent())
                    .regTime(LocalDateTime.now())
                    .board(board)
                    .user(user.get())
                    .build();
            commentRepository.save(comment);
            responseMessage.setMessage("SUCCESS");
        }catch (Exception e ){
            responseMessage.setMessage("FAIL");
        }
        return responseMessage;
    }

    /**
     * 댓글 수정하기
     * @return
     */
    @Override
    @Transactional
    public ResponseMessage updateComment(Long commentId, CommentDto commentDto) {
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            Comment comment = commentRepository.findByCommentId(commentId);
            comment.updateComment(commentDto.getContent());

            Comment save = commentRepository.save(comment);

            if(save==null){
                responseMessage.setMessage("FAIL");
            }
            else{
                responseMessage.setMessage("SUCCESS");
            }
        }catch (Exception e ){
            responseMessage.setMessage("FAIL");
        }
        return responseMessage;
    }

    /**
     * 댓글 삭제하기
     * @return
     */
    @Override
    @Transactional
    public ResponseMessage deleteComment(Long commentId) {
        ResponseMessage responseMessage = new ResponseMessage();
        try{
            nestedCommentRepository.deleteByCommentCommentId(commentId);
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
//        UserDto userDto = userService.getUserWithAuthorities();
        Optional<User> user = SecurityUtil.getCurrentEmail().flatMap(userRepository::findOneWithAuthoritiesByEmail);
        Comment comment = commentRepository.findByCommentId(replyDto.getCommentId());
        NestedComment nestedComment = NestedComment.builder()
                .content(replyDto.getContent())
                .regTime(LocalDateTime.now())
                .comment(comment)
                .user(user.get())
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
    @Transactional
    public ResponseMessage updateReply(Long replyId, ReplyDto replyDto) {
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
    public ResponseMessage deleteReply(Long replyId) {
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
