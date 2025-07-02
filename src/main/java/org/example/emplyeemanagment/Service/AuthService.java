package org.example.emplyeemanagment.Service;

import jakarta.validation.Valid;
import org.example.emplyeemanagment.Responses.AuthResponse;
import org.example.emplyeemanagment.dtos.LoginRequest;
import org.example.emplyeemanagment.dtos.SignupRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    AuthResponse signupUser(@Valid SignupRequest signupRequest);
    AuthResponse loginUser(@Valid LoginRequest loginRequest);
    void resetPassword(@Valid String username);
}
