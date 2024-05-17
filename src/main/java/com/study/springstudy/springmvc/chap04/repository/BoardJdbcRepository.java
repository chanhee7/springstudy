package com.study.springstudy.springmvc.chap04.repository;

import com.study.springstudy.springmvc.chap04.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardJdbcRepository implements BoardRepository {

    private final JdbcTemplate template;

    @Override
    public List<Board> findAll() {
        return List.of();
    }

    @Override
    public Board findOne(int boardNo) {
        String sql = "SELECT * FROM tbl_board WHERE bno = ?";
        return template.queryForObject(sql, (rs, n) -> new Board(), boardNo);
    }

    @Override
    public boolean save(Board board) {
        String sql = "INSERT INTO tbl_board " +
                "(boardNo, title, content, writer, viewCount, regDatetime) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        return template.update(sql, board.getBoardNo(), board.getTitle(), board.getContent(),
                board.getWriter(), board.getViewCount(), board.getRegDateTime()) == 1;
    }

    @Override
    public boolean delete(int boardNo) {
        String sql = "DELETE FROM tbl_board WHERE bno = ?";
        return template.update(sql, boardNo) == 1;
    }
}
