package org.example.emplyeemanagment.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.emplyeemanagment.Enums.ReportStatus;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class ReportDto {
    private  String title ;
    private String description ;
    @JsonFormat(pattern = "dd/mm/yyyy")
    private Date createdAT;
    private ReportStatus status;
    private Long internId;
    private String internName;
}
