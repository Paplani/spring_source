package com.example.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder     // super : 부모 클래스의 필드까지 포함해서
@AllArgsConstructor
@NoArgsConstructor     // Default 생성자
public class PageRequestDto {
    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;

    private String type;
    private String keyword;
}


