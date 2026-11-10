package com.example.book.entity.book;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// @Table(name="booktbl")
@Builder
@ToString
@NoArgsConstructor      // JPA는 빈 생성자로 객체를 만들고 값을 채움. 필수로 적기
@AllArgsConstructor     // @Builder가 쓰는 전체 생성자
@Getter
@Setter
@Entity      // 이 클래스 == 테이블(이름은 클래스명 -> book)
public class Book {

    @GeneratedValue(strategy = GenerationType.IDENTITY)    // 번호 자동 증가
    @Id      // PK
    private Long id;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String author;

    public void changePrice(int price) {
        this.price = price;
    }
}

// Repository가 공짜로 주는 메서드
// save(entity) : id가 없으면 추가, 있으면 수정
// findAll() : 전체조회 -> List
// findById(id) : 하나 조회 -> Optional
// deleteById(id) : 삭제
// count() : 개수