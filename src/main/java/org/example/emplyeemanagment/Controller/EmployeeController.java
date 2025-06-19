package org.example.emplyeemanagment.Controller;

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
public class EmployeeController {
    private EmployeeService employeeService;
    private LeaveRequestService leaveRequestService;

    @PostMapping(path = "save")
    public StandardResponse saveEmployee(@Valid @RequestBody EmployeeDto employee) {
        return employeeService.SaveEmployee(employee);
    }

    @GetMapping("employees")
    public StandardResponse getAllEmployee( @RequestParam(name = "page", defaultValue = "0")int page ,
                                            @RequestParam(name = "size", defaultValue = "5")int size  ) {
        return employeeService.GetAllEmployees(page,size);
    }

    @GetMapping("findOne/{id}")
    public StandardResponse getEmployeeById(@PathVariable Long id) throws AccountNotFoundException {
        return employeeService.findEmployeeById(id);

    }

    @GetMapping("employeeByEmail")
    public StandardResponse getEmployeeByEmail(@Valid @RequestParam String email) throws AccountNotFoundException {
        return employeeService.findEmployeeByEmail(email);
    }

    @PostMapping("AddLeave/{id}")
    public StandardResponse AddLeaveRequest(@Valid @RequestBody LeaveRequestDto leaveRequestDto  ,@PathVariable Long id) {
        return employeeService.addLeaveRequest(leaveRequestDto , id);
    }

    @DeleteMapping("delete/{id}")
    public StandardResponse DeleteEmployee(@PathVariable Long id) throws AccountNotFoundException {
        return employeeService.DeleteEmployee(id);
    }

    @PutMapping("update")
    public StandardResponse updateEmployee(@Valid @RequestBody EmployeeDto employee) {
        return employeeService.updateEmployee(employee);
    }
}
