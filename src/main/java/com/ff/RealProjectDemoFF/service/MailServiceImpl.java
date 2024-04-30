package com.ff.RealProjectDemoFF.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public String sendActivationEmail (String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("debashsiganguly321@gmail.com"); // sender email
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);


        mailSender.send(message);

        return "Activation mail sent";

    }

    @Override
    public String getToken(){
        String token = UUID.randomUUID().toString()+UUID.randomUUID().toString();
        return token;
    }

    @Override
    public String sendUpdateEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("debashsiganguly321@gmail.com"); // sender email
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);


        mailSender.send(message);

        return "Privilege update mail sent";
    }
}
