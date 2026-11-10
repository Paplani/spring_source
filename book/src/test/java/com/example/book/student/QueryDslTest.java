package com.example.book.student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import com.example.book.entity.student.QStudent;
import com.example.book.repository.student.StudentRepository;

@SpringBootTest
public class QueryDslTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void querydslTest() {
        QStudent student = QStudent.student;

        // 학생의 이름이 홍길동이면 모두 찾기
        // where s.name = "홍길동"
        // @Query()
        studentRepository.findAll(student.name.eq("홍길동"));
        // %홍길동%
        studentRepository.findAll(student.name.contains("홍길동"));
        //
        studentRepository.findAll(student.name.contains("홍길동").and(student.id.gt(0L)));

        studentRepository.findAll(student.name.contains("홍길동").and(student.id.gt(0L)), 
        Sort.by("id").descending());

    }

}
