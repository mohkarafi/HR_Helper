package org.example.emplyeemanagment.Controller;

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
public class DepartmentController {
    private DepartmentService departmentService;

    @PostMapping(path = "save")
    public StandardResponse saveDepartment(@RequestBody DapartmentDto dapartmentDto) {
        return departmentService.addDepartment(dapartmentDto);
    }

    @PostMapping("delete")
    public StandardResponse deleteDepartment(@Valid @RequestParam Long id) {
        return departmentService.DeleteDepartmentById(id);
    }

    @GetMapping("findById")
    public StandardResponse getDepartmentById(@Valid @RequestParam Long id) throws ChangeSetPersister.NotFoundException {
        return departmentService.findDepartmentById(id);
    }

    @GetMapping("findByName")
    public StandardResponse getDepartmentByName(@Valid @RequestParam String name) throws ChangeSetPersister.NotFoundException {
        return departmentService.findDepartmentByName(name);
    }

    @GetMapping("All")
    public StandardResponse getAllDepartments() {
        return departmentService.findAllDepartments();
    }

}
