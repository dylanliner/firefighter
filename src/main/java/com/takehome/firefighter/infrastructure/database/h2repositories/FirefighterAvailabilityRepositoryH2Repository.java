package com.takehome.firefighter.infrastructure.database.h2repositories;

import com.takehome.firefighter.domain.persistence.FirefighterAvailabilityRepository;
import com.takehome.firefighter.infrastructure.database.entities.FirefighterAvailabilityEntity;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class FirefighterAvailabilityRepositoryH2Repository implements FirefighterAvailabilityRepository {

    private final FirefighterAvailabilityEntityH2Repository firefighterAvailabilityEntityH2Repository;

    private final FirefighterEntityH2Repository firefighterEntityH2Repository;

    public FirefighterAvailabilityRepositoryH2Repository(FirefighterAvailabilityEntityH2Repository firefighterAvailabilityEntityH2Repository, FirefighterEntityH2Repository firefighterEntityH2Repository) {
        this.firefighterAvailabilityEntityH2Repository = firefighterAvailabilityEntityH2Repository;
        this.firefighterEntityH2Repository = firefighterEntityH2Repository;
    }

    @Override
    public void updateFirefighterAvailability(UUID firefigherId, boolean availability) {
        var firefighter = firefighterEntityH2Repository.findById(firefigherId).orElseThrow();
        firefighterAvailabilityEntityH2Repository.save(new FirefighterAvailabilityEntity(UUID.randomUUID(), firefighter, availability));
    }
}
