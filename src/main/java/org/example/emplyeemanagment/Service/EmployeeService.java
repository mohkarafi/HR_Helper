package org.example.emplyeemanagment.Service;

import org.example.emplyeemanagment.dtos.EmployeeDto;
import org.example.emplyeemanagment.Responses.EmployeeResponse;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

public interface EmployeeService {
    EmployeeResponse SaveEmployee(EmployeeDto employee);
    EmployeeResponse DeleteEmployee(Long id) throws AccountNotFoundException;
    List<EmployeeDto > GetAllEmployees();
    EmployeeResponse findEmployeeByEmail(String email) throws AccountNotFoundException;
}
