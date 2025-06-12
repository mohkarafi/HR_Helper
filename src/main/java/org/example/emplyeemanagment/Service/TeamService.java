package org.example.emplyeemanagment.Service;

import org.example.emplyeemanagment.Entities.Team;
import org.example.emplyeemanagment.Responses.GenericResponse;
import org.example.emplyeemanagment.dtos.TeamDto;

public interface TeamService {
    GenericResponse addTeam(TeamDto teamDto);
    GenericResponse updateTeam( Long id , TeamDto teamDto) throws Exception;
    GenericResponse deleteTeam(Long id);
    GenericResponse getAllTeams();
    GenericResponse getTeamById(Long id) throws Exception;
}
