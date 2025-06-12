package org.example.emplyeemanagment.Mappers;

import org.example.emplyeemanagment.Entities.Team;
import org.example.emplyeemanagment.dtos.TeamDto;

public class TeamMapper {
    public static Team mapToTeam(TeamDto teamDto) {
        Team team = Team.builder()
                .name(teamDto.getTeamName())
                .description(teamDto.getTeamDescription())
                .build();
        return team;
    }
    public static TeamDto mapToTeamDto(Team team) {
        TeamDto teamDto =TeamDto.builder()
                .id(team.getId())
                .teamName(team.getName())
                .teamDescription(team.getDescription())
                .build();
        return teamDto;
    }
}
