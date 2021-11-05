package com.takehome.firefighter.infrastructure.database.entities;

import com.takehome.firefighter.domain.model.Firefighter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "FIREFIGHTER")
public class FirefighterEntity {

    @Id
    private UUID id;

    private String name;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "TEAM_id")
    private TeamEntity team;


    public static FirefighterEntity toEntity(Firefighter firefighter) {
        return new FirefighterEntity(firefighter.getId(), firefighter.getName(), firefighter.getTeam() == null ? null : TeamEntity.toEntity(firefighter.getTeam()));
    }

    public Firefighter toDomain() {
        return new Firefighter(id, name, team == null ? null : team.toDomain());
    }


    public FirefighterEntity(UUID id, String name, TeamEntity team) {
        this.id = id;
        this.name = name;
        this.team = team;
    }

    public FirefighterEntity() {
    }
}
