package com.takehome.firefighter.infrastructure.database.h2repositories;

import com.takehome.firefighter.domain.model.Firefighter;
import com.takehome.firefighter.domain.persistence.CurrentFirefighterRepository;
import com.takehome.firefighter.infrastructure.database.entities.CurrentFirefighterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class CurrentFirefighterH2Repository implements CurrentFirefighterRepository {

    @Autowired
    private final CurrentFirefighterEntityH2Repository currentFirefighterEntityH2Repository;

    @Autowired
    private final FirefighterEntityH2Repository firefighterEntityH2Repository;

    public CurrentFirefighterH2Repository(CurrentFirefighterEntityH2Repository currentFirefighterEntityH2Repository, FirefighterEntityH2Repository firefighterEntityH2Repository) {
        this.currentFirefighterEntityH2Repository = currentFirefighterEntityH2Repository;
        this.firefighterEntityH2Repository = firefighterEntityH2Repository;
    }


    @Override
    public Optional<Firefighter> findPreviousFirefighter() {
        return currentFirefighterEntityH2Repository.findAll().stream().map(i -> i.getFirefighter().toDomain()).findAny();
    }

    @Override
    public void updateCurrentFirefighter(UUID currentFirefighterId) {
        deleteAll();
        var currentFirefighter = firefighterEntityH2Repository.findById(currentFirefighterId).orElseThrow();
        currentFirefighterEntityH2Repository.save(new CurrentFirefighterEntity(UUID.randomUUID(), currentFirefighter));
    }

    public void deleteAll() {
        currentFirefighterEntityH2Repository.deleteAll();
    }
}
