package org.example.emplyeemanagment.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Responses.AuthResponse;
import org.example.emplyeemanagment.Service.AuthService;
import org.example.emplyeemanagment.dtos.LoginRequest;
import org.example.emplyeemanagment.dtos.SignupRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class AuthController {
private AuthService authService;
    @PostMapping("signup")
    public AuthResponse signupUser(@Valid @RequestBody SignupRequest signupRequest) {
        return authService.signupUser(signupRequest);
    }
    @PostMapping("login")
    public AuthResponse login(@Valid @RequestBody LoginRequest loginRequest) {
       return authService.loginUser(loginRequest);
    }
  @PostMapping("forget")
    public void resetPassword(String username){
        authService.resetPassword(username);
  }
}
