package org.example.emplyeemanagment.Repository;

import org.example.emplyeemanagment.Entities.Employee;
import org.example.emplyeemanagment.Entities.LeaveRequest;
import org.example.emplyeemanagment.Enums.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM LeaveRequest l WHERE l.employee.id = :employeeId AND l.status IN :statuses")
    boolean existsByEmployeeIdAndStatusIn(@Param("employeeId") Long employeeId, @Param("statuses") List<LeaveStatus> statuses);

    LeaveRequest findLeaveRequestByEmployee_Id(Long id);
    void deleteLeaveRequestByEmployee_Id(Long id);

    @Query("select l.employee from LeaveRequest l where l.status = :status and CURRENT DATE BETWEEN l.StartDate and l.EndDate")
    List<Employee> findEmployeesCurrentlyOnLeave(@Param("status") String status);

}
