package com.dlanca.cursomc.services;

import com.dlanca.cursomc.domain.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendOrderConfirmationEmail(Order obj){
        SimpleMailMessage sm = prepareSimpleMailMessageFromOrder(obj);
        sendEmail(sm);
    }

    private SimpleMailMessage prepareSimpleMailMessageFromOrder(Order obj) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(obj.getCustomer().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Pedido confirmado! Codigo: " + obj.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(obj.toString());
        return sm;
    }
}
