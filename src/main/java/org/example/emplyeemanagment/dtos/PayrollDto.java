package org.example.emplyeemanagment.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.emplyeemanagment.Enums.payrollStatus;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PayrollDto {
    private Long id;
    @NotNull(message = "payroll date is required")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date payrollDate ;
    @NotNull(message = "base Salary is required")
    private Double baseSalary;
    @NotNull(message = "bonus is required")
    private Double bonus;
    @NotNull(message = "deductions Salary is required")
    private Double deductions;
    private payrollStatus status;
    private Long employeeId;
}
