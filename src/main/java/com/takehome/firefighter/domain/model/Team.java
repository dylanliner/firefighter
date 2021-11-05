package com.takehome.firefighter.domain.model;

import org.apache.logging.log4j.spi.ObjectThreadContextMap;

import java.util.Objects;
import java.util.UUID;

public class Team {

    private UUID id;
    private String teamName;

    public Team(UUID id, String teamName) {
        this.id = Objects.requireNonNull(id);
        this.teamName = teamName;
    }

    public UUID getId() {
        return id;
    }

    public String getTeamName() {
        return teamName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(id, team.id) && Objects.equals(teamName, team.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, teamName);
    }
}
