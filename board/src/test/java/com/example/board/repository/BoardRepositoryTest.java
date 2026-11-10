package com.example.board.repository;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.dto.PageRequestDto;
import com.example.board.entity.Board;
import com.example.board.entity.Member;
import com.example.board.entity.Reply;

@SpringBootTest
@Transactional
public class BoardRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Commit
    @Test
    public void memberInsertTest() {

        // for (int i = 0; i < 10; i++) {

        // }

        // for (하나의 타입 변수명 : 리스트명) {

        // }

        IntStream.rangeClosed(1, 10).forEach(i -> {
            Member member = Member.builder().email("user" + i + "@gmail.com").password("1111").name("user" + i).build();
            memberRepository.save(member);
        });

    }

    @Commit
    @Test
    public void boardInsertTest() {

        IntStream.rangeClosed(1, 100).forEach(i -> {

            int no = (int) (Math.random() * 100) + 1;

            String email = "user" + no + "@gmail.com";

            Board board = Board.builder().title("board title" + i).content("board contents" + i)
                    .writer(Member.builder().email(email).build()).build();
            boardRepository.save(board);
        });

    }

    @Commit
    @Test
    public void replyInsertTest() {

        IntStream.rangeClosed(1, 100).forEach(i -> {

            long no = (long) (Math.random() * 100) + 1;

            Reply reply = Reply.builder().text("reply" + i).board(Board.builder().bno(no).build())
                    .replyer("replyer" + i).build();
            replyRepository.save(reply);
        });

    }

    @Commit
    @Test
    public void boardUpdateTest() {

        Board board = boardRepository.findById(1L).get();

        board.changeTitle("change title");

    }

    @Commit
    @Test
    public void boardDeleteTest() {

        // 자식 삭제
        replyRepository.deleteByBno(64L);

        // 부모 삭제

        boardRepository.deleteById(64L);
        ;

    }

    @Transactional(readOnly = true)
    @Test
    public void getBoardWriter() {

        Board board = boardRepository.findById(24L).get();

        System.out.println(board);
        System.out.println(board.getWriter().getName());

        System.out.println(board.getReplies());
    }

    // 번호 제목 댓글개수 작성자 작성일

    @Transactional(readOnly = true)
    @Test
    public void getBoardByBnoTest() {

        Object[] result = boardRepository.getBoardByBno(100L);

        Board board = (Board) result[0];
        Member member = (Member) result[1];
        Long replyCount = (Long) result[2];

        System.out.println(Arrays.toString(result));

    }

    @Transactional(readOnly = true)
    @Test
    public void getBoardListTest() {

        PageRequestDto dto = PageRequestDto.builder().page(1).size(10).build();
        Pageable pageable = PageRequest.of(dto.getPage(), dto.getSize(),
                Sort.by("bno").descending().and(Sort.by("title").ascending()));
        Page<Object[]> result = boardRepository.list("tc", "board", pageable);
        System.out.println(result.getTotalPages());

        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));
        }

    }

}
