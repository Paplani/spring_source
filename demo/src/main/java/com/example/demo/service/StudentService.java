package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.StudentDto;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service // repository 쪽의 메소드를 호출한 결과를 컨트롤러로 넘기는 역학
public class StudentService {
    private final StudentRepository studentRepository;

    public List<StudentDto> getList() {
        List<Student> list = studentRepository.findAll();

        // List<StudentDto> students = list.stream().map(s -> )

        List<StudentDto> students = new ArrayList<>();
        for (Student s : list) {
            StudentDto dto = new StudentDto();
            dto.setId(s.getId());
            dto.setName(s.getName());
            dto.setGrade(s.getGrade());
            dto.setCreatedDate(s.getCreatedDate());
            dto.setUpdatedDate(s.getUpdatedDate());
            students.add(dto);
        }

        return students;
    }

}
