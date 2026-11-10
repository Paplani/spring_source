package com.example.board.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder 
@Getter 
@Setter 
@AllArgsConstructor
@NoArgsConstructor 
@ToString 
public class BoardDto {
    private Long bno;
    private String title;
    private String content;

    // Member
    private String email;
    private String name;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    private Long replyCount;
}
