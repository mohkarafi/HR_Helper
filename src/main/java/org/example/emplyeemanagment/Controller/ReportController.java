package org.example.emplyeemanagment.Controller;

import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Responses.GenericResponse;
import org.example.emplyeemanagment.Service.ReportService;
import org.example.emplyeemanagment.dtos.ReportDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Report")
@AllArgsConstructor
public class ReportController {
    private ReportService reportService;


    @PostMapping("Add")
    public GenericResponse addReport( @RequestBody ReportDto reportDto) throws Exception {
        return reportService.addReport(reportDto);
    }

    @PutMapping("update/{id}")
    public GenericResponse updateReport(@PathVariable Long id , @RequestBody ReportDto reportDto) throws Exception {
        return reportService.updateReport(id, reportDto);
    }

    @DeleteMapping("delete/{id}")
    public GenericResponse deleteReport(@PathVariable Long id) throws Exception {
        return reportService.deleteReport(id);
    }
    @GetMapping("All")
    public GenericResponse getAllReports() throws Exception {
        return reportService.getAllReports();
    }

    @GetMapping("findOne/{id}")
    public GenericResponse getReportById(@PathVariable Long id) throws Exception {
        return reportService.getReportById(id);
    }

}
