package org.example.emplyeemanagment.Service.Implemenatation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Entities.Team;
import org.example.emplyeemanagment.Mappers.TeamMapper;
import org.example.emplyeemanagment.Repository.TeamRepository;
import org.example.emplyeemanagment.Responses.GenericResponse;
import org.example.emplyeemanagment.Service.TeamService;
import org.example.emplyeemanagment.dtos.TeamDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class TeamServiceImplt implements TeamService {
    private TeamRepository teamRepository;

    @Override
    public GenericResponse addTeam(TeamDto teamDto) {
        Team team = TeamMapper.mapToTeam(teamDto);
        teamRepository.save(team);
        return GenericResponse.builder()
                .messageCode("200")
                .ResponseMessage("Team added successfully")
                .data(TeamMapper.mapToTeamDto(team))
                .build();
    }

    @Override
    public GenericResponse updateTeam(Long id, TeamDto teamDto) throws Exception {
        Team team = teamRepository.findById(id).orElseThrow(() -> new Exception("Team not found"));
        team.setName(teamDto.getTeamName());
        team.setDescription(teamDto.getTeamDescription());
        teamRepository.save(team);
        return GenericResponse.builder()
                .messageCode("200")
                .ResponseMessage("Team Updated successfully")
                .data(TeamMapper.mapToTeamDto(team))
                .build();
    }

    @Override
    public GenericResponse deleteTeam(Long id) {
        Optional<Team> team = teamRepository.findById(id);
        if (team.isEmpty()) {
            return GenericResponse.builder()
                    .messageCode("404")
                    .ResponseMessage("Team with this id does not exist")
                    .data(null).build();

        }
        teamRepository.delete(team.get());
        return GenericResponse.builder()
                .messageCode("200")
                .ResponseMessage("Team deleted successfully")
                .data(null)
                .build();
    }

    @Override
    public GenericResponse getAllTeams() {
        List<Team> teams = teamRepository.findAll();
        List<TeamDto> teamDtos = teams.stream().map(team -> TeamMapper.mapToTeamDto(team)).collect(Collectors.toList());
        return GenericResponse.builder()
                .messageCode("200")
                .ResponseMessage("Succes")
                .data(teamDtos)
                .build();
    }

    @Override
    public GenericResponse getTeamById(Long id) throws Exception {
        Optional<Team> team = teamRepository.findTeamById(id);
        if (team.isEmpty()) {
            return GenericResponse.builder()
                    .messageCode("404")
                    .ResponseMessage("Team with this id does not exist")
                    .data(null).build();

        }
        Team getTeam = team.get();
        return GenericResponse.builder()
                .messageCode("200")
                .ResponseMessage("Succes")
                .data(TeamMapper.mapToTeamDto(getTeam))
                .build();
    }
}
