package com.takehome.firefighter.domain.usecases;

import com.takehome.firefighter.domain.persistence.FirefightersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ChangeFirefighterAvailabilityUseCase {

    private final FirefightersRepository firefightersRepository;

    public ChangeFirefighterAvailabilityUseCase(FirefightersRepository firefightersRepository) {
        this.firefightersRepository = firefightersRepository;
    }

    @Transactional
    public void updateFirefighterAvailability(UUID firefighterId, boolean available) {
        firefightersRepository.updateFirefighterAvailability(firefighterId, available);
    }
}
