package com.takehome.firefighter.infrastructure.database.entities;

import com.takehome.firefighter.domain.model.FirefighterHistory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
public class FirefighterHistoryEntity {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "FIREFIGHTER_id")
    private FirefighterEntity firefighter;

    private ZonedDateTime date;

    public FirefighterHistoryEntity(UUID id, FirefighterEntity firefighter, ZonedDateTime date) {
        this.id = id;
        this.firefighter = firefighter;
        this.date = date;
    }

    public FirefighterHistoryEntity() {
    }

    public static FirefighterHistoryEntity toEntity(FirefighterEntity currentFirefighter, ZonedDateTime date) {
        return new FirefighterHistoryEntity(UUID.randomUUID(), currentFirefighter, date);
    }

    public FirefighterHistory toDomain() {
        return new FirefighterHistory(firefighter.toDomain(), date);
    }
}
