package org.example.emplyeemanagment.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TeamDto {
    private Long id;
    @NotBlank(message = "Team name must not be blank")
    @Size(max = 100, message = "Team name must be at most 100 characters")
    private String teamName;

    @NotBlank(message = "Team description must not be blank")
    @Size(max = 500, message = "Team description must be at most 500 characters")
    private String teamDescription;
}
