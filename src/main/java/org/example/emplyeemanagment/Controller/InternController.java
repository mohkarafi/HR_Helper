package org.example.emplyeemanagment.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Responses.StandardResponse;
import org.example.emplyeemanagment.Service.InternService;
import org.example.emplyeemanagment.dtos.InternDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Intern")
@AllArgsConstructor
public class InternController {
    private InternService internService;

    @PostMapping("Add")
    public StandardResponse AddIntern(@RequestBody InternDto internDto) throws Exception {
        return internService.AddIntern(internDto);
    }


    @PutMapping("update/{id}")
    public StandardResponse UpdateIntern(@Valid @PathVariable Long id, @RequestBody InternDto internDto) throws Exception {
        return internService.UpdateIntern(id, internDto);
    }


    @DeleteMapping("delete/{id}")
    public StandardResponse DeleteIntern(@Valid @PathVariable Long id) throws Exception {
        return internService.DeleteIntern(id);
    }

    @GetMapping("internsBySupervisor/{id}")
    public StandardResponse InternsBySupervisor(@Valid @PathVariable Long id) throws Exception {
        return internService.findInternBySupervisor(id);
    }

    @GetMapping("findOne/{id}")
    public StandardResponse GetInternById(@Valid @PathVariable Long id) throws Exception {
        return internService.findInternById(id);
    }

    @GetMapping("All")
    public StandardResponse GetAllInterns(@RequestParam(name = "page", defaultValue = "0")int page ,
                                          @RequestParam(name = "size", defaultValue = "5")int size  ) throws Exception {
        return internService.getAllInterns(page,size);
    }
}
