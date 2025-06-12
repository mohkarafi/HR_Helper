package org.example.emplyeemanagment.Service.Implemenatation;

import lombok.extern.slf4j.Slf4j;
import org.example.emplyeemanagment.Service.EmailService;
import org.example.emplyeemanagment.dtos.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service

@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;
    @Value("${spring-origin-link}")
    private String ORIGIN;



    @Override
    public void sendEmail(EmailDetails emailDetails , String token) {
        try {
            String link = ORIGIN + "auth/signup?token=" + token;
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(senderEmail);
            message.setTo(emailDetails.getReciverEmail());
            message.setSubject(emailDetails.getEmailSubject());
            message.setText(emailDetails.getEmailBody());
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
