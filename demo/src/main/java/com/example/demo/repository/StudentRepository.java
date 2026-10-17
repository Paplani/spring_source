package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Student;

// Entity명, @Id 쓰인 타입 => 간단한 CRUD 메소드로 구현되어 있음
public interface StudentRepository extends JpaRepository<Student, Long> {

}
