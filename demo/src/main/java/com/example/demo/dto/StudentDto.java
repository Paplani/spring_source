package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.entity.constants.Grade;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class StudentDto {
    private Long id;
    private String name;
    private Grade grade;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
