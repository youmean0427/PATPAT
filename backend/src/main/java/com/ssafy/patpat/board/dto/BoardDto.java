package com.ssafy.patpat.board.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ssafy.patpat.common.dto.FileDto;
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
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class BoardDto {
    private int boardId;
    private String title;
    private String author;
    private LocalDate registDate;
    private int count;
    private String content;
    private List<CommentDto> comment;
    private List<ReplyDto> reply;
    private List<FileDto> fileUrlList;
}
