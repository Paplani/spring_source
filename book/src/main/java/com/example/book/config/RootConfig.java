package com.example.book.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 스프링 설정 파일
public class RootConfig {

    @Bean // 이 메ㅓ드가 돌려주는 객체를 Bean 으로 보관
    ModelMapper getMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setFieldMatchingEnabled(true)     
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.LOOSE); // userName, user_name

        return modelMapper;

    }
}
