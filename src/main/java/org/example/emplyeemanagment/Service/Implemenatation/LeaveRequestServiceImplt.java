package org.example.emplyeemanagment.Service.Implemenatation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Entities.Employee;
import org.example.emplyeemanagment.Entities.LeaveRequest;
import org.example.emplyeemanagment.Mappers.LeaveRequestMapper;
import org.example.emplyeemanagment.Repository.EmployeeRepository;
import org.example.emplyeemanagment.Repository.LeaveRequestRepository;
import org.example.emplyeemanagment.Service.LeaveRequestService;
import org.example.emplyeemanagment.dtos.LeaveRequestDto;
import org.example.emplyeemanagment.Responses.LeaveRequestResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class LeaveRequestServiceImplt implements LeaveRequestService {
    private LeaveRequestRepository leaveRequestRepository;
    private EmployeeRepository employeeRepository;

    @Override
    public LeaveRequestResponse addLeaveRequest(LeaveRequestDto leaveRequestDto, Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee with ID " + id + " not found"));
        LeaveRequest leaveRequest = LeaveRequestMapper.mapToLeaveRequest(leaveRequestDto);
        leaveRequest.setEmployee(employee);
        System.out.println(leaveRequest);
        leaveRequestRepository.save(leaveRequest);
        return LeaveRequestResponse.builder()
                .messageCode("001")
                .message("succes")
                .leaveRequest(LeaveRequestMapper.mapToLeaveRequestDto(leaveRequest))
                .build();
    }

    @Override
    public List<LeaveRequestDto> getAllLeaveRequests() {
        List<LeaveRequest> requests = leaveRequestRepository.findAll();
        List<LeaveRequestDto> requestDtos = requests.stream().map(LeaveRequestMapper::mapToLeaveRequestDto).collect(Collectors.toList());
        return requestDtos;
    }

    @Override
    public LeaveRequestDto getLeaveRequestById(Long id) {
        LeaveRequest request = leaveRequestRepository.findById(id).orElseThrow(() -> new RuntimeException());
        return LeaveRequestMapper.mapToLeaveRequestDto(request);
    }

    @Override
    public LeaveRequestDto updateLeaveRequest(Long id, LeaveRequestDto leaveRequestDto) {
        return null;
    }

    @Override
    public void deleteLeaveRequest(Long id) {
        LeaveRequest request = leaveRequestRepository.findById(id).orElseThrow(() -> new RuntimeException());
        leaveRequestRepository.delete(request);
    }

    @Override
    public LeaveRequestDto getLeaveRequestByEmployeeId(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException());
        LeaveRequest request = leaveRequestRepository.findLeaveRequestByEmployee_Id(employee.getId());
        return LeaveRequestMapper.mapToLeaveRequestDto(request);
    }


}
