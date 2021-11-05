package com.takehome.firefighter.domain.usecases;

import com.takehome.firefighter.domain.model.Firefighter;
import com.takehome.firefighter.domain.persistence.FirefightersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateFirefighterUseCase {

    private final FirefightersRepository firefightersRepository;

    public CreateFirefighterUseCase(FirefightersRepository firefightersRepository) {
        this.firefightersRepository = firefightersRepository;
    }

    @Transactional
    public void createFirefighter(Firefighter firefighter) {
        firefightersRepository.saveFirefighter(firefighter);
    }
}
