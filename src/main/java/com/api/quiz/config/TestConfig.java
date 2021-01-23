package com.api.quiz.config;

import com.api.quiz.services.DBService;
import com.api.quiz.services.EmailService;
import com.api.quiz.services.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {
    
    @Autowired
    private DBService dbService;
    
    @Bean
    public boolean instantiateDatabase() {
        dbService.intantiateTestDatabase();
        return true;
    }
    
    @Bean
    public EmailService emailService() {
        return new MockEmailService();
    }

}