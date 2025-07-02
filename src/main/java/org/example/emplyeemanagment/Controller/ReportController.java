package org.example.emplyeemanagment.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Responses.StandardResponse;
import org.example.emplyeemanagment.Service.ReportService;
import org.example.emplyeemanagment.dtos.ReportDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Report")
@AllArgsConstructor
@Tag(name = "Report", description = "Report management endpoints")

public class ReportController {
    private ReportService reportService;

    @Operation(summary = "Add a new report")
    @PostMapping("Add")
    public StandardResponse addReport(@Valid @RequestBody ReportDto reportDto) throws Exception {
        return reportService.addReport(reportDto);
    }

    @Operation(summary = "Update a report by ID")
    @PutMapping("update/{id}")
    public StandardResponse updateReport(@PathVariable Long id, @Valid @RequestBody ReportDto reportDto) throws Exception {
        return reportService.updateReport(id, reportDto);
    }

    @Operation(summary = "Find reports by intern ID")
    @GetMapping("findByInternId/{id}")
    public StandardResponse findByInternId(@PathVariable Long id) {
        return reportService.getReportByInternId(id);
    }

    @Operation(summary = "Delete a report by ID")
    @DeleteMapping("delete/{id}")
    public StandardResponse deleteReport(@PathVariable Long id) throws Exception {
        return reportService.deleteReport(id);
    }

    @Operation(summary = "Get all reports with pagination")
    @GetMapping("All")
    public StandardResponse getAllReports(@RequestParam(name = "page", defaultValue = "0") int page,
                                          @RequestParam(name = "size", defaultValue = "5") int size) throws Exception {
        return reportService.getAllReports(page, size);
    }

    @Operation(summary = "Get a report by ID")
    @GetMapping("findOne/{id}")
    public StandardResponse getReportById(@PathVariable Long id) throws Exception {
        return reportService.getReportById(id);
    }
}