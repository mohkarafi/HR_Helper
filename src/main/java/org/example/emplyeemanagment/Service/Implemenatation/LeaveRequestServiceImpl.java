package org.example.emplyeemanagment.Service.Implemenatation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Entities.Employee;
import org.example.emplyeemanagment.Entities.LeaveRequest;
import org.example.emplyeemanagment.Mappers.EmployeeMapper;
import org.example.emplyeemanagment.Mappers.LeaveRequestMapper;
import org.example.emplyeemanagment.Repository.EmployeeRepository;
import org.example.emplyeemanagment.Repository.LeaveRequestRepository;
import org.example.emplyeemanagment.Responses.StandardResponse;
import org.example.emplyeemanagment.Service.LeaveRequestService;
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
        Optional<LeaveRequest> request = leaveRequestRepository.findById(id);
        Employee employee = employeeRepository.findById(leaveRequestDto.getEmployeeId()).orElseThrow(() -> new RuntimeException("Employee with ID " + id + " not found"));
        if (request.isEmpty()) {
            return StandardResponse.builder()
                    .code("404")
                    .status("LeaveRequest not found")
                    .build();
        }
        LeaveRequest leaveRequest = request.get();
        leaveRequest.setId(leaveRequestDto.getId());
        leaveRequest.setStartDate(leaveRequestDto.getStartDate());
        leaveRequest.setEndDate(leaveRequestDto.getEndDate());
        leaveRequest.setStatus(leaveRequestDto.getStatus());
        leaveRequest.setReason(leaveRequestDto.getReason());
        leaveRequest.setEmployee(employee);
        LeaveRequest leaveRequestSaved = leaveRequestRepository.save(leaveRequest);
        return StandardResponse.builder()
                .code("200")
                .status("LeaveRequest updated Successfully")
                .data(LeaveRequestMapper.mapToLeaveRequestDto(leaveRequestSaved))
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
