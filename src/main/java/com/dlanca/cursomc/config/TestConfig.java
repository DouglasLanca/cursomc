package com.dlanca.cursomc.config;

import com.dlanca.cursomc.services.DBService;
import com.dlanca.cursomc.services.EmailService;
import com.dlanca.cursomc.services.MockMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean InstantiateDataBase() throws ParseException {
        dbService.InstantiateTestDataBase();
        return true;
    }

    @Bean
    public EmailService emailService(){
        return new MockMailService();
    }
}
