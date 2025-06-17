package org.example.emplyeemanagment.Mappers;

import org.example.emplyeemanagment.Entities.Report;
import org.example.emplyeemanagment.dtos.ReportDto;

public class ReportMapper {
    public static Report mapToReport(ReportDto reportDto) {
        Report report = Report.builder()
                .title(reportDto.getTitle())
                .content(reportDto.getDescription())
                .createdAt(reportDto.getCreatedAt())
                .status(reportDto.getStatus())
                .build();
        return report;
    }

    public static ReportDto mapToReportDto(Report report) {
        ReportDto reportDto = ReportDto.builder()
                .title(report.getTitle())
                .description(report.getContent())
                .createdAt(report.getCreatedAt())
                .status(report.getStatus())
                .internId(report.getIntern() != null ? report.getIntern().getId(): null)
                .internName(report.getIntern() != null ? report.getIntern().getFullName() : null)
                .build();
        return reportDto;
    }
}
