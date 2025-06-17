package org.example.emplyeemanagment.Service.Implemenatation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Entities.Team;
import org.example.emplyeemanagment.Mappers.TeamMapper;
import org.example.emplyeemanagment.Repository.TeamRepository;
import org.example.emplyeemanagment.Responses.StandardResponse;
import org.example.emplyeemanagment.Service.TeamService;
import org.example.emplyeemanagment.dtos.TeamDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    @Override
    public StandardResponse addTeam(TeamDto teamDto) {
        try {
            Team team = TeamMapper.mapToTeam(teamDto);
            teamRepository.save(team);
            return StandardResponse.builder()
                    .code("200")
                    .status("Team added successfully")
                    .data(TeamMapper.mapToTeamDto(team))
                    .build();
        } catch (Exception e) {
            return StandardResponse.builder()
                    .code("500")
                    .status("Error adding Team: " + e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @Override
    public StandardResponse updateTeam(Long id, TeamDto teamDto) throws Exception {
        try {
            Team team = teamRepository.findById(id).orElseThrow(() -> new Exception("Team not found"));
            team.setName(teamDto.getTeamName());
            team.setDescription(teamDto.getTeamDescription());
            teamRepository.save(team);
            return StandardResponse.builder()
                    .code("200")
                    .status("Team Updated successfully")
                    .data(TeamMapper.mapToTeamDto(team))
                    .build();

        } catch (Exception e) {
            return StandardResponse.builder()
                    .code("500")
                    .status("Error Updating Team: " + e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @Override
    public StandardResponse deleteTeam(Long id) {
        Optional<Team> team = teamRepository.findById(id);
        if (team.isEmpty()) {
            return StandardResponse.builder()
                    .code("404")
                    .status("Team with this id does not exist")
                    .data(null).build();

        }
        teamRepository.delete(team.get());
        return StandardResponse.builder()
                .code("200")
                .status("Team deleted successfully")
                .data(null)
                .build();
    }

    @Override
    public StandardResponse getAllTeams() {
        List<Team> teams = teamRepository.findAll();
        List<TeamDto> teamDtos = teams.stream().map(team -> TeamMapper.mapToTeamDto(team)).collect(Collectors.toList());
        return StandardResponse.builder()
                .code("200")
                .status("Success")
                .data(teamDtos)
                .build();
    }

    @Override
    public StandardResponse getTeamById(Long id) throws Exception {
        Optional<Team> team = teamRepository.findTeamById(id);
        if (team.isEmpty()) {
            return StandardResponse.builder()
                    .code("404")
                    .status("Team with this id does not exist")
                    .data(null).build();

        }
        Team getTeam = team.get();
        return StandardResponse.builder()
                .code("200")
                .status("Success")
                .data(TeamMapper.mapToTeamDto(getTeam))
                .build();
    }
}
