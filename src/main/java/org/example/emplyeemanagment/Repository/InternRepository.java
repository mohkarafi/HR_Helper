package org.example.emplyeemanagment.Repository;

import org.example.emplyeemanagment.Entities.Intern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternRepository extends JpaRepository<Intern, Long> {
    Boolean existsByEmail(String email);
}
