package org.example.emplyeemanagment.Controller;

import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Responses.StandardResponse;
import org.example.emplyeemanagment.Service.TeamService;
import org.example.emplyeemanagment.dtos.TeamDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Team")
@AllArgsConstructor
public class TeamController {
    private TeamService teamService;

    @PostMapping("add")
    public StandardResponse addTeam(@RequestBody TeamDto teamDto) {
        return teamService.addTeam(teamDto);
    }

    @GetMapping("findAll")
    public StandardResponse getAllTeams(@RequestParam(name = "page", defaultValue = "0")int page ,
                                        @RequestParam(name = "size", defaultValue = "2")int size  ) {
        return teamService.getAllTeams(page , size);
    }

    @GetMapping("findById/{id}")
    public StandardResponse getTeamById(@PathVariable Long id) throws Exception {
        return teamService.getTeamById(id);
    }

    @PutMapping("update/{id}")
    public StandardResponse updateTeam(@PathVariable Long id, @RequestBody TeamDto teamDto) throws Exception {
        return teamService.updateTeam(id, teamDto);
    }

    @DeleteMapping("delete/{id}")
    public StandardResponse deleteTeam(@PathVariable Long id) {
        return teamService.deleteTeam(id);
    }
}
