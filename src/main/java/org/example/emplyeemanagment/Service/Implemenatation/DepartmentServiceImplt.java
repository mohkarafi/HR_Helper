package org.example.emplyeemanagment.Service.Implemenatation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Entities.Department;
import org.example.emplyeemanagment.Mappers.DepartmentMapper;
import org.example.emplyeemanagment.Repository.DepartmentRepository;
import org.example.emplyeemanagment.Service.DepartmentService;
import org.example.emplyeemanagment.dtos.DapartmentDto;
import org.example.emplyeemanagment.Responses.DepartmentResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class DepartmentServiceImplt implements DepartmentService {
    private DepartmentRepository departmentRepository;

    @Override
    public DapartmentDto findDepartmentById(Long id) throws RuntimeException {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Account Not Found Exception"));
        return DepartmentMapper.toDepartmentDto(department);
    }

    @Override
    public DapartmentDto findDepartmentByNAme(String name) {
        Department departmentByUsername = departmentRepository.findByName(name);
        return DepartmentMapper.toDepartmentDto(departmentByUsername);
    }

    @Override
    public List<DapartmentDto> findAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        List<DapartmentDto> departmentsDto = departments.stream().map(DepartmentMapper::toDepartmentDto).collect(Collectors.toList());
        return departmentsDto;
    }

    @Override
    public void DeleteDepartmentById(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Account Not Found Exception"));
        departmentRepository.delete(department);
    }

    @Override
    public DepartmentResponse addDepartment(DapartmentDto departmentDto) {
        Department department = DepartmentMapper.toDepartment(departmentDto);
        departmentRepository.save(department);
        return DepartmentResponse.builder()
                .messageCode("001")
                .ResponseMessage("Department added successfully")
                .data(DepartmentMapper.toDepartmentDto(department))
                .build();
    }

}
