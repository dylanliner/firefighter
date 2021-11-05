package com.takehome.firefighter.infrastructure.controller.request;

import com.takehome.firefighter.domain.model.Firefighter;

import java.util.UUID;

public class FirefighterRequest {

    private UUID id;
    private String name;
    private TeamRequest team;

    public String getName() {
        return name;
    }

    public TeamRequest getTeam() {
        return team;
    }

    public UUID getId() {
        return id;
    }

    public Firefighter toDomain() {
        return new Firefighter(id == null ? UUID.randomUUID() : id, name, team == null ? null : team.toDomain());
    }
}
