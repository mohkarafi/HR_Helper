package org.example.emplyeemanagment.Service;

import org.example.emplyeemanagment.Responses.StandardResponse;
import org.example.emplyeemanagment.dtos.PaySlipDto;
import org.springframework.stereotype.Service;

@Service
public interface PaySlipService {
    StandardResponse AddPayroll(PaySlipDto paySlipDto);
    StandardResponse UpdatePayroll(Long id , PaySlipDto paySlipDto);
    StandardResponse DeletePayroll(Long id);
    StandardResponse FindPayrollByEmployeeId(Long employeeId);
}
