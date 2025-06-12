package org.example.emplyeemanagment.Service;

import org.example.emplyeemanagment.Entities.Task;
import org.example.emplyeemanagment.Responses.GenericResponse;
import org.example.emplyeemanagment.dtos.TaskDto;

import java.util.List;

public interface TaskService {
    GenericResponse addTask(TaskDto taskDto) throws Exception;
    GenericResponse findTaskByStatus(String status);
    GenericResponse updateTask(Long id , TaskDto taskDto);
    GenericResponse deleteTask(Long id);
    List<TaskDto> findAllTasks();
}
