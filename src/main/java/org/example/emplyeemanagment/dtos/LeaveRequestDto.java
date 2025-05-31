package org.example.emplyeemanagment.dtos;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.emplyeemanagment.Entities.Employee;


import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonPropertyOrder({"reason" , "status" , "startDate" , "endDate" , "employeeId" , "employeeName"})
public class LeaveRequestDto {

    @NotNull(message = "La date de début est obligatoire")
    @FutureOrPresent(message = "La date de début doit être aujourd'hui ou dans le futur")
    private LocalDate StartDate;

    @NotNull(message = "La date de fin est obligatoire")
    @FutureOrPresent(message = "La date de fin doit être aujourd'hui ou dans le futur")
    private LocalDate EndDate;

    @NotBlank(message = "La raison du congé est obligatoire")
    private String reason;
    @NotBlank(message = "Le statut est obligatoire")
    private String status;
    private Long employeeId;
    private String EmployeeName;
}
