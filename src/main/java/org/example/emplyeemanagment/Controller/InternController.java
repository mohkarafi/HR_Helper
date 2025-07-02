package org.example.emplyeemanagment.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Responses.StandardResponse;
import org.example.emplyeemanagment.Service.InternService;
import org.example.emplyeemanagment.dtos.InternDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Intern")
@AllArgsConstructor
@Tag(name = "Intern", description = "Intern management endpoints")

public class InternController {
    private InternService internService;
    @Operation(summary = "Add new Intern")
    @PostMapping("Add")
    public StandardResponse AddIntern(@RequestBody InternDto internDto) throws Exception {
        return internService.AddIntern(internDto);
    }

    @Operation(summary = "Update an Intern")
    @PutMapping("update/{id}")
    public StandardResponse UpdateIntern(@Valid @PathVariable Long id, @RequestBody InternDto internDto) throws Exception {
        return internService.UpdateIntern(id, internDto);
    }

    @Operation(summary = "Delete an Intern ")
    @DeleteMapping("delete/{id}")
    public StandardResponse DeleteIntern(@Valid @PathVariable Long id) throws Exception {
        return internService.DeleteIntern(id);
    }

    @Operation(summary = "Find interns by supervisor")
    @GetMapping("internsBySupervisor/{id}")
    public StandardResponse InternsBySupervisor(@Valid @PathVariable Long id) throws Exception {
        return internService.findInternBySupervisor(id);
    }

    @Operation(summary = "Find an intern")
    @GetMapping("findOne/{id}")
    public StandardResponse GetInternById(@Valid @PathVariable Long id) throws Exception {
        return internService.findInternById(id);
    }


    @Operation(summary = "Get All interns")
    @GetMapping("All")
    public StandardResponse GetAllInterns(@RequestParam(name = "page", defaultValue = "0")int page ,
                                          @RequestParam(name = "size", defaultValue = "5")int size  ) throws Exception {
        return internService.getAllInterns(page,size);
    }
}
