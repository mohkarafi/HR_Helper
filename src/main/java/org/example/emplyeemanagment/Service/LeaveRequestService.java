package org.example.emplyeemanagment.Service;

import org.example.emplyeemanagment.Enums.LeaveStatus;
import org.example.emplyeemanagment.Responses.StandardResponse;
import org.example.emplyeemanagment.dtos.LeaveRequestDto;
import org.springframework.data.crossstore.ChangeSetPersister;

public interface LeaveRequestService {
    StandardResponse addLeaveRequest(LeaveRequestDto leaveRequestDto , Long employeeId);
    StandardResponse getAllLeaveRequests();
    StandardResponse getLeaveRequestById(Long id) throws ChangeSetPersister.NotFoundException;
    StandardResponse updateLeaveRequest(Long id, LeaveRequestDto leaveRequestDto);
    StandardResponse deleteLeaveRequest(Long id);
    StandardResponse findEmployeesCurrentlyOnLeave(String status);
}
