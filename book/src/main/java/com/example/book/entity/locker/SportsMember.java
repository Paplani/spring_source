package com.example.book.entity.locker;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
// @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "locker")
@Entity
@Builder
public class SportsMember {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_name")
    private String name;

    // 테이블명_pk명 (default)
    @JoinColumn(name = "locker_id") // 해당이름으로 fk 지정
    @OneToOne(fetch = FetchType.LAZY)
    private Locker locker;
}
