package org.example.emplyeemanagment.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Responses.StandardResponse;
import org.example.emplyeemanagment.Service.PaySlipService;
import org.example.emplyeemanagment.dtos.PaySlipDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("PaySlip")
@AllArgsConstructor

@Tag(name = "PaySlip", description = "PaySlip management endpoints")

public class PaySlipController {
    private final PaySlipService paySlipService;

    @Operation(summary = "Create a new pay slip")
    @PostMapping("Add")
    public StandardResponse AddPayroll(@Valid @RequestBody PaySlipDto paySlipDto) {
        return paySlipService.AddPayroll(paySlipDto);
    }

    @Operation(summary = "Update a pay slip by ID")
    @PutMapping("update/{id}")
    public StandardResponse updatePayroll(@Valid @PathVariable Long id, @RequestBody PaySlipDto paySlipDto) {
        return paySlipService.UpdatePayroll(id, paySlipDto);
    }

    @Operation(summary = "Delete a pay slip by ID")
    @DeleteMapping("delete/{id}")
    public StandardResponse deletePayroll(@Valid @PathVariable Long id) {
        return paySlipService.DeletePayroll(id);
    }

    @Operation(summary = "Find pay slips by employee ID")
    @GetMapping("findByEmployee/{employeeID}")
    public StandardResponse findByEmployee(@PathVariable Long employeeID) {
        return paySlipService.FindPayrollByEmployeeId(employeeID);
    }
}