package org.example.emplyeemanagment.Repository;

import org.example.emplyeemanagment.Entities.Department;
import org.example.emplyeemanagment.Entities.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Page<Department> findAll(Pageable pageable);
    Boolean existsByName(String name);
    Department findByName(String name);
    Department findById(long id);
}
