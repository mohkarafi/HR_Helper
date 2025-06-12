package org.example.emplyeemanagment.Repository;

import org.example.emplyeemanagment.Entities.Team;
import org.example.emplyeemanagment.dtos.TeamDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findTeamById(Long id);
    Team findTeamByName (String teamName);
}
