package com.study.springstudy.database.chap01;

import com.study.springstudy.springmvc.chap03.entity.Score;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor  // final 붙은 필드만 초기화 해줌
public class SpringJdbc {

    // @RequiredArgsConstructor 을 사용해서 final 안붙여주면 null 발생 ( 값이 안들어감 )
    private final JdbcTemplate template;

    // @Autowired (생성자 하나라 생략가능)  // @RequiredArgsConstructor 사용해서 생략 가능
//    public SpringJdbc(JdbcTemplate template) {
//        this.template = template;
//    }

    // INSERT
    public int save(Person person) {
        String sql = "INSERT INTO tbl_person VALUES (?, ?, ?)";
        return template.update(sql, person.getId(), person.getPersonName(), person.getPersonAge());
    }


    // DELETE
    public boolean delete(long id) {
        String sql = "DELETE FROM tbl_person WHERE id = ?";
        int result = template.update(sql, id);
        return result == 1;
    }


    // UPDATE
    public boolean update(Person newPerson) {
        // 이름, 나이 수정
        String sql = "UPDATE tbl_person " + "SET person_name = ?, person_age = ? " + "WHERE id = ?";
        int flag = template.update(sql, newPerson.getPersonName(), newPerson.getPersonAge(), newPerson.getId());
        return flag == 1;
    }


    // SELECT : 다중행 조회
    public List<Person> findAll() {
        String sql = "SELECT * FROM tbl_person";
        // 람다식 X
        List<Person> people = template.query(sql, new RowMapper<Person>() {
            @Override
            public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Person(rs);
            }
        });
        return people;
    }


    // SELECT : 단일행 조회
    public Person findOne(long id) {
        String sql = "SELECT * FROm tbl_person WHERE id = ?";
        // 람다식
        Person person = template.queryForObject(sql, (rs, n) -> new Person(rs), id);
        return person;
    }


    // 내부 클래스
//    public static class PersonMapper implements RowMapper<Person> {
//
//        @Override
//        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
//            return new Person(rs);
//        }
//    }

}
