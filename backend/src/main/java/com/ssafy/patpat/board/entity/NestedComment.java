package com.ssafy.patpat.board.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NestedComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nestedCommentId;
    private String content;
    private String nickName;
    private LocalDateTime regTime;
    private Long commentId;
    private Long userId;

    public void updateNestedComment(String content){
        this.content = content;
    }
}
