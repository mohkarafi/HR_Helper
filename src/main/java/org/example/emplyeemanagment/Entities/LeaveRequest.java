package org.example.emplyeemanagment.Entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.example.emplyeemanagment.Enums.LeaveReason;
import org.example.emplyeemanagment.Enums.LeaveStatus;

import java.time.LocalDate;
import java.util.Date;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name ="LeaveRequest")
@JsonPropertyOrder({"reason", "status", "StartDate", "endDate", "employee"})
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="leaveRequest_id"  )
    private Long id;
    @Column(name ="start_date"  )
    private LocalDate StartDate ;
    @Column(name ="end_date" )
    private LocalDate EndDate ;
    @Enumerated(EnumType.STRING)
    private LeaveReason reason;
    @Column(name ="status" , nullable = false )
    @Enumerated(EnumType.STRING)
    private LeaveStatus status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;
}
