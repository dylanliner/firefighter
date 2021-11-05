package com.takehome.firefighter.domain.model;

import com.sun.istack.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;
import java.util.UUID;

public class Firefighter {

    private UUID id;

    private String name;

    private Team team;


    public Firefighter(UUID id, String name, Team team) {
        this.id = Objects.requireNonNull(id);
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Firefighter that = (Firefighter) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(team, that.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, team);
    }
}
