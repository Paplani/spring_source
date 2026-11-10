package com.example.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/sample")
public class SampleController {

    @GetMapping("/guest")
    public void getGuest() {
        log.info("guest 페이지 요청");

    }

    @GetMapping("/member")
    public void getMember() {
        log.info("member 페이지 요청");

    }

    @GetMapping("/admin")
    public void getAdmin() {
        log.info("admin 페이지 요청");

    }

}
