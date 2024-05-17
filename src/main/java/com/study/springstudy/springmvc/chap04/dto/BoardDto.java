package com.study.springstudy.springmvc.chap04.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class BoardDto {

    private String title; // 글 제목
    private String content; // 글 내용
    private String writer; // 작성자명
}
