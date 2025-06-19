package org.example.emplyeemanagment.Repository;

import org.example.emplyeemanagment.Entities.Intern;
import org.example.emplyeemanagment.Entities.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternRepository extends JpaRepository<Intern, Long> {
    Page<Intern> findAll(Pageable pageable);
    Boolean existsByEmail(String email);
    @Query("SELECT i FROM Intern i WHERE i.supervisor.id = :supervisorId")
    List<Intern> findBySupervisorId(@Param("supervisorId") Long supervisorId);
}
