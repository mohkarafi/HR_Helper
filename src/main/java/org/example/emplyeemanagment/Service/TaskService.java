package org.example.emplyeemanagment.Service;

import org.example.emplyeemanagment.Responses.StandardResponse;
import org.example.emplyeemanagment.dtos.TaskDto;

import java.util.List;

public interface TaskService {
    StandardResponse addTask(TaskDto taskDto) throws Exception;
    StandardResponse findTaskByStatus(String status);
    StandardResponse updateTask(Long id , TaskDto taskDto);
    StandardResponse deleteTask(Long id);
    StandardResponse findAllTasks();
    StandardResponse findTasksByEmployeeId(Long employeeId);
}
