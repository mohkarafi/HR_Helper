package org.example.emplyeemanagment.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.emplyeemanagment.Enums.ReportStatus;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class ReportDto {
    @NotBlank(message = "Title must not be blank")
    private String title;

    @NotBlank(message = "Description must not be blank")
    private String description;

    @NotNull(message = "Created date is required")
    @FutureOrPresent(message = "Created date must be today or in the future")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date createdAt;

    @NotNull(message = "Status is required")
    private ReportStatus status;

    @NotNull(message = "Intern ID is required")
    private Long internId;

    private String internName;
}
