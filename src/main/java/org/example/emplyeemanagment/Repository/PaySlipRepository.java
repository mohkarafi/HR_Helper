package org.example.emplyeemanagment.Repository;

import org.example.emplyeemanagment.Entities.PaySlip;
import org.example.emplyeemanagment.Entities.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaySlipRepository extends JpaRepository<PaySlip, Long> {
    Page<PaySlip> findAll(Pageable pageable);
    @Query("Select p From PaySlip p where p.employee.id=:employeeId")
    List<PaySlip> findByEmployeeId(@Param("employeeId") Long employeeId);
}
