package com.example.memo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.memo.dto.MemoDto;
import com.example.memo.dto.MemoRequestDto;
import com.example.memo.service.MemoService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

// @Controller // 데이터는 담고, 템플릿 리턴
@RestController // 데이터만 전송할거야
@RequestMapping("/memo")
@RequiredArgsConstructor
@Tag(name = "Response Memo", description = "Response Memo API")
public class MemoController {

    private final MemoService memoService;

    @GetMapping("")
    @Operation(summary = "memo 목록 조회", description = "전체 memo 목록을 조회합니다.")
    public List<MemoDto> getMemos() {

        return memoService.readAll();

    }

    // http://localhost:8080/memo/1
    @GetMapping("/{id}")
    @Operation(summary = "memo 조회", description = "특정 memo 조회합니다.")
    public MemoDto getMemo(@PathVariable Long id) {

        return memoService.read(id);

    }

    @PostMapping("")
    @Operation(summary = "memo 삽입", description = "특정 memo 삽입합니다.")
    public Long postMemo(@RequestBody MemoRequestDto dto) {

        return memoService.create(dto);

    }

    @PutMapping("/{id}")
    @Operation(summary = "memo 수정", description = "특정 memo 수정합니다.")
    public Long putMemo(@PathVariable Long id, @RequestBody MemoRequestDto dto) {

        return memoService.update(id, dto);

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "memo 삭제", description = "특정 memo 삭제합니다.")
    public ResponseEntity<Void> deleteMemo(@PathVariable Long id) {

        memoService.delete(id);

        // Http 상태코드 보낸다면?
        return ResponseEntity.noContent().build();

    }

}
