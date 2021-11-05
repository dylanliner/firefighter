package com.takehome.firefighter.domain.persistence;

import com.takehome.firefighter.domain.model.Firefighter;

import java.util.Optional;
import java.util.UUID;

public interface FirefightersRepository {

    Optional<Firefighter> findNextFirefighter(Firefighter currentFirefighterName);

    Optional<Firefighter> findFirstFirefighterAlphabetically();

    void saveFirefighter(Firefighter firefighter);

    void updateFirefighterAvailability(UUID firefighterId, boolean available);
}
