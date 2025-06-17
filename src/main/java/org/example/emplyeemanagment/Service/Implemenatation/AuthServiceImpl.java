package org.example.emplyeemanagment.Service.Implemenatation;

import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Configuration.JwtHelper;
import org.example.emplyeemanagment.Entities.Employee;
import org.example.emplyeemanagment.Entities.UserAccount;
import org.example.emplyeemanagment.Repository.EmployeeRepository;
import org.example.emplyeemanagment.Repository.UserAccountRepository;
import org.example.emplyeemanagment.Responses.AuthResponse;
import org.example.emplyeemanagment.Service.AuthService;
import org.example.emplyeemanagment.Service.EmailService;
import org.example.emplyeemanagment.Service.NotificationService;
import org.example.emplyeemanagment.dtos.EmailDetails;
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
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserAccountRepository userAccountRepository;
    private final EmployeeRepository employeeRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtHelper jwtHelper;
    private final EmailService emailServie;
    private final NotificationService employeeService;

    @Override
    public AuthResponse signupUser(SignupRequest signupRequest) {
        Employee employee = employeeRepository.findById(signupRequest.getEmployeeId()).orElseThrow(() -> new UsernameNotFoundException("Employee Not Found"));
        UserAccount userAccount = UserAccount.builder()
                .username(signupRequest.getUsername())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .role(signupRequest.getRole())
                .employeeID(employee)
                .build();
        userAccountRepository.save(userAccount);


        EmailDetails emailDetails = EmailDetails.builder()
                .ReciverEmail(employee.getEmail())
                .EmailSubject(" Your HR-HELPER account has been successfully created")
                .EmailBody("Hello "+ employee.getFirstName()+ " and welcome to HR-HELPER!\n\nWe're excited to have you on board. Your account has been successfully created.\nFeel free to explore the features and start managing your HR tasks with ease.\n\nIf you have any questions, we're here to help!\n\nCheers,\nThe HR-HELPER Team.")
                .build();
        // String token = UUID.randomUUID().toString();
        emailServie.sendEmail(emailDetails);
        return AuthResponse.builder()
                .responseCode("200")
                .responseMessage(userAccount.getUsername() + "is successfully registered")
                .build();
    }

    @Override
    public AuthResponse loginUser(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        UserAccount userAccount = userAccountRepository.findByUsername(loginRequest.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Map<String, Object> Claims = new HashMap<>();
        Claims.put("id", userAccount.getId());
        String token = jwtHelper.generateToken(Claims, userAccount);
        return AuthResponse.builder()
                .responseCode("200")
                .responseMessage(userAccount.getUsername() + "is successfully logged in with the token " + token)
                .build();
    }


}
