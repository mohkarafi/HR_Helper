package org.example.emplyeemanagment.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Responses.StandardResponse;
import org.example.emplyeemanagment.Service.LeaveRequestService;
import org.example.emplyeemanagment.dtos.LeaveRequestDto;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("LeaveRequest")
public class LeaveRequestController {
    private LeaveRequestService leaveRequestService;

    @GetMapping("GetbyId")
    public StandardResponse getLeaveRequestById(@Valid @RequestParam Long id) throws ChangeSetPersister.NotFoundException {
        return leaveRequestService.getLeaveRequestById(id);
    }

    @PostMapping("Add/{id}")
    public StandardResponse addLeaveRequest(@Valid @RequestBody LeaveRequestDto leaveRequestDto, @PathVariable Long id) {
        return leaveRequestService.addLeaveRequest(leaveRequestDto , id);
    }

    @GetMapping("getAll")
    public StandardResponse getAllLeaveRequests() {
        return leaveRequestService.getAllLeaveRequests();
    }

    @PostMapping("delete")
    public StandardResponse deleteLeaveRequest(@Valid @RequestParam Long id) {
        return leaveRequestService.deleteLeaveRequest(id);
    }

}
