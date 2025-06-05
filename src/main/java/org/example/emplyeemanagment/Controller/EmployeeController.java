package org.example.emplyeemanagment.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Service.EmployeeService;
import org.example.emplyeemanagment.Service.LeaveRequestService;
import org.example.emplyeemanagment.dtos.EmployeeDto;
import org.example.emplyeemanagment.Responses.EmployeeResponse;
import org.example.emplyeemanagment.dtos.LeaveRequestDto;
import org.example.emplyeemanagment.Responses.LeaveRequestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("Employee")
public class EmployeeController {
    private EmployeeService employeeService;
    private LeaveRequestService leaveRequestService;

    @PostMapping(path = "save")
    public EmployeeResponse saveEmployee(@Valid @RequestBody EmployeeDto employee) {
        return employeeService.SaveEmployee(employee);
    }

    @GetMapping("employees")
    public List<EmployeeDto> getAllEmployee() {
        return employeeService.GetAllEmployees();
    }
    @GetMapping("findOne/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) throws AccountNotFoundException {
        EmployeeResponse response = employeeService.findEmployeeById(id);
        return ResponseEntity.ok(response);

    }

    @GetMapping("employeeByEmail")
    public EmployeeResponse getEmployeeByEmail(@Valid @RequestParam String email) throws AccountNotFoundException {
        return employeeService.findEmployeeByEmail(email);
    }

    @PostMapping("AddLeave/{id}")
    public LeaveRequestResponse AddLeaveRequest(@RequestBody LeaveRequestDto leaveRequestDto, @PathVariable Long id) {
        return leaveRequestService.addLeaveRequest(leaveRequestDto, id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<EmployeeResponse> DeleteEmployee(@PathVariable Long id) throws AccountNotFoundException {
     EmployeeResponse response = employeeService.DeleteEmployee(id);
     return ResponseEntity.ok(response);
    }
    @PutMapping("update")
    public EmployeeResponse updateEmployee(@Valid @RequestBody EmployeeDto employee) {
        return employeeService.updateEmployee(employee);
    }
}
