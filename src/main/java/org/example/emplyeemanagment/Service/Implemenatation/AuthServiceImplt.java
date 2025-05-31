package org.example.emplyeemanagment.Service.Implemenatation;

import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Configuration.JwtHelper;
import org.example.emplyeemanagment.Entities.Employee;
import org.example.emplyeemanagment.Entities.UserAccount;
import org.example.emplyeemanagment.Repository.EmployeeRepository;
import org.example.emplyeemanagment.Repository.UserAccountRepository;
import org.example.emplyeemanagment.Responses.AuthResponse;
import org.example.emplyeemanagment.Service.AuthService;
import org.example.emplyeemanagment.dtos.LoginRequest;
import org.example.emplyeemanagment.dtos.SignupRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthServiceImplt implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserAccountRepository userAccountRepository;
    private final EmployeeRepository employeeRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtHelper jwtHelper;

    @Override
    public AuthResponse signupUser(SignupRequest signupRequest) {
        Employee employee = employeeRepository.findById(signupRequest.getEmployeeId()).orElseThrow(() -> new UsernameNotFoundException("Employee Not Found"));
        System.out.println(employee);
        UserAccount userAccount = UserAccount.builder()
                .username(signupRequest.getUsername())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .role("USER")
                .employee(employee)
                .build();
        userAccountRepository.save(userAccount);
        return AuthResponse.builder()
                .responseCode("001")
                .responseMessage(userAccount.getUsername()+ "is successfully registered")
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (loginRequest.getUsername(), loginRequest.getPassword()));
        UserAccount userAccount = userAccountRepository.findByUsername(loginRequest.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
         Map<String , Object> claims = new HashMap<>();
         claims.put("userId" , userAccount.getId());
        String token = jwtHelper.generateToken(claims , userAccount);
        return AuthResponse.builder()
                .responseCode("001")
                .responseMessage("User successfully logged with the token id => " +token  )
                .build();
    }

}
