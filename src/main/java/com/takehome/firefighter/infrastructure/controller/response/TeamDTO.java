package com.takehome.firefighter.infrastructure.controller.response;

import com.takehome.firefighter.domain.model.Team;

import java.util.UUID;

public class TeamDTO {

    private final UUID id;
    private final String teamName;

    public TeamDTO(UUID id, String teamName) {
        this.id = id;
        this.teamName = teamName;
    }

    public UUID getId() {
        return id;
    }

    public String getTeamName() {
        return teamName;
    }

    public static TeamDTO toDto(Team team) {
        return new TeamDTO(team.getId(), team.getTeamName());
    }
}
