package com.example.memo.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import aQute.bnd.annotation.headers.BundleLicense;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@EntityListeners(value = AuditingEntityListener.class)
@Table(name = "memotbl")
@Entity
public class Memo {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "mno")
    private Long id;

    @Column(nullable = false, length = 100, name = "memo_text")
    private String text;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    public void changeText(String text) {
        this.text = text;
    }

}
