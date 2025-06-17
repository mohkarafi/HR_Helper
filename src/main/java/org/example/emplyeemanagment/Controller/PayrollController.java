package org.example.emplyeemanagment.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Responses.StandardResponse;
import org.example.emplyeemanagment.Service.PayrollService;
import org.example.emplyeemanagment.dtos.PayrollDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Payroll")
@AllArgsConstructor
public class PayrollController {
    private final PayrollService payrollService;

    @PostMapping("Add")
    public StandardResponse AddPayroll(@Valid @RequestBody PayrollDto payrollDto) {
        return payrollService.AddPayroll(payrollDto);
    }

    @PutMapping("update/{id}")
    public StandardResponse updatePayroll(@Valid @PathVariable Long id, @RequestBody PayrollDto payrollDto) {
        return payrollService.UpdatePayroll(id, payrollDto);
    }

    @DeleteMapping("delete/{id}")
    public StandardResponse deletePayroll(@Valid @PathVariable Long id) {
        return payrollService.DeletePayroll(id);
    }

    @GetMapping("findByEmployee/{employeeID}")
    public StandardResponse findByEmployee(@PathVariable Long employeeID) {
        return payrollService.FindPayrollByEmployeeId(employeeID);
    }
}
