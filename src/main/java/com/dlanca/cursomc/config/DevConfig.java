package com.dlanca.cursomc.config;

import com.dlanca.cursomc.services.DBService;
import com.dlanca.cursomc.services.EmailService;
import com.dlanca.cursomc.services.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean InstantiateDataBase() throws ParseException {

        if(!"create".equals(strategy)){
            return false;
        }

        dbService.InstantiateTestDataBase();
        return true;
    }

    @Bean
    public EmailService emailService(){
        return new SmtpEmailService();
    }
}
