package org.example.emplyeemanagment.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Responses.StandardResponse;
import org.example.emplyeemanagment.Service.TaskService;
import org.example.emplyeemanagment.dtos.TaskDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Task")
@AllArgsConstructor
public class TaskController {
    private TaskService taskService;

    @PostMapping("save")
    public StandardResponse saveTask(@Valid @RequestBody TaskDto taskDto) throws Exception {
        return taskService.addTask(taskDto);
    }

    @GetMapping("status")
    public StandardResponse findByStatus(@Valid @RequestBody String Status) {
        return taskService.findTaskByStatus(Status);
    }

    @PutMapping("update/{id}")
    public StandardResponse updateTask(@PathVariable Long id, @Valid @RequestBody TaskDto taskDto) throws Exception {
        return taskService.updateTask(id, taskDto);
    }

    @DeleteMapping("delete/{id}")
    public StandardResponse deleteTask(@PathVariable Long id) throws Exception {
        return taskService.deleteTask(id);
    }

    @GetMapping("findByEmployeeId/{employeeId}")
    public StandardResponse findByEmployeeId(@Valid @PathVariable Long employeeId) throws Exception {
        return taskService.findTasksByEmployeeId(employeeId);
    }

    @GetMapping("All")
    public StandardResponse getAllTasks(@RequestParam(name = "page", defaultValue = "0")int page ,
                                        @RequestParam(name = "size", defaultValue = "5")int size  ) {
        return taskService.findAllTasks(page , size);
    }
}
