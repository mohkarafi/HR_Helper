package org.example.emplyeemanagment.Service.Implemenatation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Entities.Department;
import org.example.emplyeemanagment.Entities.Employee;
import org.example.emplyeemanagment.Entities.LeaveRequest;
import org.example.emplyeemanagment.Enums.LeaveStatus;
import org.example.emplyeemanagment.Mappers.EmployeeMapper;
import org.example.emplyeemanagment.Mappers.LeaveRequestMapper;
import org.example.emplyeemanagment.Repository.DepartmentRepository;
import org.example.emplyeemanagment.Repository.EmployeeRepository;
import org.example.emplyeemanagment.Repository.LeaveRequestRepository;
import org.example.emplyeemanagment.Responses.StandardResponse;
import org.example.emplyeemanagment.Service.NotificationService;
import org.example.emplyeemanagment.Service.EmployeeService;
import org.example.emplyeemanagment.dtos.EmailDetails;
import org.example.emplyeemanagment.dtos.EmployeeDto;
import org.example.emplyeemanagment.dtos.LeaveRequestDto;
import org.example.emplyeemanagment.utils.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final LeaveRequestRepository leaveRequestRepository;
    private final DepartmentRepository departmentRepository;
    private final SecurityUtils securityUtils;
    private final NotificationService notificationService;


    public StandardResponse SaveEmployee(EmployeeDto employeeDto) {
        if (employeeRepository.existsByEmail(employeeDto.getEmail())) {
            return StandardResponse.builder()
                    .code("500")
                    .status("Employee Already Exists")
                    .data(null)
                    .build();
        }
        try {
            Department department = departmentRepository.findById(employeeDto.getDepartment().getId()).orElseThrow(() -> new RuntimeException("Department not found"));
            Employee employee = EmployeeMapper.toEmployee(employeeDto);
            String token = UUID.randomUUID().toString();
            employee.setDepartment(department);
            employee.setIsVerified(false);
            employee.setAccountCreationToken(token);
            Employee savedEmployee = employeeRepository.save(employee);

            EmailDetails emailDetails = EmailDetails.builder().ReciverEmail(employeeDto.getEmail())
                    .EmailSubject("Welcome to the Team! " + employeeDto.getFirstName())
                    .EmailBody("Congratulations and welcome to our company!\n" +
                            "We’re happy to have you with us and excited about what we’ll accomplish together.")
                    .build();
            notificationService.sendEmail(emailDetails);
            return StandardResponse.builder()
                    .code("200")
                    .status("Employee Saved")
                    .data(EmployeeMapper.toEmployeeDto(savedEmployee))
                    .build();
        } catch (Exception e) {
            return StandardResponse.builder()
                    .code("500")
                    .status("error : " + e.getMessage())
                    .data(null)
                    .build();
        }

    }


    @Override
    public StandardResponse DeleteEmployee(Long id) throws AccountNotFoundException {
        leaveRequestRepository.deleteLeaveRequestByEmployee_Id(id);
        Employee employee = employeeRepository.findById(id).orElseThrow(()
                -> new AccountNotFoundException("Account Not Found"));
        employeeRepository.delete(employee);
        return StandardResponse.builder()
                .code("200")
                .status("Employee Deleted Successfully")
                .data(null)
                .build();
    }

    @Override
    public StandardResponse GetAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDto> employeesDtos = employees.stream().map(EmployeeMapper::toEmployeeDto).collect(Collectors.toList());
        return StandardResponse.builder()
                .code("200")
                .status("Employee Listed Successfully")
                .data(employeesDtos)
                .build();
    }

    @Override
    public StandardResponse findEmployeeByEmail(String email) throws AccountNotFoundException {
        Employee employee = employeeRepository.findEmployeeByEmail(email);
        if (employee == null) {
            throw new AccountNotFoundException("Employee with email " + email + " not found.");
        }
        return StandardResponse.builder()
                .code("002")
                .status("Success")
                .data(EmployeeMapper.toEmployeeDto(employee))
                .build();
    }

    @PreAuthorize("$(securityUtils.isOwner(#id))")
    @Override
    public StandardResponse updateEmployee(EmployeeDto employeeDto) {
        Employee Findemployee = employeeRepository.findEmployeeByEmail(employeeDto.getEmail());
        if (Findemployee == null) {
            return StandardResponse.builder()
                    .code("404")
                    .status("Employee not found")
                    .data(null)
                    .build();
        }
        Employee employee = EmployeeMapper.toEmployee(employeeDto);
        employeeRepository.save(employee);

        return StandardResponse.builder()
                .code("200")
                .status("Employee updated successfully")
                .data(EmployeeMapper.toEmployeeDto(employee))
                .build();
    }

    // @PreAuthorize("(@securityUtils.isOwner(#id))")
    @Override
    public StandardResponse findEmployeeById(Long id) throws AccountNotFoundException {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("Employee not found"));
        EmployeeDto employeeDto = EmployeeMapper.toEmployeeDto(employee);
        return StandardResponse.builder()
                .code("200")
                .status("Success")
                .data(employeeDto)
                .build();
    }

    @Override
    public StandardResponse addLeaveRequest(LeaveRequestDto leaveRequestDto, Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();
        boolean alreadyExists = leaveRequestRepository.existsByEmployeeIdAndStatusIn(employeeId, List.of(LeaveStatus.PENDING, LeaveStatus.APPROVED));
        System.out.println("Already has a leave request? " + alreadyExists);

        if (alreadyExists) {
            return StandardResponse.builder()
                    .code("400")
                    .status("The employee already has a pending or approved leave request.")
                    .build();
        }
        LeaveRequest leave = LeaveRequestMapper.mapToLeaveRequest(leaveRequestDto);
        leave.setEmployee(employee);
        leaveRequestRepository.save(leave);
        EmailDetails emailDetails = EmailDetails.builder().ReciverEmail(employee.getEmail()).
                EmailSubject("Leave Request Submitted Successfully – HR-HELPER")
                .EmailBody("Hello " + employee.getFirstName() + "\n\n  Your leave request has been submitted successfully on HR-HELPER.\n\nStatus: PENDING\nStart Date: " + leave.getStartDate() + " \nEnd Date: " + leave.getEndDate() + "\nType: " + leave.getReason() + "\n\nYou will be notified once it is reviewed by your manager.\n\nBest regards,\nHR-HELPER Team")
                .build();
        notificationService.sendEmail(emailDetails);
        return StandardResponse.builder()
                .code("200")
                .status("Leave request added successfully")
                .build();
    }


}

