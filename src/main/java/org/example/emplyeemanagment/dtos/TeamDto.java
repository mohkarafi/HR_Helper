package org.example.emplyeemanagment.dtos;

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
    private String teamName;
    private String teamDescription;
}
