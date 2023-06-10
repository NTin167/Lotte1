package com.example.lotte.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class AppConfig {
    // Các cấu hình khác
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}