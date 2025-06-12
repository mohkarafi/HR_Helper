package org.example.emplyeemanagment.Controller;

import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Responses.GenericResponse;
import org.example.emplyeemanagment.Service.InternService;
import org.example.emplyeemanagment.dtos.InternDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Intern")
@AllArgsConstructor
public class InternController {
    private InternService internService;

    @PostMapping("Add")
    public GenericResponse AddIntern(@RequestBody InternDto internDto) throws Exception {
        return internService.AddIntern(internDto);
    }


    @PutMapping("update/{id}")
    public GenericResponse UpdateIntern(@PathVariable Long id , @RequestBody InternDto internDto) throws Exception {
        return internService.UpdateIntern(id, internDto);
    }


    @DeleteMapping("delete/{id}")
    public GenericResponse DeleteIntern(@PathVariable Long id) throws Exception {
        return internService.DeleteIntern(id);
    }


    @GetMapping("findOne/{id}")
    public GenericResponse GetInternById(@PathVariable Long id) throws Exception {
        return internService.findInternById(id);
    }

@GetMapping("All")
    public GenericResponse GetAllInterns() throws Exception {
        return internService.getAllInterns();
    }
}
