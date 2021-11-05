package com.takehome.firefighter.infrastructure.controller.response;

import com.takehome.firefighter.domain.model.Firefighter;

import java.util.UUID;

public class FirefighterDTO {
    private UUID id;
    private String name;
    private TeamDTO team;

    public FirefighterDTO() {
    }

    public FirefighterDTO(UUID id, String name, TeamDTO team) {
        this.id = id;
        this.name = name;
        this.team = team;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TeamDTO getTeam() {
        return team;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
    }

    public static FirefighterDTO toDTO(Firefighter firefighter) {
        return new FirefighterDTO(firefighter.getId(), firefighter.getName(), firefighter.getTeam() == null ? null : TeamDTO.toDto(firefighter.getTeam()));
    }
}
