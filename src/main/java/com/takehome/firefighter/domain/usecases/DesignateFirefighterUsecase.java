package com.takehome.firefighter.domain.usecases;

import com.takehome.firefighter.domain.persistence.CurrentFirefighterRepository;
import com.takehome.firefighter.domain.persistence.FirefighterHistoryRepository;
import com.takehome.firefighter.domain.persistence.FirefightersRepository;
import com.takehome.firefighter.domain.model.Firefighter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Service
public class DesignateFirefighterUsecase {

    private final FirefightersRepository firefightersRepository;

    private final CurrentFirefighterRepository currentFirefighterRepository;

    private final FirefighterHistoryRepository firefighterHistoryRepository;

    public DesignateFirefighterUsecase(FirefightersRepository firefightersRepository, CurrentFirefighterRepository currentFirefighterRepository, FirefighterHistoryRepository firefighterHistoryRepository) {
        this.firefightersRepository = firefightersRepository;
        this.currentFirefighterRepository = currentFirefighterRepository;
        this.firefighterHistoryRepository = firefighterHistoryRepository;
    }

    @Transactional
    public Firefighter designateFirefighter() {

        var preiousFirefighter = currentFirefighterRepository.findPreviousFirefighter();


        var currentFirefighter = preiousFirefighter.isPresent() ?
                firefightersRepository.findNextFirefighter(preiousFirefighter.get()).orElseThrow() :
                firefightersRepository.findFirstFirefighterAlphabetically().orElseThrow();

        currentFirefighterRepository.updateCurrentFirefighter(currentFirefighter.getId());

        firefighterHistoryRepository.updateFireFighterHistory(currentFirefighter.getId(), ZonedDateTime.now());

        return currentFirefighter;
    }
}
