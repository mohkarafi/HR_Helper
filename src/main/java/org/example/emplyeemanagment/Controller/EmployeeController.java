package org.example.emplyeemanagment.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Responses.StandardResponse;
import org.example.emplyeemanagment.Service.EmployeeService;
import org.example.emplyeemanagment.Service.LeaveRequestService;
import org.example.emplyeemanagment.dtos.EmployeeDto;
import org.example.emplyeemanagment.dtos.LeaveRequestDto;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;

@RestController
@AllArgsConstructor
@RequestMapping("Employee")
@Tag(name = "Employee", description = "Employee management endpoints")
public class EmployeeController {
    private EmployeeService employeeService;
    private LeaveRequestService leaveRequestService;
    @Operation(summary = "Add new employee")
    @PostMapping(path = "save")
    public StandardResponse saveEmployee(@Valid @RequestBody EmployeeDto employee) {
        return employeeService.SaveEmployee(employee);
    }


    @Operation(summary = "Get all employees")
    @GetMapping("employees")
    public StandardResponse getAllEmployee( @RequestParam(name = "page", defaultValue = "0")int page ,
                                            @RequestParam(name = "size", defaultValue = "5")int size  ) {
        return employeeService.GetAllEmployees(page,size);
    }


    @Operation(summary = "Find Employee By Id")
    @GetMapping("findOne/{id}")
    public StandardResponse getEmployeeById(@PathVariable Long id) throws AccountNotFoundException {
        return employeeService.findEmployeeById(id);
    }


    @Operation(summary = "Find Employee By Email")
    @GetMapping("employeeByEmail")
    public StandardResponse getEmployeeByEmail(@Valid @RequestParam String email) throws AccountNotFoundException {
        return employeeService.findEmployeeByEmail(email);
    }

    @Operation(summary = "Add leaveRequest")
    @PostMapping("AddLeave/{id}")
    public StandardResponse AddLeaveRequest(@Valid @RequestBody LeaveRequestDto leaveRequestDto  ,@PathVariable Long id) {
        return employeeService.addLeaveRequest(leaveRequestDto , id);
    }
    @Operation(summary = "Delete Employee By ID")
    @DeleteMapping("delete/{id}")
    public StandardResponse DeleteEmployee(@PathVariable Long id) throws AccountNotFoundException {
        return employeeService.DeleteEmployee(id);
    }

    @Operation(summary = "Update Employee")
    @PutMapping("update")
    public StandardResponse updateEmployee(@Valid @RequestBody EmployeeDto employee) {
        return employeeService.updateEmployee(employee);
    }
}
