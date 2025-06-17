package org.example.emplyeemanagment.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Responses.StandardResponse;
import org.example.emplyeemanagment.Service.ReportService;
import org.example.emplyeemanagment.dtos.ReportDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Report")
@AllArgsConstructor
public class ReportController {
    private ReportService reportService;


    @PostMapping("Add")
    public StandardResponse addReport(@Valid  @RequestBody ReportDto reportDto) throws Exception {
        return reportService.addReport(reportDto);
    }

    @PutMapping("update/{id}")
    public StandardResponse updateReport(@PathVariable Long id , @Valid @RequestBody ReportDto reportDto) throws Exception {
        return reportService.updateReport(id, reportDto);
    }
    @GetMapping("findByInternId/{id}")
    public StandardResponse findByInternId(@PathVariable Long id){
        return reportService.getReportByInternId(id);
    }
    @DeleteMapping("delete/{id}")
    public StandardResponse deleteReport(@PathVariable Long id) throws Exception {
        return reportService.deleteReport(id);
    }
    @GetMapping("All")
    public StandardResponse getAllReports() throws Exception {
        return reportService.getAllReports();
    }

    @GetMapping("findOne/{id}")
    public StandardResponse getReportById(@PathVariable Long id) throws Exception {
        return reportService.getReportById(id);
    }

}
