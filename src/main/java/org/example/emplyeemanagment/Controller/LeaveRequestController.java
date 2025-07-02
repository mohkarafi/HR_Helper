package org.example.emplyeemanagment.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Enums.LeaveStatus;
import org.example.emplyeemanagment.Responses.StandardResponse;
import org.example.emplyeemanagment.Service.LeaveRequestService;
import org.example.emplyeemanagment.dtos.LeaveRequestDto;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("LeaveRequest")
@Tag(name = "LeaveRequest", description = "LeaveRequest management endpoints")

public class LeaveRequestController {
    private LeaveRequestService leaveRequestService;

    @Operation(summary = "Get leave request by ID")
    @GetMapping("/getById")
    public StandardResponse getLeaveRequestById(@Valid @RequestParam Long id) throws ChangeSetPersister.NotFoundException {
        return leaveRequestService.getLeaveRequestById(id);
    }

    @Operation(summary = "Update a leave request")
    @PutMapping("/update/{id}")
    public StandardResponse updateLeaveRequest(@RequestBody LeaveRequestDto leaveRequestDto, @PathVariable Long id) {
        return leaveRequestService.updateLeaveRequest(id, leaveRequestDto);
    }

    @Operation(summary = "Create a new leave request")
    @PostMapping("/add/{id}")
    public StandardResponse addLeaveRequest(@Valid @RequestBody LeaveRequestDto leaveRequestDto, @PathVariable Long id) {
        return leaveRequestService.addLeaveRequest(leaveRequestDto, id);
    }

    @Operation(summary = "Get all leave requests with pagination")
    @GetMapping("/getAll")
    public StandardResponse getAllLeaveRequests(@RequestParam(name = "page", defaultValue = "0") int page,
                                                @RequestParam(name = "size", defaultValue = "5") int size) {
        return leaveRequestService.getAllLeaveRequests(page, size);
    }

    @Operation(summary = "Delete a leave request by ID")
    @PostMapping("/delete")
    public StandardResponse deleteLeaveRequest(@Valid @RequestParam Long id) {
        return leaveRequestService.deleteLeaveRequest(id);
    }
}