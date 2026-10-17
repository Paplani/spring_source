package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.StudentDto;
import com.example.demo.service.StudentService;

import lombok.RequiredArgsConstructor;

// 객체를 생성 후 스프링 컨테이너가 해당 객체 관리
// @Component, @Controller, @Service @Repository
// 객체생성방법 -> 생성자, setter 이용



// template 사용
// void => /student/list => /student/list 경로를 통해 template 찾기
// String => string 값을 기준으로 template 찾기

@RequiredArgsConstructor
@RequestMapping("/student")
@Controller // 객체를 생성 후 스프링 컨테이너가 해당 객체 관리
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/list")
    public void getStudents(Model model) {
        List<StudentDto> list = studentService.getList();
        model.addAttribute("list", list);
        // return "list";
    }

}
