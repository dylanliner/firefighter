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

    private boolean available;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "TEAM_id")
    private TeamEntity team;

    public FirefighterEntity() {
    }


    private FirefighterEntity(UUID id, String name, TeamEntity team, boolean available) {
        this.id = id;
        this.name = name;
        this.team = team;
        this.available = available;
    }

    public static FirefighterEntity toEntity(Firefighter firefighter) {
        return new FirefighterEntity(firefighter.getId(),
                firefighter.getName(), firefighter.getTeam() == null ? null : TeamEntity.toEntity(firefighter.getTeam()),
                firefighter.isAvailable());
    }

    public Firefighter toDomain() {
        return new Firefighter(id, name, team == null ? null : team.toDomain(), available);
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}
