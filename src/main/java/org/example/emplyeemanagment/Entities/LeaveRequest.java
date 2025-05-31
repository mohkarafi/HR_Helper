package org.example.emplyeemanagment.Entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

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
    @Column(name ="reason" , nullable = false )
    private String reason;
    @Column(name ="status" , nullable = false )
    private String status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;
}
