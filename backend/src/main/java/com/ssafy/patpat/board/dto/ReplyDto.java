package com.ssafy.patpat.board.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ReplyDto {
    @Schema(example = "댓글Id")
    private Long commentId;
    @Schema(example = "대댓글Id")
    private Long replyId;
    @Schema(example = "등록시간")
    private LocalDateTime regDt;
    @Schema(example = "작성자")
    private String author;
    @Schema(example = "본문")
    private String content;
    @Schema(example = "유저id")
    private Long userId;
}
