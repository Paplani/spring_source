package com.example.book.repository.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.example.book.entity.board.Board;

public interface BoardRepository extends JpaRepository<Board, Long>, QuerydslPredicateExecutor<Board> {

    @Query ("SELECT count(b),avg(b.id), max(b.id) from Board b")
    List<Object[]> aggreate();
}
