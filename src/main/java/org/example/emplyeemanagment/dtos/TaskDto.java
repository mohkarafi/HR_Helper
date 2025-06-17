package org.example.emplyeemanagment.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.emplyeemanagment.Entities.Employee;
import org.example.emplyeemanagment.Entities.Task;
import org.example.emplyeemanagment.Entities.Team;
import org.example.emplyeemanagment.Enums.TaskStatus;


import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class TaskDto {
    private Long id;
    @NotBlank(message = "Task name must not be blank")
    @Size(max = 100, message = "Task name must be at most 100 characters")
    private String name;

    @NotBlank(message = "Description must not be blank")
    @Size(max = 1000, message = "Description must be at most 1000 characters")
    private String description;

    @NotNull(message = "Due date must not be null")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dueDate;

    @NotNull(message = "Assigned date must not be null")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date assignedDate;

    @NotNull(message = "Status must not be null")
    private TaskStatus status;

    @NotNull(message = "Employee ID must not be null")
    private Long employeeId;

    // @NotBlank(message = "AssignedTo must not be blank")
    private String assignedTo;

    @NotNull(message = "Team ID must not be null")
    private Long teamId;

    private String teamName;
}
