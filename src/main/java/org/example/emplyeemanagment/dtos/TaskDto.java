package org.example.emplyeemanagment.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
    private String name;
    private String description;
    @JsonFormat(pattern = "dd-mm-yyyy")
    private Date dueDate;
    @JsonFormat(pattern = "dd-mm-yyyy")
    private Date assignedDate;
    private TaskStatus status;
    private Long employeeId;
    private String AssignedTo;
    private Long TeamId ;
    private String TeamName;
}
