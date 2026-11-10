package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean // 객체 생성을 위한 어노테이션
    SecurityFilterChain securityFilterChain(HttpSecurity http) {

        http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/", "/sample/guest").permitAll()
                .requestMatchers("/sample/member").hasRole("USER")
                .requestMatchers("/sample/admin").hasRole("ADMIN")
                .anyRequest().authenticated())
                // .httpBasic(Customizer.withDefaults());
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

    // 비밀번호 암호화
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    UserDetailsService users() {
        UserDetails user = User.builder().username("user1")
                .password("{bcrypt}$2a$10$FlmPTYWRwOqdg5MnMGmWPOeqB9JEGjHOIsg9OLgfOYcUOzm/4nepy")
                .roles("USER").build();

        UserDetails admin = User.builder().username("admin")
                .password("{bcrypt}$2a$10$FlmPTYWRwOqdg5MnMGmWPOeqB9JEGjHOIsg9OLgfOYcUOzm/4nepy")
                .roles("ADMIN", "USER").build();

        return new InMemoryUserDetailsManager(user, admin);
    }

}
