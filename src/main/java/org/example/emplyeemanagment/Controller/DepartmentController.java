package org.example.emplyeemanagment.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Responses.StandardResponse;
import org.example.emplyeemanagment.Service.DepartmentService;
import org.example.emplyeemanagment.dtos.DapartmentDto;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("Department")
@Tag(name = "Department", description = "Department management endpoints")

public class DepartmentController {
    private DepartmentService departmentService;

    @Operation(summary = "Add new Department")
    @PostMapping(path = "save")
    public StandardResponse saveDepartment(@RequestBody DapartmentDto dapartmentDto) {
        return departmentService.addDepartment(dapartmentDto);
    }

    @Operation(summary = "Delete an Department")
    @PostMapping("delete")
    public StandardResponse deleteDepartment(@Valid @RequestParam Long id) {
        return departmentService.DeleteDepartmentById(id);
    }

    @Operation(summary = "Find Department by id")
    @GetMapping("findById")
    public StandardResponse getDepartmentById(@Valid @RequestParam Long id) throws ChangeSetPersister.NotFoundException {
        return departmentService.findDepartmentById(id);
    }

    @Operation(summary = "Find Department by name")
    @GetMapping("findByName")
    public StandardResponse getDepartmentByName(@Valid @RequestParam String name) throws ChangeSetPersister.NotFoundException {
        return departmentService.findDepartmentByName(name);
    }

    @Operation(summary = "Get All Departments")
    @GetMapping("All")
    public StandardResponse getAllDepartments(@RequestParam(name = "page", defaultValue = "0")int page ,
                                              @RequestParam(name = "size", defaultValue = "5")int size  ) {
        return departmentService.findAllDepartments(page,size);
    }

}
