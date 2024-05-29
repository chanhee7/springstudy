package com.study.springstudy.springmvc.chap05.mapper;

import com.study.springstudy.springmvc.chap04.entity.Board;
import com.study.springstudy.springmvc.chap04.mapper.BoardMapper;
import com.study.springstudy.springmvc.chap05.entity.Reply;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyMapperTest {

    @Autowired BoardMapper boardMapper;
    @Autowired ReplyMapper replyMapper;

//    @Test
//    @DisplayName("게시물 100개와 댓글 5000개를 랜덤으로 등록")
//    void bulkInsert() {
//        // 게시물 100개 등록
//        for (int i = 1; i <= 100; i++) {
//            Board board = Board.builder()
//                    .title("재밌는 글" + i)
//                    .writer("재밌음" + i)
//                    .content("아무무" + i)
//                    .build();
//            boardMapper.save(board);
//        }
//
//        // 댓글 5000개 등록
//        for (int i = 1; i <= 5000; i++) {
//            Reply reply = Reply.builder()
//                    .replyText("하하호호" + i)
//                    .replyWriter("끼끼끼" + i)
//                    .boardNo((long) (Math.random() * 100 + 1))
//                    .build();
//            replyMapper.save(reply);
//        }
//    }


    @Test
    @DisplayName("전체 조회")
    void findAllTest() {
        //given
        long boardNo = 1;
        //when
        List<Reply> replies = replyMapper.findAll(boardNo, null);
        //then
        replies.forEach(System.out::println);
    }

    @Test
    @DisplayName("댓글 삭제")
    void deleteTest() {
        //given
        long replyNo = 1;
        //when
        replyMapper.delete(replyNo);
        //then
    }
    
    @Test
    @DisplayName("수정")
    void modifyTest() {
        //given
        long replyNo = 2;
        Reply reply = Reply.builder()
                .replyNo(replyNo)
                .replyText("수정")
                .build();
        //when
        replyMapper.modify(reply);
        //then
    }


}