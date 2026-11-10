package com.example.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchBoardRepository {
    
    // 하나 가져오기
    Object[] getBoardByBno(Long bno);

    // 전체 리스트
    Page<Object[]> list(String type, String keyword, Pageable pageable);

    
}
