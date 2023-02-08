package com.ssafy.patpat.board.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CommentDto {
    @Schema(example = "게시판Id")
    private Long boardId;
    @Schema(example = "댓글Id")
    private Long commentId;
    @Schema(example = "작성자")
    private String author;
    @Schema(example = "본문")
    private String content;
    @Schema(example = "등록시간")
    private LocalDateTime regDt;
    @Schema(example = "대댓글 리스트")
    private List<ReplyDto> replyList;
    @Schema(example = "유저id")
    private Long userId;
}
