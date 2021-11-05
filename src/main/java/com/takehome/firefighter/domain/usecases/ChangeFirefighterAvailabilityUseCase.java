package com.takehome.firefighter.domain.usecases;

import com.takehome.firefighter.domain.persistence.FirefighterAvailabilityRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ChangeFirefighterAvailabilityUseCase {

    private final FirefighterAvailabilityRepository firefighterAvailabilityRepository;

    public ChangeFirefighterAvailabilityUseCase(FirefighterAvailabilityRepository firefighterAvailabilityRepository) {
        this.firefighterAvailabilityRepository = firefighterAvailabilityRepository;
    }

    public void updateFirefighterAvailability(UUID firefighterId, boolean availability) {
        firefighterAvailabilityRepository.updateFirefighterAvailability(firefighterId, availability);
    }
}
