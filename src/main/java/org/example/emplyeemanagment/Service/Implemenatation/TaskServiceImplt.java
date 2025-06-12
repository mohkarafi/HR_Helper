package org.example.emplyeemanagment.Service.Implemenatation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Entities.Employee;
import org.example.emplyeemanagment.Entities.Task;
import org.example.emplyeemanagment.Entities.Team;
import org.example.emplyeemanagment.Mappers.TaskMapper;
import org.example.emplyeemanagment.Repository.EmployeeRepository;
import org.example.emplyeemanagment.Repository.TaskRepository;
import org.example.emplyeemanagment.Repository.TeamRepository;
import org.example.emplyeemanagment.Responses.GenericResponse;
import org.example.emplyeemanagment.Service.TaskService;
import org.example.emplyeemanagment.dtos.TaskDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class TaskServiceImplt implements TaskService {
    private TaskRepository taskRepository;
    private EmployeeRepository employeeRepository;
    private TeamRepository teamRepository;
    @Override
    public GenericResponse addTask(TaskDto taskDto) throws Exception {
        Employee employee = employeeRepository.findById(taskDto.getEmployeeId()).orElseThrow(() -> new Exception("Employee Not Found"));
       Team team = teamRepository.findById(taskDto.getTeamId()).orElseThrow(() -> new Exception("Team Not Found"));
        Task task = TaskMapper.mapTotask(taskDto);
        task.setEmployee(employee);
        task.setTeam(team);
        taskRepository.save(task);
        return GenericResponse.builder()
                .messageCode("200")
                .ResponseMessage("Task added successfully")
                .data(TaskMapper.mapTotaskDto(task))
                .build();
    }

    @Override
    public GenericResponse findTaskByStatus(String Status) {
        Task task = taskRepository.findTaskByStatus(Status);
        if (task == null) {
            return GenericResponse.builder()
                    .messageCode("404")
                    .ResponseMessage("Task not found")
                    .build();
        }
        return GenericResponse.builder()
                .messageCode("200")
                .ResponseMessage("Successfully found task")
                .data(TaskMapper.mapTotaskDto(task))
                .build();
    }

    @Override
    public GenericResponse updateTask(Long id, TaskDto taskDto) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task Not Found"));
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee Not Found"));
        task.setTitle(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        task.setAssignedDate(taskDto.getAssignedDate());
        task.setDueDate(taskDto.getDueDate());
        task.setEmployee(employee);
        taskRepository.save(task);
        return GenericResponse.builder()
                .messageCode("200")
                .ResponseMessage("Task updated successfully")
                .data(TaskMapper.mapTotaskDto(task))
                .build();

    }


    @Override
    public GenericResponse deleteTask(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()) {
            return GenericResponse.builder()
                    .messageCode("404")
                    .ResponseMessage("Task with this id does not exist")
                    .data(null).build();
        }
        taskRepository.delete(task.get());
        return GenericResponse.builder()
                .messageCode("202")
                .ResponseMessage("Task deleted successfully")
                .data(null).build();
    }

    @Override
    public List<TaskDto> findAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        List<TaskDto> TasksDtos = tasks.stream().map(task ->  TaskMapper.mapTotaskDto(task)).collect(Collectors.toList());
        return TasksDtos;
    }
}
