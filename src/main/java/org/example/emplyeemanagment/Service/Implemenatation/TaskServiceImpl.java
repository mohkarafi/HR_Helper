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
import org.example.emplyeemanagment.Responses.StandardResponse;
import org.example.emplyeemanagment.Service.TaskService;
import org.example.emplyeemanagment.dtos.TaskDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;
    private final TeamRepository teamRepository;

    @Override
    public StandardResponse addTask(TaskDto taskDto) throws Exception {
        try {
            Employee employee = employeeRepository.findById(taskDto.getEmployeeId()).orElseThrow(() -> new Exception("Employee Not Found"));
            Team team = teamRepository.findById(taskDto.getTeamId()).orElseThrow(() -> new Exception("Team Not Found"));
            Task task = TaskMapper.mapTotask(taskDto);
            task.setEmployee(employee);
            task.setTeam(team);
            taskRepository.save(task);
            return StandardResponse.builder()
                    .code("200")
                    .status("Task added successfully")
                    .data(TaskMapper.mapTotaskDto(task))
                    .build();
        } catch (Exception e) {
            return StandardResponse.builder()
                    .code("500")
                    .status("error:" + e.getMessage())
                    .build();
        }
    }

    @Override
    public StandardResponse findTaskByStatus(String Status) {
        Task task = taskRepository.findTaskByStatus(Status);
        if (task == null) {
            return StandardResponse.builder()
                    .code("404")
                    .status("Task not found")
                    .build();
        }
        return StandardResponse.builder()
                .code("200")
                .status("Successfully found task")
                .data(TaskMapper.mapTotaskDto(task))
                .build();
    }

    @Override
    public StandardResponse updateTask(Long id, TaskDto taskDto) {
        try {
            Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task Not Found"));
            Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee Not Found"));
            task.setTitle(taskDto.getName());
            task.setDescription(taskDto.getDescription());
            task.setStatus(taskDto.getStatus());
            task.setAssignedDate(taskDto.getAssignedDate());
            task.setDueDate(taskDto.getDueDate());
            task.setEmployee(employee);
            taskRepository.save(task);
            return StandardResponse.builder()
                    .code("200")
                    .status("Task updated successfully")
                    .data(TaskMapper.mapTotaskDto(task))
                    .build();

        } catch (Exception e) {
            return StandardResponse.builder()
                    .code("500")
                    .status("error:" + e.getMessage())
                    .build();
        }
    }


    @Override
    public StandardResponse deleteTask(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isEmpty()) {
            return StandardResponse.builder()
                    .code("404")
                    .status("Task with this id does not exist")
                    .data(null).build();
        }
        taskRepository.delete(task.get());
        return StandardResponse.builder()
                .code("202")
                .status("Task deleted successfully")
                .data(null).build();
    }

    @Override
    public StandardResponse findAllTasks(int page , int size) {
        Page<Task> taskPages = taskRepository.findAll(PageRequest.of(page,size));
        List<TaskDto> TasksDtos = taskPages.getContent().stream().map(task -> TaskMapper.mapTotaskDto(task)).collect(Collectors.toList());
        return StandardResponse.builder()
                .code("200")
                .status("Successfully found all tasks")
                .data(TasksDtos)
                .build();
    }

    @Override
    public StandardResponse findTasksByEmployeeId(Long employeeId) {
        if (taskRepository.findTasksByEmployeeId(employeeId).isEmpty()) {
            return StandardResponse.builder()
                    .code("403")
                    .status("No task assigned to this employee")
                    .data(null)
                    .build();
        }
        List<Task> tasks = taskRepository.findTasksByEmployeeId(employeeId);
        List<TaskDto> tasksDtos = tasks.stream().map(task -> TaskMapper.mapTotaskDto(task)).collect(Collectors.toList());
        return StandardResponse.builder()
                .code("200")
                .status("Successfully found tasks")
                .data(tasksDtos)
                .build();
    }
}
