package org.example.emplyeemanagment.Controller;

import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Responses.GenericResponse;
import org.example.emplyeemanagment.Service.TaskService;
import org.example.emplyeemanagment.dtos.TaskDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Task")
@AllArgsConstructor
public class TaskController {
    private TaskService taskService;
    @PostMapping("save")
    public GenericResponse saveTask( @RequestBody  TaskDto taskDto) throws Exception {
        return taskService.addTask(taskDto);
    }
    @GetMapping("status")
    public GenericResponse findByStatus( @RequestBody String Status)  {
        return taskService.findTaskByStatus(Status);
    }
    @PutMapping("update/{id}")
    public GenericResponse updateTask(@PathVariable Long id ,   @RequestBody  TaskDto taskDto) throws Exception {
        return taskService.updateTask(id , taskDto);
    }
    @DeleteMapping("delete/{id}")
    public GenericResponse deleteTask(@PathVariable Long id) throws Exception {
        return taskService.deleteTask(id);
    }
    @GetMapping("All")
    public List<TaskDto> getAllTasks(){
        return taskService.findAllTasks();
    }
}
