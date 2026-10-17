package com.example.demo.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.demo.entity.constants.Grade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// fastapi기준 model

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Setter
@Getter
@EntityListeners(value = AuditingEntityListener.class)
@Table(name = "studenttbl")
@Entity
public class Student {
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sname", length = 50, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Grade grade;

    // @CreationTimestamp : Hibernate 이용
    @CreatedDate // Spring data jpa
    private LocalDateTime createdDate;

    // @UpdateTimestamp : Hibernate 이용
    @LastModifiedDate // 데이터 변경 일어날 때 자동으로 삽입
    private LocalDateTime updatedDate;

}
