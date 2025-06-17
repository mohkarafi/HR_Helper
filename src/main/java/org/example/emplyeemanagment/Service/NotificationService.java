package org.example.emplyeemanagment.Service;

import org.example.emplyeemanagment.dtos.EmailDetails;

public interface NotificationService {
    void sendEmail(EmailDetails emailDetails );
}
