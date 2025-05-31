package org.example.emplyeemanagment.Responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.emplyeemanagment.dtos.LeaveRequestDto;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LeaveRequestResponse {
    private String messageCode;
    private String message;
    private LeaveRequestDto leaveRequest;
}
