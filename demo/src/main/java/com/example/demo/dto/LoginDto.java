package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// lombok

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoginDto {
    private String id;
    private String password;

}
