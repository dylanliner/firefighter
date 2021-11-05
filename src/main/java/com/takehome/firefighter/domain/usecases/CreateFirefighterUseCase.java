package com.takehome.firefighter.domain.usecases;

import com.takehome.firefighter.domain.persistence.FirefightersRepository;
import com.takehome.firefighter.domain.model.Firefighter;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
public class CreateFirefighterUseCase {

    private final FirefightersRepository firefightersRepository;

    public CreateFirefighterUseCase(FirefightersRepository firefightersRepository) {
        this.firefightersRepository = firefightersRepository;
    }

    public void createFirefighter(Firefighter firefighter) {
        firefightersRepository.saveFirefighter(firefighter);
    }
}
