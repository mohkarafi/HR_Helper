package org.example.emplyeemanagment.Repository;

import org.example.emplyeemanagment.Entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findTaskByStatus(String status);
    @Query("select t From Task t where t.employee.id=:employeeId")
    List<Task> findTasksByEmployeeId(@Param("employeeId") Long employeeId);
}
