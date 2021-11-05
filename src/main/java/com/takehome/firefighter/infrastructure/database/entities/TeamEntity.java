package com.takehome.firefighter.infrastructure.database.entities;

import com.takehome.firefighter.domain.model.Team;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "TEAM")
public class TeamEntity {

    @Id
    UUID id;

    String teamName;

    public TeamEntity() {
    }

    public TeamEntity(UUID id, String teamName) {
        this.id = id;
        this.teamName = teamName;
    }

    public static TeamEntity toEntity(Team team) {
        return new TeamEntity(team.getId(), team.getTeamName());

    }

    public Team toDomain() {
        return new Team(id, teamName);
    }
}
