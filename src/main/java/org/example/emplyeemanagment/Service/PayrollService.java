package org.example.emplyeemanagment.Service;

import org.example.emplyeemanagment.Responses.StandardResponse;
import org.example.emplyeemanagment.dtos.PayrollDto;
import org.springframework.stereotype.Service;

@Service
public interface PayrollService {
    StandardResponse AddPayroll(PayrollDto payrollDto);
    StandardResponse UpdatePayroll(Long id , PayrollDto payrollDto);
    StandardResponse DeletePayroll(Long id);
    StandardResponse FindPayrollByEmployeeId(Long employeeId);
}
