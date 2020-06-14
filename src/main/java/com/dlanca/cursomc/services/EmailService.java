package com.dlanca.cursomc.services;

import com.dlanca.cursomc.domain.Order;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Order obj);

    void sendEmail(SimpleMailMessage msg);
}
