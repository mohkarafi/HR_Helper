package org.example.emplyeemanagment.Service;

import org.example.emplyeemanagment.dtos.LeaveRequestDto;
import org.example.emplyeemanagment.Responses.LeaveRequestResponse;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface LeaveRequestService {
    LeaveRequestResponse addLeaveRequest(LeaveRequestDto leaveRequestDto , Long id);
    List<LeaveRequestDto> getAllLeaveRequests();
    LeaveRequestDto getLeaveRequestById(Long id) throws ChangeSetPersister.NotFoundException;
    LeaveRequestDto updateLeaveRequest(Long id, LeaveRequestDto leaveRequestDto);
    void deleteLeaveRequest(Long id);
    LeaveRequestDto getLeaveRequestByEmployeeId(Long employeeId);
}
