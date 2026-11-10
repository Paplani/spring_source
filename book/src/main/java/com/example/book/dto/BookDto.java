package com.example.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@Data
public class BookDto {
    private Long id;
    private String isbn;
    private String title;
    private int price;
    private String author;
}

// DTO(Data Transfer Object) : 화면과 주고받는 데이터 상자
// Entity가 DB 안쪽 모양, DTO가 바깥에 보여줄 모양, entity = models, dto = schemas
// Entity : DB 테이블과 1:1로 연관된 객체. Repository가 다룸(Repository가 DB에 접근하기 때문)
// DTO : 요청&응답에 필요한 값만. Controller가 다룸(주소 받기 때문 -> python 서버에서 router랑 같음)
// Service가 둘 사이를 변환. 들어올 땐 DTO → Entity, 나갈 땐 Entity → DTO.

// memo, book: ModelMapper가 이름이 같은 필드끼리 자동으로 복사
// BookRequestDto dto = mapper.map(book, BookRequestDto.class);    Entity → DTO
// Book book = mapper.map(dto, Book.class);                        DTO → Entity