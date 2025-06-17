package org.example.emplyeemanagment.dtos;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.emplyeemanagment.Entities.Employee;
import org.example.emplyeemanagment.Enums.LeaveReason;
import org.example.emplyeemanagment.Enums.LeaveStatus;


import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonPropertyOrder({"reason" , "status" , "startDate" , "endDate" , "employeeId" , "employeeName"})
public class LeaveRequestDto {
   private Long id;
    @NotNull(message = "Start date is required")
    @FutureOrPresent(message = "Start date must be today or in the future")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate StartDate;

    @NotNull(message = "End date is required")
    @FutureOrPresent(message = "End date must be today or in the future")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate EndDate;

    @NotNull(message = "Leave reason is required")
    private LeaveReason reason;

    @NotNull(message = "Status is required")
    private LeaveStatus status;

    private Long employeeId;
    private String EmployeeName;

}
