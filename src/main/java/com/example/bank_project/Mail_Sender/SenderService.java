package com.example.bank_project.Mail_Sender;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SenderService {

    @Value("${spring.mail.username}")
    private String from;

    private final JavaMailSender mailSender;

    public SenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
}
