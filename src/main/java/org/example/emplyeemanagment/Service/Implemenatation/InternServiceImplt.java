package org.example.emplyeemanagment.Service.Implemenatation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Entities.Employee;
import org.example.emplyeemanagment.Entities.Intern;
import org.example.emplyeemanagment.Entities.Task;
import org.example.emplyeemanagment.Mappers.InternMapper;
import org.example.emplyeemanagment.Repository.EmployeeRepository;
import org.example.emplyeemanagment.Repository.InternRepository;
import org.example.emplyeemanagment.Repository.TaskRepository;
import org.example.emplyeemanagment.Responses.GenericResponse;
import org.example.emplyeemanagment.Service.InternService;
import org.example.emplyeemanagment.dtos.InternDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class InternServiceImplt implements InternService {
    private InternRepository internRepository;
    private EmployeeRepository employeeRepository;
    private TaskRepository taskRepository;




    @Override
    public GenericResponse AddIntern(InternDto internDto) throws Exception {
        Boolean InternExist = internRepository.existsByEmail(internDto.getEmail());
        if (InternExist) {
            return GenericResponse.builder()
                    .messageCode("403")
                    .ResponseMessage("Intern Already Exist")
                    .data(null)
                    .build();
        }
        Employee employee = employeeRepository.findById(internDto.getSupervisor()).orElseThrow(() -> new Exception("Supervisor Not found"));
        Task task = taskRepository.findById(internDto.getTask()).orElseThrow(()-> new Exception("Task Not found"));
        Intern intern = InternMapper.mapToIntern(internDto);
        intern.setSupervisor(employee);
        intern.setTask(task);
        internRepository.save(intern);
        return GenericResponse.builder()
                .messageCode("200")
                .ResponseMessage("Successfully Added Intern")
                .data(InternMapper.mapToInternDto(intern))
                .build();
    }






    @Override
    public GenericResponse UpdateIntern(Long id, InternDto internDto) throws Exception {
     Intern intern = internRepository.findById(id).orElseThrow(() -> new Exception("Intern Not found"));
        Employee employee = employeeRepository.findById(internDto.getSupervisor()).orElseThrow(() -> new Exception("Supervisor Not found"));
        Task task = taskRepository.findById(internDto.getTask()).orElseThrow(()-> new Exception("Task Not found"));
       intern.setId(intern.getId());
       intern.setFullName(internDto.getFullName());
       intern.setEmail(internDto.getEmail());
       intern.setPhone(internDto.getPhone());
       intern.setStartDate(internDto.getStartDate());
       intern.setEndDate(internDto.getEndDate());
       intern.setSchool(internDto.getSchool());
       intern.setSupervisor(employee);
       intern.setTask(task);
       internRepository.save(intern);
       return GenericResponse.builder()
               .messageCode("200")
               .ResponseMessage("Intern Updated Successfully")
               .data(InternMapper.mapToInternDto(intern))
               .build();
    }






    @Override
    public GenericResponse getAllInterns() {
        List<Intern> interns = internRepository.findAll();
        List<InternDto>internDtos = interns.stream().map(intern -> InternMapper.mapToInternDto(intern)).collect(Collectors.toList());
        return GenericResponse.builder()
                .messageCode("200")
                .ResponseMessage("Interns Found")
                .data(internDtos)
                .build();
    }




    @Override
    public GenericResponse DeleteIntern(Long id) throws Exception {
        Intern intern = internRepository.findById(id).orElseThrow(() -> new Exception("Intern Not found"));
        internRepository.delete(intern);
        return  GenericResponse.builder()
                .messageCode("200")
                .ResponseMessage("Intern Deleted Successfully")
                .build();
    }





    @Override
    public GenericResponse findInternById(Long id) {
        Optional<Intern> intern = internRepository.findById(id);
        if(intern.isEmpty()) {
            return GenericResponse.builder()
                    .messageCode("403")
                    .ResponseMessage("Intern Not Found")
                    .data(null)
                    .build();
        }
        Intern internFound  =  intern.get();
        return GenericResponse.builder()
                .messageCode("200")
                .ResponseMessage("Successfully Found Intern")
                .data(InternMapper.mapToInternDto(internFound))
                .build();
    }




    @Override
    public GenericResponse findInternBySupervisor(InternDto internDto) {
        return null;
    }
}
