package com.study.springstudy.database.chap02;

import com.study.springstudy.database.chap01.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonMapperTest {

    @Autowired
    PersonMapper mapper;

    @Test
    @DisplayName("mybatis mapper 로 사람 정보를 등록한다.")
    void saveTest() {
        //given
        Person p = new Person(9999, "마바맨", 59);

        //when
        boolean flag = mapper.save(p);

        //then
        assertTrue(flag);
    }

    @Test
    @DisplayName("id 값으로 사람 정보를 삭제한다.")
    void delTest() {
        //given
        long id = 9999;

        //when
        boolean flag = mapper.delete(id);

        //then
        assertTrue(flag);
    }
    
    @Test
    @DisplayName("id 값이 66인 사람의 정보를 수정한다.")
    void updateTest() {
        //given
        Person person = new Person(66, "육육이", 10);

        //when
        boolean flag = mapper.update(person);

        //then
        assertTrue(flag);
    }

    @Test
    @DisplayName("전체조회하면 결과 건수가 5건이다.")
    void findAllTest() {
        //given
        
        //when
        List<Person> people = mapper.findAll();
        
        //then
        people.forEach(System.out::println);
        assertEquals(5, people.size());
    }

    @Test
    @DisplayName("id 값으로 사람 정보를 개별 조회한다")
    void findOneTest() {
        //given
        long id = 66;

        //when
        Person person = mapper.findOne(id);

        //then
        System.out.println("person = " + person);
        assertEquals("육육이", person.getPersonName());
    }

    @Test
    @DisplayName("사람수와 이름리스트를 조회한다")
    void findNamesTest() {
        //given

        //when
        List<String> names = mapper.findNames();
        int count = mapper.count();

        //then
        names.forEach(System.out::println);
        System.out.println("count = " + count);
    }

}