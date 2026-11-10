package com.example.book.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.book.dto.StudentDto;
import com.example.book.entity.student.Student;
import com.example.book.repository.student.StudentRepository;

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
        for (Student s : list) {         // Entity -> DTO 옮겨 담기
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

// 서버가 켜질 때 스프링이 @Service, @Controller, Repository 같은 표시가 붙은 클래스를 찾아서 미리 하나씩 만들어 보관해요. 이렇게 보관된 객체를 빈(Bean)이라고 하고, 보관하는 곳을 스프링 컨테이너라고 해요.
// @RequiredArgsConstructor가 final 필드를 받는 생성자를 만들어요.
// 스프링이 그 생성자에 보관해 둔 Repository를 넣어 줘요(주입).
// FastAPI에서 db: Session = Depends(get_db)라고 쓰면 세션이 알아서 들어오던 것과 같은 원리예요.

