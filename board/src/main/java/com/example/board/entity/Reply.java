package com.example.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
// @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"board"})
@Entity
@Builder
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 자동 증가
    private Long rno;

    private String text;

    private String replyer;


    @ManyToOne (fetch = FetchType.LAZY, optional = false)
    private Board board;
}
