package org.example.emplyeemanagment.Controller;

import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Service.DepartmentService;
import org.example.emplyeemanagment.dtos.DapartmentDto;
import org.example.emplyeemanagment.Responses.DepartmentResponse;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("Department")
public class DepartmentController {
    private DepartmentService departmentService;

    @PostMapping(path = "save")
    public DepartmentResponse saveDepartment(@RequestBody DapartmentDto dapartmentDto) {
        return departmentService.addDepartment(dapartmentDto);
    }

    @PostMapping("delete")
    public void deleteDepartment(@RequestParam Long id) {
        departmentService.DeleteDepartmentById(id);
    }

    @GetMapping("findById")
    public DapartmentDto getDepartmentById(@RequestParam Long id) throws ChangeSetPersister.NotFoundException {
        return departmentService.findDepartmentById(id);
    }

    @GetMapping("findByName")
    public DapartmentDto getDepartmentByName(@RequestParam String name) throws ChangeSetPersister.NotFoundException {
        return departmentService.findDepartmentByNAme(name);
    }

    @GetMapping("All")
    public List<DapartmentDto> getAllDepartments() {
        return departmentService.findAllDepartments();
    }

}
