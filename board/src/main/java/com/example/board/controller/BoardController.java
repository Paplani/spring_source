package com.example.board.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.dto.BoardDto;
import com.example.board.dto.PageRequestDto;
import com.example.board.dto.PageResultDto;
import com.example.board.services.BoardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
@RestController
@Tag(name = "Board", description = "Board API")
public class BoardController {

    private final BoardService boardService;

    // 페이지 나누기 조회
    @GetMapping("")
    @Operation(summary = "board 목록 조회")
    public PageResultDto<BoardDto> getList(PageRequestDto pageRequestDto) {
        return boardService.getBoards(pageRequestDto);
    }

    // 하나 조회
    @GetMapping("/{bno}")
    public BoardDto getBoard(@PathVariable Long bno) {
        return boardService.getBoard(bno);
    }

    @PutMapping("path/{bno}")
    public ResponseEntity<String> putMethodName(@PathVariable Long bno, @RequestBody BoardDto boardDto) {
        boardDto.setBno(bno);

        boardService.update(boardDto);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @PostMapping("path")
    public Long postMethodName(@RequestBody BoardDto boardDto) {
        log.info("board 추가 {}", boardDto);

        return boardService.insert(boardDto);
    }

    @DeleteMapping("/{bno}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long bno) {
        boardService.delete(bno);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

}
