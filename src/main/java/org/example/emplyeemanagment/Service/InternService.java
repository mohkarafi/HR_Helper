package org.example.emplyeemanagment.Service;

import org.example.emplyeemanagment.Responses.GenericResponse;
import org.example.emplyeemanagment.dtos.InternDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface InternService {
    GenericResponse AddIntern(InternDto internDto) throws Exception;
    GenericResponse UpdateIntern( Long id , InternDto internDto) throws Exception;
    GenericResponse getAllInterns();
    GenericResponse DeleteIntern(Long id) throws Exception;
    GenericResponse findInternById(Long id);
    GenericResponse findInternBySupervisor(InternDto internDto);
}
