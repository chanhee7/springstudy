package com.study.springstudy.springmvc.chap04.dto;

import com.study.springstudy.springmvc.chap04.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// 서버에서 조회한 데이터 중 화면에 필요한 데이터만 모아놓은 클래스
@Getter
public class BoardListResponseDto {

    /*
        {
            shortTitle: "",

        }
     */

    private int bno; // 원본 게시물 번호
    private String shortTitle; // 줄임 처리된 제목 (5글자 이상일때)
    private String shortContent; // 줄임 처리된 글 내용 (30글자 이상일때)
    private String date; // 포맷팅된 날짜문자열
    private int view; // 조회 수
    private boolean hit; // HIT 게시물인가?
    private boolean newArticle; // 새 게시물인가? (등록 5분 이내)
    private int replyCount; // 댓글 수
    private String account;

    // 엔터티를 DTO 로 변환하는 생성자
    public BoardListResponseDto(BoardFindAllDto b) {
        this.bno = (int) b.getBoardNo();
        this.shortTitle = makeShortTitle(b.getTitle());
        this.shortContent = makeShortContent(b.getContent());

        // 게시물 등록 시간
        LocalDateTime time = b.getRegDateTime();
        this.date = dateFormatting(time);
        this.view = b.getViewCount();
        this.hit = this.view >= 5;
        this.newArticle = LocalDateTime.now().isBefore(time.plusMinutes(5));
        this.replyCount = b.getReplyCount();
        this.account = b.getAccount();
    }

    private String dateFormatting(LocalDateTime regDateTime) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return pattern.format(regDateTime);
    }

    private String makeShortContent(String content) {
        return (content.length() > 30) ? content.substring(0, 30) + "..." : content;
    }

    private String makeShortTitle(String title) {
        return (title.length() > 5) ? title.substring(0, 5) + "..." : title;
    }
}
