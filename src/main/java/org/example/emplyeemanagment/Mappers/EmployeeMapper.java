package org.example.emplyeemanagment.Mappers;

import org.example.emplyeemanagment.Entities.Employee;
import org.example.emplyeemanagment.dtos.DapartmentDto;
import org.example.emplyeemanagment.dtos.EmployeeDto;

public class EmployeeMapper {
    public static EmployeeDto toEmployeeDto(Employee employee) {

        return EmployeeDto.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .hireDate(employee.getHireDate())
                .position(employee.getPosition())
                .department(employee.getDepartment())
                .isValid(employee.getIsVerified())
                .accountCreationToken(employee.getAccountCreationToken())
                .build();
    }


    public static Employee toEmployee(EmployeeDto employeeDto) {

        return    Employee.builder()
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .email(employeeDto.getEmail())
                .phoneNumber(employeeDto.getPhoneNumber())
                .position(employeeDto.getPosition())
                .hireDate(employeeDto.getHireDate())
                .department(employeeDto.getDepartment())
                .build();

    }
}
