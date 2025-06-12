package org.example.emplyeemanagment.Service;

import org.example.emplyeemanagment.dtos.EmailDetails;

public interface EmailService {
    void sendEmail(EmailDetails emailDetails , String token);
}
