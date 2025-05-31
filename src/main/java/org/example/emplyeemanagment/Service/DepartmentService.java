package org.example.emplyeemanagment.Service;

import org.example.emplyeemanagment.dtos.DapartmentDto;
import org.example.emplyeemanagment.Responses.DepartmentResponse;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface DepartmentService {
    DapartmentDto findDepartmentById(Long id) throws ChangeSetPersister.NotFoundException;
    DapartmentDto findDepartmentByNAme(String name);
    List<DapartmentDto> findAllDepartments();
    void DeleteDepartmentById(Long id);
    DepartmentResponse addDepartment(DapartmentDto department);
}
