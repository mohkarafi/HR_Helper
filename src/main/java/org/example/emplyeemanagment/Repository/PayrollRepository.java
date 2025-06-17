package org.example.emplyeemanagment.Repository;

import org.example.emplyeemanagment.Entities.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, Long> {
    @Query("Select p From Payroll p where p.employee.id=:employeeId")
    List<Payroll> findByEmployeeId(@Param("employeeId") Long employeeId);
}
