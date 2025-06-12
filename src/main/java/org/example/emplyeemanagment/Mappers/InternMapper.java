package org.example.emplyeemanagment.Mappers;

import org.example.emplyeemanagment.Entities.Intern;
import org.example.emplyeemanagment.dtos.InternDto;

public class InternMapper {

    public static Intern mapToIntern(InternDto internDto) {
        Intern intern = Intern.builder()
                .fullName(internDto.getFullName())
                .email(internDto.getEmail())
                .phone(internDto.getPhone())
                .startDate(internDto.getStartDate())
                .endDate(internDto.getEndDate())
                .school(internDto.getSchool())
                .build();
        return intern;
    }
    public static InternDto mapToInternDto(Intern intern) {
        InternDto internDto = InternDto.builder()
                .fullName(intern.getFullName())
                .email(intern.getEmail())
                .phone(intern.getPhone())
                .school(intern.getSchool())
                .startDate(intern.getStartDate())
                .endDate(intern.getEndDate())
                .supervisorName(intern.getSupervisor() != null ?  intern.getSupervisor().getLastName() + " " + intern.getSupervisor().getFirstName() : null)
                .supervisor(intern.getSupervisor() != null ?  intern.getSupervisor().getId() : null)
                .task(intern.getTask() != null ? intern.getTask().getId() : null)
                .taskName(intern.getTask()!= null ? intern.getTask().getTitle():null)
                .build();
        return internDto;
    }
}
