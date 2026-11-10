package com.example.book.board;

import java.util.List;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.book.entity.board.Board;
import com.example.book.entity.board.QBoard;
import com.example.book.repository.board.BoardRepository;

@SpringBootTest
public class BoardTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insert() {

        LongStream.rangeClosed(0, 100).forEach((b) -> {

            Board board = Board.builder().title("title" + b).content("content" + b).writer("writer" + b).build();
            boardRepository.save(board);
        });
    }

    @Test
    public void querydslTest() {

        // new 해서 새로운 객체 생성한것과 같은 효과
        QBoard board = QBoard.board;

        // title = title 1 조회
        System.out.println(boardRepository.findAll(board.title.eq("title1")));

        // title like '%title1%'
        System.out.println(boardRepository.findAll(board.title.contains("title1")));

        // title like '%title1%' and id > 0 order by id desc
        System.out.println(boardRepository.findAll(board.title.contains("title1").and(board.id.gt(0L)),
                Sort.by("id").descending()));

        // page 나누기
        Pageable pageable = PageRequest.of(2, 20);
        Page<Board> result = boardRepository.findAll(board.id.gt(0L), pageable);

        System.out.println(result);
        System.out.println("page size" + result.getSize());
        System.out.println("page totalPage" + result.getTotalPages());
        System.out.println("page Elements" + result.getTotalElements());
        System.out.println("page content" + result.getContent());
    }

    @Test
    public void aggreateTest() {
        List<Object[]> result = boardRepository.aggreate();

        // System.out.println(result); // [[Ljava.lang.Object;@1531ec6]

        for(Object[] objects:result){
            System.out.println(objects[0]);   // 101
            System.out.println(objects[1]);   // 51.0
            System.out.println(objects[2]);   // 101
            // BoardRepository.java에 있는 Query 에서 3개를 선택하는 쿼리를 작성했음
        }
    }
}
