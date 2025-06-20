package org.example.emplyeemanagment.Controller;

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
public class LeaveRequestController {
    private LeaveRequestService leaveRequestService;

    @GetMapping("GetbyId")
    public StandardResponse getLeaveRequestById(@Valid @RequestParam Long id) throws ChangeSetPersister.NotFoundException {
        return leaveRequestService.getLeaveRequestById(id);
    }
    @PutMapping("update/{id}")
    public StandardResponse updateLeaveRequest(@RequestBody LeaveRequestDto leaveRequestDto, @PathVariable Long id)  {
        return leaveRequestService.updateLeaveRequest(id,leaveRequestDto);
    }

    @PostMapping("Add/{id}")
    public StandardResponse addLeaveRequest(@Valid @RequestBody LeaveRequestDto leaveRequestDto, @PathVariable Long id) {
        return leaveRequestService.addLeaveRequest(leaveRequestDto , id);
    }

    @GetMapping("getAll")
    public StandardResponse getAllLeaveRequests(@RequestParam(name = "page", defaultValue = "0")int page ,
                                                @RequestParam(name = "size", defaultValue = "5")int size  ) {
        return leaveRequestService.getAllLeaveRequests(page,size);
    }

    @PostMapping("delete")
    public StandardResponse deleteLeaveRequest(@Valid @RequestParam Long id) {
        return leaveRequestService.deleteLeaveRequest(id);
    }

}
