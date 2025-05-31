package org.example.emplyeemanagment.Mappers;

import org.example.emplyeemanagment.Entities.LeaveRequest;
import org.example.emplyeemanagment.dtos.LeaveRequestDto;

public class LeaveRequestMapper {

    public static LeaveRequest mapToLeaveRequest(LeaveRequestDto leaveRequestDto) {
        LeaveRequest leaveRequest = LeaveRequest.builder()
                .StartDate(leaveRequestDto.getStartDate())
                .EndDate(leaveRequestDto.getEndDate())
                .reason(leaveRequestDto.getReason())
                .status(leaveRequestDto.getStatus())
                .build();
        return leaveRequest;
    }

    public static LeaveRequestDto mapToLeaveRequestDto(LeaveRequest leaveRequest) {
        LeaveRequestDto leaveRequestDto = LeaveRequestDto.builder()
                .StartDate(leaveRequest.getStartDate())
                .EndDate(leaveRequest.getEndDate())
                .reason(leaveRequest.getReason())
                .status(leaveRequest.getStatus())
                .employeeId(leaveRequest.getEmployee() != null ? leaveRequest.getEmployee().getId() : null)
                .EmployeeName(leaveRequest.getEmployee() != null ? leaveRequest.getEmployee().getFirstName() + " " + leaveRequest.getEmployee().getLastName() : null)
                .build();
        return leaveRequestDto;
    }
}
