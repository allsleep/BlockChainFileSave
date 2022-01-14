package com.config;

import com.CommonMethods.IdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RandomIdConfig {
    @Bean
    public IdWorker idWorker() {
        return new IdWorker(0,0);
    }
}
