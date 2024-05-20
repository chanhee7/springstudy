package com.study.springstudy.springmvc.chap04.dto;

import com.study.springstudy.springmvc.chap04.entity.Board;
import lombok.*;

// dto의 필드명은 반드시 html form 태그의 입력태그들 name 속성와 일치해야 함.
// 클라이언트 혹은 JSP 와 데이터 주고받는 역할
@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BoardWriteRequestDto {

    private String title; // 글 제목
    private String content; // 글 내용
    private String writer; // 작성자명

    public Board toEntity() {
        Board b = new Board();
        b.setContent(this.content);
        b.setWriter(this.writer);
        b.setTitle(this.title);
        return b;
    }
}
