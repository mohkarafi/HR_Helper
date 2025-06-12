package org.example.emplyeemanagment.Service.Implemenatation;

import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Entities.Intern;
import org.example.emplyeemanagment.Entities.Report;
import org.example.emplyeemanagment.Mappers.ReportMapper;
import org.example.emplyeemanagment.Repository.InternRepository;
import org.example.emplyeemanagment.Repository.ReportRepository;
import org.example.emplyeemanagment.Responses.GenericResponse;
import org.example.emplyeemanagment.Service.ReportService;
import org.example.emplyeemanagment.dtos.ReportDto;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReportServiceImplt implements ReportService {
    private ReportRepository reportRepository;
    private InternRepository internRepository;


    @Override
    public GenericResponse addReport(ReportDto reportDto) {
        try {
            Intern intern = internRepository.findById(reportDto.getInternId())
                    .orElseThrow(() -> new Exception("Intern not found with id: " + reportDto.getInternId()));

            Report report = ReportMapper.mapToReport(reportDto);
            report.setIntern(intern);
            Report savedReport = reportRepository.save(report);

            return GenericResponse.builder()
                    .messageCode("200")
                    .ResponseMessage("Report added successfully")
                    .data(ReportMapper.mapToReportDto(savedReport))
                    .build();

        } catch (Exception e) {
            return GenericResponse.builder()
                    .messageCode("500")
                    .ResponseMessage("Error adding report: " + e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @Override
    public GenericResponse updateReport(Long id, ReportDto reportDto) {
        Optional<Report> optionalReport = reportRepository.findById(id);
        if (optionalReport.isEmpty()) {
            return GenericResponse.builder()
                    .messageCode("404")
                    .ResponseMessage("Report not found")
                    .data(null)
                    .build();
        }

        try {
            Intern intern = internRepository.findById(reportDto.getInternId())
                    .orElseThrow(() -> new Exception("Intern not found"));

            Report reportToUpdate = optionalReport.get();
            reportToUpdate.setTitle(reportDto.getTitle());
            reportToUpdate.setContent(reportDto.getDescription());
            reportToUpdate.setIntern(intern);

            Report updatedReport = reportRepository.save(reportToUpdate);

            return GenericResponse.builder()
                    .messageCode("200")
                    .ResponseMessage("Report updated successfully")
                    .data(ReportMapper.mapToReportDto(updatedReport))
                    .build();

        } catch (Exception e) {
            return GenericResponse.builder()
                    .messageCode("500")
                    .ResponseMessage("Error updating report: " + e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @Override
    public GenericResponse deleteReport(Long id) {
        Optional<Report> optionalReport = reportRepository.findById(id);
        if (optionalReport.isEmpty()) {
            return GenericResponse.builder()
                    .messageCode("404")
                    .ResponseMessage("Report not found")
                    .build();
        }

        reportRepository.delete(optionalReport.get());
        return GenericResponse.builder()
                .messageCode("200")
                .ResponseMessage("Report deleted successfully")
                .build();
    }

    @Override
    public GenericResponse getAllReports() {
        List<Report> reports = reportRepository.findAll();
        List<ReportDto> reportDtos = reports.stream()
                .map(ReportMapper::mapToReportDto)
                .collect(Collectors.toList());

        return GenericResponse.builder()
                .messageCode("200")
                .ResponseMessage("Success")
                .data(reportDtos)
                .build();
    }

    @Override
    public GenericResponse getReportById(Long id) {
        Optional<Report> optionalReport = reportRepository.findById(id);
        if (optionalReport.isEmpty()) {
            return GenericResponse.builder()
                    .messageCode("404")
                    .ResponseMessage("Report not found")
                    .data(null)
                    .build();
        }

        ReportDto reportDto = ReportMapper.mapToReportDto(optionalReport.get());

        return GenericResponse.builder()
                .messageCode("200")
                .ResponseMessage("Success")
                .data(reportDto)
                .build();
    }

}
