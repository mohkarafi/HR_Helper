package org.example.emplyeemanagment.Repository;

import org.example.emplyeemanagment.Entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Boolean existsByEmail(String email);

    Employee findEmployeeByEmail(String email);

    Page<Employee> findAll(Pageable pageable);
}
