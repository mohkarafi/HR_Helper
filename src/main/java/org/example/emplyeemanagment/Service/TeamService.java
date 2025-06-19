package org.example.emplyeemanagment.Service;

import org.example.emplyeemanagment.Responses.StandardResponse;
import org.example.emplyeemanagment.dtos.TeamDto;

public interface TeamService {
    StandardResponse addTeam(TeamDto teamDto);
    StandardResponse updateTeam(Long id , TeamDto teamDto) throws Exception;
    StandardResponse deleteTeam(Long id);
    StandardResponse getAllTeams(int page , int size);
    StandardResponse getTeamById(Long id) throws Exception;
}
