package org.example.emplyeemanagment.Service.Implemenatation;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Entities.Employee;
import org.example.emplyeemanagment.Entities.LeaveRequest;
import org.example.emplyeemanagment.Enums.LeaveReason;
import org.example.emplyeemanagment.Enums.LeaveStatus;
import org.example.emplyeemanagment.Mappers.EmployeeMapper;
import org.example.emplyeemanagment.Mappers.LeaveRequestMapper;
import org.example.emplyeemanagment.Repository.EmployeeRepository;
import org.example.emplyeemanagment.Repository.LeaveRequestRepository;
import org.example.emplyeemanagment.Responses.StandardResponse;
import org.example.emplyeemanagment.Service.LeaveRequestService;
import org.example.emplyeemanagment.Service.EmployeeService;
import org.example.emplyeemanagment.Service.NotificationService;
import org.example.emplyeemanagment.dtos.EmailDetails;
import org.example.emplyeemanagment.dtos.EmployeeDto;
import org.example.emplyeemanagment.dtos.LeaveRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class LeaveRequestServiceImpl implements LeaveRequestService {
    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeRepository employeeRepository;
    private final NotificationService notificationService;
    @Override
    public StandardResponse addLeaveRequest(LeaveRequestDto leaveRequestDto, Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee with ID " + id + " not found"));
        LeaveRequest leaveRequest = LeaveRequestMapper.mapToLeaveRequest(leaveRequestDto);
        leaveRequest.setEmployee(employee);
        leaveRequestRepository.save(leaveRequest);
        return StandardResponse.builder()
                .code("200")
                .status("success")
                .data(LeaveRequestMapper.mapToLeaveRequestDto(leaveRequest))
                .build();
    }

    @Override
    public StandardResponse getAllLeaveRequests() {
        List<LeaveRequest> requests = leaveRequestRepository.findAll();
        List<LeaveRequestDto> requestDtos = requests.stream().map(LeaveRequestMapper::mapToLeaveRequestDto).collect(Collectors.toList());
        return StandardResponse.builder()
                .code("200")
                .status("success")
                .data(requestDtos)
                .build();
    }

    @Override
    public StandardResponse getLeaveRequestById(Long id) {
        Optional<LeaveRequest> request = leaveRequestRepository.findById(id);
        if (request.isEmpty()) {
            return StandardResponse.builder()
                    .code("404")
                    .status("LeaveRequest not found")
                    .build();
        }
        LeaveRequest leaveRequest = request.get();
        return StandardResponse.builder()
                .code("200")
                .status("success")
                .data(LeaveRequestMapper.mapToLeaveRequestDto(leaveRequest))
                .build();
    }

    @Override
    public StandardResponse updateLeaveRequest(Long id, LeaveRequestDto leaveRequestDto) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("LeaveRequest not found"));

        Employee employee = employeeRepository.findById(leaveRequest.getEmployee().getId()).orElseThrow(() -> new RuntimeException("Employee with ID " + id + " not found"));
        leaveRequest.setStatus(leaveRequestDto.getStatus());
        leaveRequest.setEmployee(employee);

        LeaveRequest leaveRequestUpdated = leaveRequestRepository.save(leaveRequest);

        if (leaveRequestUpdated.getStatus() == LeaveStatus.APPROVED &&
                (leaveRequestUpdated.getReason() == LeaveReason.Vacation ||
                        leaveRequestUpdated.getReason() == LeaveReason.Bereavement)) {

            EmailDetails emailDetails = EmailDetails.builder()
                    .ReciverEmail("simokrf02@gmail.com")
                    .EmailSubject("Leave Request Appproved")
                    .EmailBody("Good news! Your leave request from "
                            + leaveRequestUpdated.getStartDate() + " to "
                            + leaveRequestUpdated.getEndDate() + " has been approved.")
                    .build();

            notificationService.sendEmail(emailDetails);
        }

        return StandardResponse.builder()
                .code("200")
                .status("LeaveRequest updated successfully")
                .data(LeaveRequestMapper.mapToLeaveRequestDto(leaveRequestUpdated))
                .build();
    }


    @Override
    public StandardResponse deleteLeaveRequest(Long id) {
        Optional<LeaveRequest> request = leaveRequestRepository.findById(id);
        if (request.isEmpty()) {
            return StandardResponse.builder()
                    .code("404")
                    .status("LeaveRequest not found")
                    .build();
        }
        LeaveRequest leaveRequest = request.get();
        leaveRequestRepository.delete(leaveRequest);
        return StandardResponse.builder()
                .code("200")
                .status("LeaveRequest deleted successfully")
                .build();
    }

    @Override
    public StandardResponse findEmployeesCurrentlyOnLeave(String status) {
        List<Employee> employeesOnLeave = leaveRequestRepository.findEmployeesCurrentlyOnLeave(status);
        List<EmployeeDto> employeesOnLeaveDto = employeesOnLeave.stream().map(employee -> EmployeeMapper.toEmployeeDto(employee)).collect(Collectors.toList());
        return StandardResponse.builder()
                .code("200")
                .status("success")
                .data(employeesOnLeaveDto)
                .build();

    }


}
