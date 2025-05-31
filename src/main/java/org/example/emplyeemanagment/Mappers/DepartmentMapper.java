package org.example.emplyeemanagment.Mappers;

import org.example.emplyeemanagment.Entities.Department;
import org.example.emplyeemanagment.dtos.DapartmentDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class DepartmentMapper {
    public static DapartmentDto toDepartmentDto(Department department) {
        DapartmentDto dapartmentDto = DapartmentDto.builder()
                .id(department.getId())
                .name(department.getName())
                .build();
        return dapartmentDto;
    }

    public static Department toDepartment(DapartmentDto dapartmentDto) {
        Department department = Department.builder()
                .id(dapartmentDto.getId())
                .name(dapartmentDto.getName())
                .build();
        return department;
    }
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
