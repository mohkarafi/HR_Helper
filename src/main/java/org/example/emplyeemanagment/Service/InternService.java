package org.example.emplyeemanagment.Service;

import org.example.emplyeemanagment.Responses.StandardResponse;
import org.example.emplyeemanagment.dtos.InternDto;
import org.springframework.stereotype.Service;

@Service
public interface InternService {
    StandardResponse AddIntern(InternDto internDto) throws Exception;
    StandardResponse UpdateIntern(Long id , InternDto internDto) throws Exception;
    StandardResponse getAllInterns();
    StandardResponse DeleteIntern(Long id) throws Exception;
    StandardResponse findInternById(Long id);
    StandardResponse findInternBySupervisor(Long supervisorId);
}
