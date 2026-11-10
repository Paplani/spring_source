package com.example.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class SecurityTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testEncoder() {
        // 원 비밀번호
        String rawPassword = "1111";

        // 단방향 암호
        String encodePass = passwordEncoder.encode(rawPassword);
        System.out.println("비밀번호 확인 " + rawPassword + " 인코딩 비밀번호 " + encodePass);

        System.out.println(passwordEncoder.matches(rawPassword, encodePass));
        System.out.println(passwordEncoder.matches("2222", encodePass));

    }

}
