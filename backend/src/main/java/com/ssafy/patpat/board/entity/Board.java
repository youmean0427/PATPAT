package com.ssafy.patpat.board.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardId;
    private Long userId;
    private String nickName;
    private String title;
    private String content;
    private LocalDateTime dateTime;
    private int count;
    private int postCode;

    public void update(String title,String content){
        this.title = title;
        this.content = content;
    }
}
