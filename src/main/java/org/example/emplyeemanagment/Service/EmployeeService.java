package org.example.emplyeemanagment.Service;

import org.example.emplyeemanagment.Responses.StandardResponse;
import org.example.emplyeemanagment.dtos.EmployeeDto;
import org.example.emplyeemanagment.dtos.LeaveRequestDto;

import javax.security.auth.login.AccountNotFoundException;

public interface EmployeeService {
    StandardResponse SaveEmployee(EmployeeDto employee);
    StandardResponse DeleteEmployee(Long id) throws AccountNotFoundException;
    StandardResponse GetAllEmployees();
    StandardResponse findEmployeeByEmail(String email) throws AccountNotFoundException;
    StandardResponse updateEmployee(EmployeeDto employeeDto);
    StandardResponse findEmployeeById(Long id) throws AccountNotFoundException;
    StandardResponse addLeaveRequest(LeaveRequestDto leaveRequestDto , Long id);
}
