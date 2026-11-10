package com.example.book.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.book.dto.BookDto;
import com.example.book.dto.PageRequestDto;
import com.example.book.dto.PageResultDto;
import com.example.book.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;

@RequestMapping("/books")
@RequiredArgsConstructor
@RestController // 화면단이 데이터만 받기를 원하기 때문에 지금의 타입
public class BookController {

    private final BookService service;

    @GetMapping("")
    @Operation(summary = "도서 목록 조회", description = "전체 도서 목록을 조회합니다.")
    public PageResultDto<BookDto> getBooks(PageRequestDto dto) {

        PageResultDto<BookDto> result =  service.list(dto);

        return result;

    }

    @GetMapping("/{id}")
    @Operation(summary = "도서 조회", description = "특정 도서를 id 기준으로 조회합니다.")
    // 경로 받기 : @PathVariable Long ~
    public BookDto getBookById(@PathVariable Long id) {

        return service.readId(id);

    }

    @GetMapping("/title")
    @Operation(summary = "도서 조회", description = "특정 도서를 title 기준으로  조회합니다.")
    // 쿼리 받기 -> 메서드 안의 매개변수로
    public BookDto getBookByTitle(String title) {

        return service.readTitle(title);

    }

    @GetMapping("/isbn")
    @Operation(summary = "도서 조회", description = "특정 도서를 isbn 기준으로  조회합니다.")
    public BookDto getBookByIsbn(String isbn) {

        return service.readIsbn(isbn);

    }

    // form 형태로 전송 <form method = "post">
    // 어노테이션 없이 DTO (demo LoginDto, input의 name과 필드명이 같아야 함)로 받음
    @PostMapping("")
    @Operation(summary = "도서 삽입", description = "특정 도서 삽입합니다.")
    // 본문 json -> @RequestBody BookRequestDto 가 값을 받음 -> create 서비스 함수로 id 리턴받음
    public Long postBook(@RequestBody BookDto dto) {

        return service.create(dto);

    }

    @PutMapping("/{id}")
    @Operation(summary = "도서 수정", description = "특정 도서를 삽입 수정합니다.")
    public Long putBook(@PathVariable Long id, @RequestBody BookDto dto) {
        dto.setId(id);
        service.update(dto);
        return id;

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "도서 삭제", description = "특정 도서 삭제합니다.")
    public ResponseEntity<Void> deleteMemo(@PathVariable Long id) {

        service.delete(id);

        // Http 상태코드 보낸다면?
        return ResponseEntity.noContent().build();
    }
}
