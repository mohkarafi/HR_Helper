package org.example.emplyeemanagment.Service.Implemenatation;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.emplyeemanagment.Configuration.JwtHelper;
import org.example.emplyeemanagment.Entities.Employee;
import org.example.emplyeemanagment.Entities.PasswordResetToken;
import org.example.emplyeemanagment.Entities.UserAccount;
import org.example.emplyeemanagment.Repository.EmployeeRepository;
import org.example.emplyeemanagment.Repository.PasswordResetRepository;
import org.example.emplyeemanagment.Repository.UserAccountRepository;
import org.example.emplyeemanagment.Responses.AuthResponse;
import org.example.emplyeemanagment.Service.AuthService;
import org.example.emplyeemanagment.Service.EmployeeService;
import org.example.emplyeemanagment.Service.NotificationService;
import org.example.emplyeemanagment.dtos.EmailDetails;
import org.example.emplyeemanagment.dtos.LoginRequest;
import org.example.emplyeemanagment.dtos.SignupRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserAccountRepository userAccountRepository;
    private final EmployeeRepository employeeRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtHelper jwtHelper;
    private final NotificationService emailServie;
    private final EmployeeService employeeService;
    private final PasswordResetRepository passwordResetRepository;
    private final  NotificationService notificationService;

    @Override
    public AuthResponse signupUser(SignupRequest signupRequest) {
        if (employeeRepository.existsById(signupRequest.getEmployeeId())) {
            return AuthResponse.builder()
                    .responseCode("500")
                    .responseMessage("This employee already has a user account")
                    .build();
        }
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
                .EmailBody("Hello " + employee.getFirstName() + " and welcome to HR-HELPER!\n\nWe're excited to have you on board. Your account has been successfully created.\nFeel free to explore the features and start managing your HR tasks with ease.\n\nIf you have any questions, we're here to help!\n\nCheers,\nThe HR-HELPER Team.")
                .build();
        emailServie.sendEmail(emailDetails);
        return AuthResponse.builder()
                .responseCode("200")
                .responseMessage(userAccount.getUsername() + " is successfully registered")
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


    public void resetPassword(String username) {
        Optional<UserAccount> user = userAccountRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        String token = UUID.randomUUID().toString();
        LocalDateTime expiryDate = LocalDateTime.now().plusMinutes(15);

        PasswordResetToken passwordResetToken = PasswordResetToken.builder()
                .token(token)
                .expiryDate(expiryDate)
                .userId(user.get())
                .build();

        passwordResetRepository.save(passwordResetToken);

        EmailDetails emailDetails = EmailDetails.builder()
                .ReciverEmail(user.get().getEmployeeID().getEmail())
                .EmailSubject("Reset Password")
                .EmailBody("Click the link below to reset your password:\nhttp://localhost:8081/auth/forget?token=" + token)
                .build();

        notificationService.sendEmail(emailDetails);
    }
}


