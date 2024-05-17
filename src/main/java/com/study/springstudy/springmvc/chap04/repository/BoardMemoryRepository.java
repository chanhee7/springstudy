package com.study.springstudy.springmvc.chap04.repository;

import com.study.springstudy.springmvc.chap04.entity.Board;

import java.util.List;

public class BoardMemoryRepository implements BoardRepository {

    @Override
    public List<Board> findAll() {
        return List.of();
    }

    @Override
    public Board findOne(int boardNo) {
        return null;
    }

    @Override
    public boolean save(Board board) {
        return false;
    }

    @Override
    public boolean delete(int boardNo) {
        return false;
    }
}
