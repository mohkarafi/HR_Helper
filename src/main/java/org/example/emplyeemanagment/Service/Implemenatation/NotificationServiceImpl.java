package org.example.emplyeemanagment.Service.Implemenatation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.emplyeemanagment.Service.NotificationService;
import org.example.emplyeemanagment.dtos.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;
    @Value("${spring-origin-link}")
    private String ORIGIN;



    @Override
    public void sendEmail(EmailDetails emailDetails ) {
        try {
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
