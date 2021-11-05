package com.takehome.firefighter.domain.usecases;

import com.takehome.firefighter.domain.persistence.CurrentFirefighterRepository;
import com.takehome.firefighter.domain.persistence.FirefighterHistoryRepository;
import com.takehome.firefighter.domain.persistence.FirefightersRepository;
import com.takehome.firefighter.domain.model.Firefighter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Service
public class DesignateFirefighterUseCase {

    private final FirefightersRepository firefightersRepository;

    private final CurrentFirefighterRepository currentFirefighterRepository;

    private final FirefighterHistoryRepository firefighterHistoryRepository;

    public DesignateFirefighterUseCase(FirefightersRepository firefightersRepository, CurrentFirefighterRepository currentFirefighterRepository, FirefighterHistoryRepository firefighterHistoryRepository) {
        this.firefightersRepository = firefightersRepository;
        this.currentFirefighterRepository = currentFirefighterRepository;
        this.firefighterHistoryRepository = firefighterHistoryRepository;
    }

    @Transactional
    public Firefighter designateFirefighter() {

        Firefighter currentFirefighter = getFirefighter();

        currentFirefighterRepository.updateCurrentFirefighter(currentFirefighter.getId());

        firefighterHistoryRepository.updateFireFighterHistory(currentFirefighter.getId(), ZonedDateTime.now());

        return currentFirefighter;
    }

    private Firefighter getFirefighter() {
        var previousFirefighter = currentFirefighterRepository.findPreviousFirefighter();
        return previousFirefighter.flatMap(firefightersRepository::findNextFirefighterAlphabetically)
                .or(firefightersRepository::findFirstFirefighterAlphabetically).orElseThrow();
    }
}
