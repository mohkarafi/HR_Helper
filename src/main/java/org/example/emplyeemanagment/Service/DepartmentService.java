package org.example.emplyeemanagment.Service;

import org.example.emplyeemanagment.Responses.StandardResponse;
import org.example.emplyeemanagment.dtos.DapartmentDto;
import org.springframework.data.crossstore.ChangeSetPersister;

public interface DepartmentService {
    StandardResponse findDepartmentById(Long id) throws ChangeSetPersister.NotFoundException;
    StandardResponse findDepartmentByName(String name);
    StandardResponse findAllDepartments(int page , int size);
    StandardResponse DeleteDepartmentById(Long id);
    StandardResponse addDepartment(DapartmentDto department);
}
