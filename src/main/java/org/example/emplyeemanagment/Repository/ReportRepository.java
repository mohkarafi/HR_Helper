package org.example.emplyeemanagment.Repository;

import org.example.emplyeemanagment.Entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report , Long> {
    @Query("SELECT r FROM Report r WHERE r.intern.id = :internId")
    List<Report> findByInternId(@Param("internId") Long internId);
}
