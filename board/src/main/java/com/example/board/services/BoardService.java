package com.example.board.services;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.board.dto.BoardDto;
import com.example.board.dto.PageRequestDto;
import com.example.board.dto.PageResultDto;
import com.example.board.entity.Board;
import com.example.board.entity.Member;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.ReplyRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Transactional
@Service
@RequiredArgsConstructor
@Log4j2
public class BoardService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    // 기본타입 / 객체 타입
    // int => Integer
    // long => Long

    public Long insert(BoardDto insertBoardDto) {
        // dto => entity
        Board board = dtoToEntity(insertBoardDto);
        return boardRepository.save(board).getBno();
    }

    public void update(BoardDto upDto) {

        Board board = boardRepository.findById(upDto.getBno()).get();

        // 수정 메소드 호출
        board.changeContent(upDto.getContent());
        board.changeTitle(upDto.getTitle());
        // save 는 생략가능 == dirty check 해줌
    }

    public void delete(Long bno) {

        // 댓글부터 삭제
        replyRepository.deleteByBno(bno);
        // 원본글 삭제
        boardRepository.deleteById(bno);
    }

    // 상세 조회 (아래 entityToDto의 리턴타입은 BoardDto로 dto가 나옴)
    public BoardDto getBoard(Long bno) {
        Object[] result = boardRepository.getBoardByBno(bno);
        // entity => Dto
        return entityToDto((Board) result[0], (Member) result[1], (Long) result[2]);
    }

    public PageResultDto<BoardDto> getBoards(PageRequestDto requestDto) {

        Pageable pageable = PageRequest.of(requestDto.getPage() - 1, requestDto.getSize(),
                Sort.by("bno").descending().and(Sort.by("title").ascending()));

        Page<Object[]> result = boardRepository.list(requestDto.getType(), requestDto.getKeyword(), pageable);

        // 내용은 아래모양으로 들어있음
        // result.getContent();

        // java.util
        Function<Object[], BoardDto> fn = (entity -> entityToDto((Board) entity[0], (Member) entity[1],
                (Long) entity[2]));
        List<BoardDto> dtoList = result.stream().map(fn).collect(Collectors.toList());

        Long totalCount = result.getTotalElements();

        PageResultDto<BoardDto> pageResultDto = PageResultDto.<BoardDto>withAll().dtoList(dtoList)
                .totalCount(totalCount).pageRequestDto(requestDto)
                .build();

        return pageResultDto;
    }

    private Board dtoToEntity(BoardDto dto) {
        Board board = Board.builder().bno(dto.getBno()).title(dto.getTitle()).content(dto.getContent())
                .writer(Member.builder().email(dto.getEmail()).build())
                .build();
        return board;
    }

    private BoardDto entityToDto(Board board, Member member, Long replyCount) {
        BoardDto dto = BoardDto.builder().bno(board.getBno()).title(board.getTitle()).content(board.getContent())
                .createdDate(board.getCreatedDate()).email(member.getEmail()).name(member.getName())
                .replyCount(replyCount)
                .build();
        return dto;
    }

}
