package org.example.emplyeemanagment.Mappers;

import org.example.emplyeemanagment.Entities.PaySlip;
import org.example.emplyeemanagment.dtos.PaySlipDto;

public class PaySlipMapper {
    public static PaySlipDto mapToPayrollDto(PaySlip paySlip) {
        PaySlipDto paySlipDto = PaySlipDto.builder()
                .id(paySlip.getId())
                .payrollDate(paySlip.getPayrollDate())
                .baseSalary(paySlip.getBaseSalary())
                .bonus(paySlip.getBonus())
                .deductions(paySlip.getDeductions())
                .status(paySlip.getStatus())
                .employeeId(paySlip.getEmployee() != null ? paySlip.getEmployee().getId() : null)
                .build();
     return paySlipDto;
    }
    public  static PaySlip mapToPayroll(PaySlipDto paySlipDto) {
        PaySlip paySlip = PaySlip.builder()
                .payrollDate(paySlipDto.getPayrollDate())
                .baseSalary(paySlipDto.getBaseSalary())
                .bonus(paySlipDto.getBonus())
                .deductions(paySlipDto.getDeductions())
                .status(paySlipDto.getStatus())
                .build();
        return paySlip;
    }
}
