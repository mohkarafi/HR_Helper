package org.example.emplyeemanagment.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Responses.StandardResponse;
import org.example.emplyeemanagment.Service.TaskService;
import org.example.emplyeemanagment.dtos.TaskDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Task")
@AllArgsConstructor
@Tag(name = "Task", description = "Task management endpoints")

public class TaskController {
    private TaskService taskService;

    @Operation(summary = "Create a new task")
    @PostMapping("save")
    public StandardResponse saveTask(@Valid @RequestBody TaskDto taskDto) throws Exception {
        return taskService.addTask(taskDto);
    }

    @Operation(summary = "Find tasks by status")
    @GetMapping("status")
    public StandardResponse findByStatus(@Valid @RequestBody String Status) {
        return taskService.findTaskByStatus(Status);
    }

    @Operation(summary = "Update a task by ID")
    @PutMapping("update/{id}")
    public StandardResponse updateTask(@PathVariable Long id, @Valid @RequestBody TaskDto taskDto) throws Exception {
        return taskService.updateTask(id, taskDto);
    }

    @Operation(summary = "Delete a task by ID")
    @DeleteMapping("delete/{id}")
    public StandardResponse deleteTask(@PathVariable Long id) throws Exception {
        return taskService.deleteTask(id);
    }

    @Operation(summary = "Find tasks by employee ID")
    @GetMapping("findByEmployeeId/{employeeId}")
    public StandardResponse findByEmployeeId(@Valid @PathVariable Long employeeId) throws Exception {
        return taskService.findTasksByEmployeeId(employeeId);
    }

    @Operation(summary = "Get all tasks with pagination")
    @GetMapping("All")
    public StandardResponse getAllTasks(@RequestParam(name = "page", defaultValue = "0") int page,
                                        @RequestParam(name = "size", defaultValue = "5") int size) {
        return taskService.findAllTasks(page, size);
    }
}