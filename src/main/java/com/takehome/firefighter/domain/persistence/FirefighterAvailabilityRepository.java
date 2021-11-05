package com.takehome.firefighter.domain.persistence;

import java.util.UUID;

public interface FirefighterAvailabilityRepository {

    void updateFirefighterAvailability(UUID firefighterId, boolean availability);
}
