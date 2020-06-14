package com.dlanca.cursomc.services;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public class MockMailService extends AbstractEmailService {

    private static final Logger LOG = LoggerFactory.getLogger(MockMailService.class);

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        LOG.info("SIMULANDO EMAIL");
        LOG.info(msg.toString());
        LOG.info("EMAIL ENVIADO");
    }

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        LOG.info("SIMULANDO HTML EMAIL");
        LOG.info(msg.toString());
        LOG.info("EMAIL ENVIADO");
    }

}
