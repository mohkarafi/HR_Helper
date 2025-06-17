package org.example.emplyeemanagment.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InternDto {
    @NotNull(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Email is required")
    private String email;

    @NotNull(message = "The phone number is required")
    private String phone;

    private String school;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "Start date is required")
    @FutureOrPresent(message = "Start date must be today or in the future")
    private Date startDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "End date is required")
    @FutureOrPresent(message = "End date must be today or in the future")
    private Date endDate;

    private Long supervisor;
    private String supervisorName;
    private Long task;
    private String taskName;



}
