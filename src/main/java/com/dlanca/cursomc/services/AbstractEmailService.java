package com.dlanca.cursomc.services;

import com.dlanca.cursomc.domain.Customer;
import com.dlanca.cursomc.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.Template;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

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

    private String htmlFromTemplateOrder(Order obj){
        Context context = new Context();
        context.setVariable("pedido", obj);
        return templateEngine.process("email/orderConfirmation", context);
    }

    @Override
    public void sendOrderConfirmationHtmlEmail(Order obj) {
        try {
            MimeMessage mm = prepareMimeMailMessageFromOrder(obj);
            sendHtmlEmail(mm);
        }catch (MessagingException e){
            sendOrderConfirmationEmail(obj);
        }

    }

    protected MimeMessage prepareMimeMailMessageFromOrder(Order obj) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
        mmh.setTo(obj.getCustomer().getEmail());
        mmh.setFrom(sender);
        mmh.setSubject("Confimation Order! Code: " + obj.getId());
        mmh.setSentDate(new Date(System.currentTimeMillis()));
        mmh.setText(htmlFromTemplateOrder(obj), true);
        return mimeMessage;
    }

    @Override
    public void sendNewPasswordEmail(Customer customer, String newPass){
        SimpleMailMessage sm = prepareNewPasswordEmail(customer, newPass);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareNewPasswordEmail(Customer customer, String newPass){
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(customer.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Noca senha solicitada");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Nova Senha: " + newPass);
        return sm;
    }
}
