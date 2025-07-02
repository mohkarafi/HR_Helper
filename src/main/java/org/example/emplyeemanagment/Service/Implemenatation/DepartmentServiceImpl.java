package org.example.emplyeemanagment.Service.Implemenatation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.emplyeemanagment.Entities.Department;
import org.example.emplyeemanagment.Mappers.DepartmentMapper;
import org.example.emplyeemanagment.Repository.DepartmentRepository;
import org.example.emplyeemanagment.Responses.StandardResponse;
import org.example.emplyeemanagment.Service.DepartmentService;
import org.example.emplyeemanagment.dtos.DapartmentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;


    @Override
    public StandardResponse addDepartment(DapartmentDto departmentDto) {
        Boolean existByName = departmentRepository.existsByName(departmentDto.getName());
        if (existByName) {
            return StandardResponse.builder()
                    .code("404")
                    .status("Department already exists")
                    .build();
        }

        Department department = DepartmentMapper.toDepartment(departmentDto);
        departmentRepository.save(department);
        return StandardResponse.builder()
                .code("200")
                .status("Department added successfully")
                .data(DepartmentMapper.toDepartmentDto(department))
                .build();
    }


    @Override
    public StandardResponse findDepartmentById(Long id) throws RuntimeException {
        Optional<Department> department = departmentRepository.findById(id);
        if(department.isEmpty()){
            return StandardResponse.builder()
                    .code("404")
                    .status("Department Not Found")
                    .build();
        }
        return StandardResponse.builder()
                .code("200")
                .status("Department Found")
                .data(DepartmentMapper.toDepartmentDto(department.get()))
                .build();
    }

    @Override
    public StandardResponse findDepartmentByName(String name) {
        Department departmentByUsername = departmentRepository.findByName(name);
        return StandardResponse.builder()
                .code("200")
                .status("Department Found")
                .data(DepartmentMapper.toDepartmentDto(departmentByUsername))
                .build();
    }

    @Override
    public StandardResponse findAllDepartments(int page , int size) {
        Page<Department> internPages = departmentRepository.findAll(PageRequest.of(page, size));
        List<DapartmentDto> departmentsDto = internPages.getContent().stream().map(DepartmentMapper::toDepartmentDto).collect(Collectors.toList());
        return StandardResponse.builder()
                .code("200")
                .status("Departments Found")
                .data(departmentsDto)
                .build();
    }

    @Override
    public StandardResponse DeleteDepartmentById(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        if(department.isEmpty()){
            return StandardResponse.builder()
                    .code("404")
                    .status("Department Not Found")
                    .build();
        }
        departmentRepository.delete(department.get());
        return StandardResponse.builder()
                .code("200")
                .status("Department Deleted Successfully")
                .build();
    }



}
