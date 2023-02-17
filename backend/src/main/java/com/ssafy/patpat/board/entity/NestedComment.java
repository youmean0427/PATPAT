package com.ssafy.patpat.board.entity;

import com.ssafy.patpat.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private LocalDateTime regTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    public void updateNestedComment(String content){
        this.content = content;
    }
}
