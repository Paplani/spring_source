package com.example.board.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
// @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "writer", "replies" })
@Entity
@Builder
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 자동 증가
    @Column(name = "bno")
    private Long bno;

    @Column(length = 100)
    private String title;

    @Column(length = 1500)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id")
    private Member writer;

    @Builder.Default
    @OneToMany(mappedBy = "board")
    private List<Reply> replies = new ArrayList<>();

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }

}
