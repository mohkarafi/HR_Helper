package org.example.emplyeemanagment.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.emplyeemanagment.Enums.paySlipStatus;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class PaySlip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private Date payrollDate ;
    private Double baseSalary;
    private Double bonus;
    private Double deductions;
    private Double totalPaid;
    private paySlipStatus status = paySlipStatus.Pending;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
