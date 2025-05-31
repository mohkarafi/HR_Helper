package org.example.emplyeemanagment;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.example.emplyeemanagment.Entities.Department;
import org.example.emplyeemanagment.Entities.Employee;
import org.example.emplyeemanagment.Repository.EmployeeRepository;
import org.example.emplyeemanagment.Service.EmployeeService;
import org.example.emplyeemanagment.dtos.EmployeeDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class EmplyeeManagmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmplyeeManagmentApplication.class, args);
    }

    CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository) {
        return args -> {
            String key = Encoders.BASE64.encode(Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded());
            System.out.println(key);
            /*EmployeeDto employee = EmployeeDto.builder()
                    .firstName("mohamed")
                    .lastName("karafi")
                    .email("karafimhd@gmaiol.com")
                    .phoneNumber("0666666666")
                    .hireDate(new Date())
                    .position("Tester")
                    .build();
            employeeService.SaveEmployee(employee);
            Employee employee = employeeRepository.findByEmail("simokrf02@gmail.com");
            System.out.println(employee != null ? "trouvé " : "nopn trouvé");
        Employee employee = Employee.builder().firstName("ahmed").build();
            Department department = Department.builder().name("IT").build();
            employeeRepository.save(employee);
            employee.setDepartmentId(department);
        };*/
        };
    }
}
