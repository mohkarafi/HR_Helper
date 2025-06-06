package org.example.emplyeemanagment.Repository;

import org.example.emplyeemanagment.Entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByName(String name);
    Department findById(long id);
}
