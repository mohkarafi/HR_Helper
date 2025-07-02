package org.example.emplyeemanagment.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.example.emplyeemanagment.Responses.StandardResponse;
import org.example.emplyeemanagment.Service.TeamService;
import org.example.emplyeemanagment.dtos.TeamDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Team")
@AllArgsConstructor
@Tag(name = "Team", description = "Team management endpoints")
public class TeamController {
    private TeamService teamService;

    @Operation(summary = "Add a new team")
    @PostMapping("add")
    public StandardResponse addTeam(@RequestBody TeamDto teamDto) {
        return teamService.addTeam(teamDto);
    }

    @Operation(summary = "Get all teams with pagination")
    @GetMapping("findAll")
    public StandardResponse getAllTeams(@RequestParam(name = "page", defaultValue = "0") int page,
                                        @RequestParam(name = "size", defaultValue = "2") int size) {
        return teamService.getAllTeams(page, size);
    }

    @Operation(summary = "Get a team by ID")
    @GetMapping("findById/{id}")
    public StandardResponse getTeamById(@PathVariable Long id) throws Exception {
        return teamService.getTeamById(id);
    }

    @Operation(summary = "Update a team by ID")
    @PutMapping("update/{id}")
    public StandardResponse updateTeam(@PathVariable Long id, @RequestBody TeamDto teamDto) throws Exception {
        return teamService.updateTeam(id, teamDto);
    }

    @Operation(summary = "Delete a team by ID")
    @DeleteMapping("delete/{id}")
    public StandardResponse deleteTeam(@PathVariable Long id) {
        return teamService.deleteTeam(id);
    }
}