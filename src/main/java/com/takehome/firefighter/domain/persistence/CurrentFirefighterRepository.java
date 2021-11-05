package com.takehome.firefighter.domain.persistence;

import com.takehome.firefighter.domain.model.Firefighter;

import java.util.Optional;
import java.util.UUID;

public interface CurrentFirefighterRepository {

    Optional<Firefighter> findPreviousFirefighter();

    void updateCurrentFirefighter(UUID currentFirefighterId);
}
