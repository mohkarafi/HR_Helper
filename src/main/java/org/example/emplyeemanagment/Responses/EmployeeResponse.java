package org.example.emplyeemanagment.Responses;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.emplyeemanagment.dtos.EmployeeDto;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonPropertyOrder({"messageCode", "responseMessage", "data"})
public class EmployeeResponse {
    private String messageCode;
    private String ResponseMessage;
    private EmployeeDto data;
}
