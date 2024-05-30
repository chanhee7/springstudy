package com.study.springstudy.springmvc.chap05.mapper;

import com.study.springstudy.springmvc.chap05.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberMapperTest {

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    PasswordEncoder encoder;

    @Test
    @DisplayName("회원가입에 성공해야 한다")
    void saveTest() {
        //given
        Member member = Member.builder()
                .account("찬")
                .password("1234")
                .name("CHAN")
                .email("chan@naver.com")
                .build();
        //when
        boolean flag = memberMapper.save(member);
        //then
        assertTrue(flag);
    }

    @Test
    @DisplayName("찬 계정명을 조회하면 그 회원의 이름이 CHAN 이다")
    void findOneTest() {
        //given
        String acc = "찬";
        //when
        Member foundMember = memberMapper.findOne(acc);
        //then
        assertEquals(foundMember.getName(), "CHAN");
    }

    @Test
    @DisplayName("계정명이 찬인 회원은 중복확인 결과가 true 이다")
    void existsTest() {
        //given
        String acc = "찬";
        //when
        boolean flag = memberMapper.existsById("account", acc);
        //then
        assertTrue(flag);
    }

    @Test
    @DisplayName("계정명이 희인 회원은 중복확인 결과가 false 이다")
    void existsTest2() {
        //given
        String acc = "희";
        //when
        boolean flag = memberMapper.existsById("account", acc);
        //then
        assertFalse(flag);
    }

    @Test
    @DisplayName("평문의 암호를 인코딩해야 한다")
    void encodingTest() {
        //given
        String rawPassword = "abc1234";
        //when
        String encodedPassword = encoder.encode(rawPassword);
        //then
        System.out.println("encodedPassword = " + encodedPassword);
    }

}