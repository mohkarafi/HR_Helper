package org.example.emplyeemanagment.Mappers;

import org.example.emplyeemanagment.Entities.Task;
import org.example.emplyeemanagment.dtos.TaskDto;

public class TaskMapper {
    public static Task mapTotask(TaskDto taskDto) {
        Task task = Task.builder()
                .title(taskDto.getName())
                .status(taskDto.getStatus())
                .assignedDate(taskDto.getAssignedDate())
                .description(taskDto.getDescription())
                .dueDate(taskDto.getDueDate())
                .build();
        return task;
    }
    public static TaskDto mapTotaskDto(Task task) {
        TaskDto taskDto = TaskDto.builder()
                .id(task.getId())
                .name(task.getTitle())
                .status(task.getStatus())
                .dueDate(task.getDueDate())
                .assignedDate(task.getAssignedDate())
                .description(task.getDescription())
                .employeeId(task.getEmployee().getId() != null ? task.getEmployee().getId() : null)
                .AssignedTo(task.getEmployee() != null ? task.getEmployee().getFirstName() + " "+ task.getEmployee().getLastName() : null)
                .TeamId(task.getTeam() != null ? task.getTeam().getId() : null)
                .TeamName(task.getTeam() != null ? task.getTeam().getName() : null)
                .build();

        return taskDto;
    }
}
