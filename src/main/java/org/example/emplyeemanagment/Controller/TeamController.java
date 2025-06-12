package org.example.emplyeemanagment.Controller;

import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Responses.GenericResponse;
import org.example.emplyeemanagment.Service.TeamService;
import org.example.emplyeemanagment.dtos.TeamDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Team")
@AllArgsConstructor
public class TeamController {
    private TeamService teamService;
    @PostMapping("add")
    public GenericResponse addTeam(@RequestBody TeamDto teamDto){
        return teamService.addTeam(teamDto);
    }
    @GetMapping("findAll")
    public GenericResponse getAllTeams(){
        return teamService.getAllTeams();
    }
    @GetMapping("findById/{id}")
    public GenericResponse getTeamById(@PathVariable Long id) throws Exception {
        return teamService.getTeamById(id);
    }

    @PutMapping("update/{id}")
    public GenericResponse updateTeam(@PathVariable Long id , @RequestBody TeamDto teamDto) throws Exception {
        return teamService.updateTeam(id , teamDto);
    }
    @DeleteMapping("delete/{id}")
    public GenericResponse deleteTeam(@PathVariable Long id){
        return teamService.deleteTeam(id);
    }
}
