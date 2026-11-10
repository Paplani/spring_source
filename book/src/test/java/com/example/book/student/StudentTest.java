package com.example.book.student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.book.entity.student.Student;
import com.example.book.entity.student.Grade;
import com.example.book.repository.student.StudentRepository;

// 테스트용 클래스
@SpringBootTest
public class StudentTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void insertTest() {
        Student student = new Student();
        student.setName("홍길동");
        student.setGrade(Grade.FRESHMAN);

        System.out.println(studentRepository.save(student));

    }

    @Test
    public void updateTest() {

        // 수정할 대상 찾기
        Student student = studentRepository.findById(1L).get();
        student.setName("성춘향");
        System.out.println(studentRepository.save(student));

    }

    @Test
    public void deleteTest() {

        // Student student = studentRepository.findById(1L).get();
        // studentRepository.delete(student);

        studentRepository.deleteById(1L);

    }
}
