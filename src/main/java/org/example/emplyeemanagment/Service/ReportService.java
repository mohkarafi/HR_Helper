package org.example.emplyeemanagment.Service;

import org.example.emplyeemanagment.Responses.GenericResponse;
import org.example.emplyeemanagment.dtos.ReportDto;

public interface ReportService {
    GenericResponse addReport(ReportDto reportDto) throws Exception;
    GenericResponse updateReport( Long id , ReportDto reportDto);
    GenericResponse deleteReport(Long id);
    GenericResponse getAllReports();
    GenericResponse getReportById(Long id);
}
