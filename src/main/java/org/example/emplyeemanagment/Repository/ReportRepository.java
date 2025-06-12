package org.example.emplyeemanagment.Repository;

import org.example.emplyeemanagment.Entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report , Long> {
}
