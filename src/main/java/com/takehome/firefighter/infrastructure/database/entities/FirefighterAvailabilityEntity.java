package com.takehome.firefighter.infrastructure.database.entities;

import java.util.UUID;

public class FirefighterAvailabilityEntity {

    private UUID id;

    private FirefighterEntity firefighter;

    private boolean availability;


    public FirefighterAvailabilityEntity(UUID id, FirefighterEntity firefighter, boolean availability) {
        this.id = id;
        this.firefighter = firefighter;
        this.availability = availability;
    }

    public FirefighterAvailabilityEntity() {
    }

}
