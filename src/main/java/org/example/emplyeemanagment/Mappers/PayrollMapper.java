package org.example.emplyeemanagment.Mappers;

import org.example.emplyeemanagment.Entities.Payroll;
import org.example.emplyeemanagment.dtos.PayrollDto;

public class PayrollMapper {
    public static PayrollDto mapToPayrollDto(Payroll payroll) {
        PayrollDto payrollDto = PayrollDto.builder()
                .id(payroll.getId())
                .payrollDate(payroll.getPayrollDate())
                .baseSalary(payroll.getBaseSalary())
                .bonus(payroll.getBonus())
                .deductions(payroll.getDeductions())
                .status(payroll.getStatus())
                .employeeId(payroll.getEmployee() != null ? payroll.getEmployee().getId() : null)
                .build();
     return payrollDto;
    }
    public  static Payroll mapToPayroll(PayrollDto payrollDto) {
        Payroll payroll = Payroll.builder()
                .payrollDate(payrollDto.getPayrollDate())
                .baseSalary(payrollDto.getBaseSalary())
                .bonus(payrollDto.getBonus())
                .deductions(payrollDto.getDeductions())
                .status(payrollDto.getStatus())
                .build();
        return payroll;
    }
}
