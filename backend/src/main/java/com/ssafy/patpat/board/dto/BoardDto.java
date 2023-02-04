package com.ssafy.patpat.board.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ssafy.patpat.common.dto.FileDto;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "보드 객체")
public class BoardDto {
    @Schema(example = "게시판Id")
    private int boardId;
    @Schema(example = "제목")
    private String title;
    @Schema(example = "작성자")
    private String author;
    @Schema(example = "등록일")
    private LocalDate registDate;
    @Schema(example = "조회수")
    private int count;
    @Schema(example = "본문")
    private String content;
    @Schema(example = "타입코드")
    private int typeCode;
    @Schema(example = "댓글 리스트(" +
            "    댓글 id = commentId" +
            "    댓글 작성자 = author" +
            "    내용 = content)")
    private List<CommentDto> commentList;
    @Schema(example = "파일 url 리스트("+
            "    파일 url = filePath)")
    private List<FileDto> fileUrlList;
    @Schema(example = "썸네일")
    private FileDto thumbnail;
}
