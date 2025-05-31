package org.example.emplyeemanagment.Service.Implemenatation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Entities.Department;
import org.example.emplyeemanagment.Entities.Employee;
import org.example.emplyeemanagment.Mappers.EmployeeMapper;
import org.example.emplyeemanagment.Repository.DepartmentRepository;
import org.example.emplyeemanagment.Repository.EmployeeRepository;
import org.example.emplyeemanagment.Repository.LeaveRequestRepository;
import org.example.emplyeemanagment.Service.EmployeeService;
import org.example.emplyeemanagment.dtos.EmployeeDto;
import org.example.emplyeemanagment.Responses.EmployeeResponse;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Transactional
public class EmployeeServiceImplt implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private LeaveRequestRepository leaveRequestRepository;
    private DepartmentRepository departmentRepository;

    public EmployeeResponse SaveEmployee(EmployeeDto employeeDto) {
        if (employeeRepository.existsByEmail(employeeDto.getEmail())) {
            EmployeeResponse employee = new EmployeeResponse("001", "Account Already Exist", null);
            return employee;
        }
        Department department = departmentRepository.findById(employeeDto.getDepartment().getId()).orElseThrow(() -> new RuntimeException("Department not found"));
        Employee employee = EmployeeMapper.toEmployee(employeeDto);
        employee.setDepartment(department);
        employeeRepository.save(employee);
        EmployeeResponse employeeResponse = EmployeeResponse.builder()
                .messageCode("001")
                .ResponseMessage("Success")
                .data(EmployeeMapper.toEmployeeDto(employee))
                .build();
        return employeeResponse;
    }


    @Override
    public EmployeeResponse DeleteEmployee(Long id) throws AccountNotFoundException {
        leaveRequestRepository.deleteLeaveRequestByEmployee_Id(id);
        Employee employee = employeeRepository.findById(id).orElseThrow(()
                -> new AccountNotFoundException("Account Not Found"));
        employeeRepository.delete(employee);
        return EmployeeResponse.builder()
                .messageCode("002")
                .ResponseMessage("Employee Deleted Successfully")
                .data(null)
                .build();
    }

    @Override
    public List<EmployeeDto> GetAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDto> employeesDtos = employees.stream().map(EmployeeMapper::toEmployeeDto).collect(Collectors.toList());
        return employeesDtos;
    }

    @Override
    public EmployeeResponse findEmployeeByEmail(String email) throws AccountNotFoundException {
        Employee employee = employeeRepository.findEmployeeByEmail(email);
        if (employee == null) {
            throw new AccountNotFoundException("Employee with email " + email + " not found.");
        }
        return EmployeeResponse.builder()
                .messageCode("002")
                .ResponseMessage("Success")
                .data(EmployeeMapper.toEmployeeDto(employee))
                .build();
    }

}

