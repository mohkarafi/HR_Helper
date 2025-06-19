package org.example.emplyeemanagment.Repository;

import org.example.emplyeemanagment.Entities.Team;
import org.example.emplyeemanagment.dtos.TeamDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.web.PageableArgumentResolver;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Page<Team> findAll(Pageable pageable);
    Optional<Team> findTeamById(Long id);
    Team findTeamByName (String teamName);
}
