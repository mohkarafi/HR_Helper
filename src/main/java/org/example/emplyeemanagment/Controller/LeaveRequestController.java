package org.example.emplyeemanagment.Controller;

import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Service.LeaveRequestService;
import org.example.emplyeemanagment.dtos.LeaveRequestDto;
import org.example.emplyeemanagment.Responses.LeaveRequestResponse;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("LeaveRequest")
public class LeaveRequestController {
    private LeaveRequestService leaveRequestService;
     @GetMapping("GetbyId")
    public LeaveRequestDto getLeaveRequestById(@RequestParam Long id) throws ChangeSetPersister.NotFoundException {
        return leaveRequestService.getLeaveRequestById(id);
    }
   @PostMapping("Add")
    public LeaveRequestResponse addLeaveRequest(@RequestBody LeaveRequestDto leaveRequestDto ,@PathVariable Long id) {
        return leaveRequestService.addLeaveRequest(leaveRequestDto , id);
    }
    @GetMapping("getAll")
    public List<LeaveRequestDto> getAllLeaveRequests() {
         return leaveRequestService.getAllLeaveRequests();
    }
    @PostMapping("delete")
    public void  deleteLeaveRequest(@RequestParam  Long id) {
         leaveRequestService.deleteLeaveRequest(id);
    }

    }
