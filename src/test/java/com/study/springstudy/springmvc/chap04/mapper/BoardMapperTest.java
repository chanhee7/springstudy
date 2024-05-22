package com.study.springstudy.springmvc.chap04.mapper;

import com.study.springstudy.springmvc.chap04.entity.Board;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardMapperTest {

    @Autowired
    BoardMapper mapper;

    @Test
    @DisplayName("테스트 게시물 300개 생성한다")
    void insertTest() {
        for (int i = 0; i <= 300; i++) {
            Board b = new Board();
            b.setTitle("테스트 제목"+i);
            b.setWriter("테스트 작성자"+i);
            b.setContent("테스트 내용"+i);

            mapper.save(b);
        }
    }
}