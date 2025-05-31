package org.example.emplyeemanagment.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SignupRequest {
    @NotNull(message = "username is required ")
    private String username;
    @NotNull(message = "password is required ")
    private String password;
    @NotNull(message = "employee id cannot be null")
    private Long employeeId;

    private String role = "USER";
}
