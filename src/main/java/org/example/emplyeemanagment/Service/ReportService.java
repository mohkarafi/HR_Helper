package org.example.emplyeemanagment.Service;

import org.example.emplyeemanagment.Responses.StandardResponse;
import org.example.emplyeemanagment.dtos.ReportDto;

public interface ReportService {
    StandardResponse addReport(ReportDto reportDto) throws Exception;
    StandardResponse updateReport(Long id , ReportDto reportDto);
    StandardResponse deleteReport(Long id);
    StandardResponse getAllReports(int page , int size);
    StandardResponse getReportById(Long id);
    StandardResponse getReportByInternId(Long id);
}
