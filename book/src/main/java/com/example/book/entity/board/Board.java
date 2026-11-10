package com.example.book.entity.board;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
// @Setter
@NoArgsConstructor 
@AllArgsConstructor 
@ToString 
@Entity 
@Builder 
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 자동 증가
    @Column(name = "bno")
    private Long id;

    @Column(length = 100)
    private String title;

    @Column(length = 1500)
    private String content;

    @Column(length = 50)
    private String writer;
}
