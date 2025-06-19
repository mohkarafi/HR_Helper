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
import org.example.emplyeemanagment.Responses.StandardResponse;
import org.example.emplyeemanagment.Service.NotificationService;
import org.example.emplyeemanagment.Service.InternService;
import org.example.emplyeemanagment.dtos.EmailDetails;
import org.example.emplyeemanagment.dtos.InternDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class InternServiceImpl implements InternService {
    private final InternRepository internRepository;
    private final EmployeeRepository employeeRepository;
    private final TaskRepository taskRepository;
    private final NotificationService emailService;

    @Override
    public StandardResponse AddIntern(InternDto internDto) throws Exception {
        Boolean InternExist = internRepository.existsByEmail(internDto.getEmail());
        if (InternExist) {
            return StandardResponse.builder()
                    .code("403")
                    .status("Intern Already Exist")
                    .data(null)
                    .build();
        }
        try {
            Employee employee = employeeRepository.findById(internDto.getSupervisor()).orElseThrow(() -> new Exception("Supervisor Not found"));
            Task task = taskRepository.findById(internDto.getTask()).orElseThrow(() -> new Exception("Task Not found"));
            Intern intern = InternMapper.mapToIntern(internDto);
            intern.setSupervisor(employee);
            intern.setTask(task);
            internRepository.save(intern);

            EmailDetails emailDetails = EmailDetails.builder().ReciverEmail(intern.getEmail())
                    .EmailSubject("Welcome to the Team! " + intern.getFullName() + "- internship Confirmation")
                    .EmailBody(
                            "Congratulations and welcome to our company!  \n" +
                            "We’re happy to have you on board as an intern.  \n" +
                            "Looking forward to what we’ll accomplish together!.")
                    .build();
            emailService.sendEmail(emailDetails);
            return StandardResponse.builder()
                    .code("200")
                    .status("Successfully Added Intern")
                    .data(InternMapper.mapToInternDto(intern))
                    .build();
        } catch (Exception e) {
            return StandardResponse.builder()
                    .code("500")
                    .status("error" + e.getMessage())
                    .data(null)
                    .build();
        }

    }


    @Override
    public StandardResponse UpdateIntern(Long id, InternDto internDto) throws Exception {
        Intern intern = internRepository.findById(id).orElseThrow(() -> new Exception("Intern Not found"));
        Employee employee = employeeRepository.findById(internDto.getSupervisor()).orElseThrow(() -> new Exception("Supervisor Not found"));
        Task task = taskRepository.findById(internDto.getTask()).orElseThrow(() -> new Exception("Task Not found"));
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
        return StandardResponse.builder()
                .code("200")
                .status("Intern Updated Successfully")
                .data(InternMapper.mapToInternDto(intern))
                .build();
    }


    @Override
    public StandardResponse getAllInterns(int page , int size) {
        Page<Intern> internPages = internRepository.findAll(PageRequest.of(page, size));
        List<InternDto> internDtos = internPages.getContent().stream().map(intern -> InternMapper.mapToInternDto(intern)).collect(Collectors.toList());
        return StandardResponse.builder()
                .code("200")
                .status("Interns Found")
                .data(internDtos)
                .build();
    }


    @Override
    public StandardResponse DeleteIntern(Long id) throws Exception {
        Intern intern = internRepository.findById(id).orElseThrow(() -> new Exception("Intern Not found"));
        internRepository.delete(intern);
        return StandardResponse.builder()
                .code("200")
                .status("Intern Deleted Successfully")
                .build();
    }


    @Override
    public StandardResponse findInternById(Long id) {
        Optional<Intern> intern = internRepository.findById(id);
        if (intern.isEmpty()) {
            return StandardResponse.builder()
                    .code("403")
                    .status("Intern Not Found")
                    .data(null)
                    .build();
        }
        Intern internFound = intern.get();
        return StandardResponse.builder()
                .code("200")
                .status("Successfully Found Intern")
                .data(InternMapper.mapToInternDto(internFound))
                .build();
    }


    @Override
    public StandardResponse findInternBySupervisor(Long supervisorId) {
        List<Intern> interns = internRepository.findBySupervisorId(supervisorId);
        List<InternDto> internDtos = interns.stream().map(intern -> InternMapper.mapToInternDto(intern)).collect(Collectors.toList());
        return StandardResponse.builder()
                .code("200")
                .status("Interns Found")
                .data(internDtos)
                .build();
    }
}
