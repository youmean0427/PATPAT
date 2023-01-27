package com.ssafy.patpat.board.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CommentDto {
    private int boardId;
    private int commentId;
    private String author;
    private String content;
}
