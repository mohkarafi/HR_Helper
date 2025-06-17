package org.example.emplyeemanagment.dtos;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmailDetails {
    private String ReciverEmail;
    private String EmailBody;
    @Size(max = 255, message = "Email subject is too long")
    private String EmailSubject;
    private String attachment;
}