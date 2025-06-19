package org.example.emplyeemanagment.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Responses.StandardResponse;
import org.example.emplyeemanagment.Service.PaySlipService;
import org.example.emplyeemanagment.dtos.PaySlipDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Payroll")
@AllArgsConstructor
public class PaySlipController {
    private final PaySlipService paySlipService;

    @PostMapping("Add")
    public StandardResponse AddPayroll(@Valid @RequestBody PaySlipDto paySlipDto) {
        return paySlipService.AddPayroll(paySlipDto);
    }

    @PutMapping("update/{id}")
    public StandardResponse updatePayroll(@Valid @PathVariable Long id, @RequestBody PaySlipDto paySlipDto) {
        return paySlipService.UpdatePayroll(id, paySlipDto);
    }

    @DeleteMapping("delete/{id}")
    public StandardResponse deletePayroll(@Valid @PathVariable Long id) {
        return paySlipService.DeletePayroll(id);
    }

    @GetMapping("findByEmployee/{employeeID}")
    public StandardResponse findByEmployee(@PathVariable Long employeeID) {
        return paySlipService.FindPayrollByEmployeeId(employeeID);
    }
}
