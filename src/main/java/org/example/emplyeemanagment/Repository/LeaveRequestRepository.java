package org.example.emplyeemanagment.Repository;

import org.example.emplyeemanagment.Entities.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    LeaveRequest findLeaveRequestById(Long id);
    LeaveRequest findLeaveRequestByReason(String Reason);
    LeaveRequest findLeaveRequestByEmployee_Id(Long id);
    Boolean existsLeaveRequestByEmployee_Id(Long id);
    void deleteLeaveRequestByEmployee_Id(Long id);


}
