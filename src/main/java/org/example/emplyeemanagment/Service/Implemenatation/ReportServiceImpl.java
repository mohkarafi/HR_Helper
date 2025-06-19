package org.example.emplyeemanagment.Service.Implemenatation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.emplyeemanagment.Entities.Intern;
import org.example.emplyeemanagment.Entities.Report;
import org.example.emplyeemanagment.Mappers.ReportMapper;
import org.example.emplyeemanagment.Repository.InternRepository;
import org.example.emplyeemanagment.Repository.ReportRepository;
import org.example.emplyeemanagment.Responses.StandardResponse;
import org.example.emplyeemanagment.Service.ReportService;
import org.example.emplyeemanagment.dtos.ReportDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final InternRepository internRepository;


    @Override
    public StandardResponse addReport(ReportDto reportDto) {
        try {
            Intern intern = internRepository.findById(reportDto.getInternId())
                    .orElseThrow(() -> new Exception("Intern not found with id: " + reportDto.getInternId()));

            Report report = ReportMapper.mapToReport(reportDto);
            report.setIntern(intern);
            Report savedReport = reportRepository.save(report);

            return StandardResponse.builder()
                    .code("200")
                    .status("Report added successfully")
                    .data(ReportMapper.mapToReportDto(savedReport))
                    .build();

        } catch (Exception e) {
            return StandardResponse.builder()
                    .code("500")
                    .status("Error adding report: " + e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @Override
    public StandardResponse updateReport(Long id, ReportDto reportDto) {
        Optional<Report> optionalReport = reportRepository.findById(id);
        if (optionalReport.isEmpty()) {
            return StandardResponse.builder()
                    .code("404")
                    .status("Report not found")
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

            return StandardResponse.builder()
                    .code("200")
                    .status("Report updated successfully")
                    .data(ReportMapper.mapToReportDto(updatedReport))
                    .build();

        } catch (Exception e) {
            return StandardResponse.builder()
                    .code("500")
                    .status("Error updating report: " + e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @Override
    public StandardResponse deleteReport(Long id) {
        Optional<Report> optionalReport = reportRepository.findById(id);
        if (optionalReport.isEmpty()) {
            return StandardResponse.builder()
                    .code("404")
                    .status("Report not found")
                    .build();
        }

        reportRepository.delete(optionalReport.get());
        return StandardResponse.builder()
                .code("200")
                .status("Report deleted successfully")
                .build();
    }

    @Override
    public StandardResponse getAllReports(int page , int size) {
        Page<Report> reportPages = reportRepository.findAll(PageRequest.of(page,size));
        List<ReportDto> reportDtos = reportPages.getContent().stream()
                .map(ReportMapper::mapToReportDto)
                .collect(Collectors.toList());

        return StandardResponse.builder()
                .code("200")
                .status("Success")
                .data(reportDtos)
                .build();
    }

    @Override
    public StandardResponse getReportById(Long id) {
        Optional<Report> optionalReport = reportRepository.findById(id);
        if (optionalReport.isEmpty()) {
            return StandardResponse.builder()
                    .code("404")
                    .status("Report not found")
                    .data(null)
                    .build();
        }

        ReportDto reportDto = ReportMapper.mapToReportDto(optionalReport.get());
        return StandardResponse.builder()
                .code("200")
                .status("Success")
                .data(reportDto)
                .build();
    }

    @Override
    public StandardResponse getReportByInternId(Long internID) {
        Optional<Intern> intern = internRepository.findById(internID);
        if(intern.isEmpty()) {
            return StandardResponse.builder()
                    .code("403")
                    .status("Intern with the id " + internID + " not found")
                    .data(null)
                    .build();
        }
        List<Report>reports = reportRepository.findByInternId(internID);
        log.info("Nombre de rapports trouvés pour internID {} : {}", internID, reports.size());

        List<ReportDto> reportDtos = reports.stream().map(report -> ReportMapper.mapToReportDto(report)).collect(Collectors.toList());
        return StandardResponse.builder()
                .code("200")
                .status("Success")
                .data(reportDtos)
                .build();
    }

}
