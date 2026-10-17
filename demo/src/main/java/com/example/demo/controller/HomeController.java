package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.LoginDto;

import lombok.extern.log4j.Log4j2;

// public : 어디서든 접근 가능
// private: 현재 클래스 안에서만 가능
// 

@Log4j2
@Controller // => template 으로 응답 : thymeleaf, jsp(X)
// @RestController => 데이터만 보낼게 : react
public class HomeController {

    @GetMapping("/")
    public String getHome() {
        // template 이름
        return "home";
    }

    @PostMapping("/login")
    public void postLogin(LoginDto dto) {
        log.info("로그인요청 {}", dto);
        // TemplateInputException: Error resolving template [login]
        // void : login.html 이 존재해야 함
    }

}
