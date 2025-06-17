package org.example.emplyeemanagment.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginRequest {
    @NotNull(message = "Username is required ")
    private String username;
    @NotNull(message = "password is required ")
    private String password;
}
