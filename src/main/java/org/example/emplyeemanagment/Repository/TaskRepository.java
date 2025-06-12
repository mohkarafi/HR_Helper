package org.example.emplyeemanagment.Repository;

import org.example.emplyeemanagment.Entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findTaskByStatus(String status);
}
