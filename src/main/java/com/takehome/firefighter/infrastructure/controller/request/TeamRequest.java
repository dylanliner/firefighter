package com.takehome.firefighter.infrastructure.controller.request;

import com.takehome.firefighter.domain.model.Team;

import java.util.UUID;

public class TeamRequest {

    private final UUID id;
    private final String teamName;

    public TeamRequest(UUID id, String teamName) {
        this.id = id;
        this.teamName = teamName;
    }

    public UUID getId() {
        return id;
    }

    public String getTeamName() {
        return teamName;
    }

    public Team toDomain() {
        return new Team(id == null ? UUID.randomUUID() : id, teamName);
    }
}
