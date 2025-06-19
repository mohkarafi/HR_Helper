package org.example.emplyeemanagment.Repository;

import org.example.emplyeemanagment.Entities.Report;
import org.example.emplyeemanagment.Entities.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report , Long> {
    Page<Report> findAll(Pageable pageable);
    @Query("SELECT r FROM Report r WHERE r.intern.id = :internId")
    List<Report> findByInternId(@Param("internId") Long internId);
}
