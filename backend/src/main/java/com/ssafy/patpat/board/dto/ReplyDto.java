package com.ssafy.patpat.board.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ReplyDto {
    @Schema(example = "댓글Id")
    private int commentId;
    @Schema(example = "대댓글Id")
    private int replyId;
    @Schema(example = "작성자")
    private String author;
    @Schema(example = "본문")
    private String content;
}
