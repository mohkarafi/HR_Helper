package org.example.emplyeemanagment.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeInfo {
    private String firstName;
    private String lastName;
    private String email;
    private String position;
    private String phoneNumber;
    private LocalDate hireDate;
    private String department;
}
