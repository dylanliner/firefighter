package com.takehome.firefighter.infrastructure.database.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
public class CurrentFirefighterEntity {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "FIREFIGHTER_id")
    private FirefighterEntity firefighter;


    public FirefighterEntity getFirefighter() {
        return firefighter;
    }

    public CurrentFirefighterEntity() {
    }

    public CurrentFirefighterEntity(UUID id, FirefighterEntity firefighter) {
        this.id = id;
        this.firefighter = firefighter;
    }
}
