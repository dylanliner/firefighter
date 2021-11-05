package com.takehome.firefighter.infrastructure.database.h2repositories;

import com.takehome.firefighter.domain.model.Firefighter;
import com.takehome.firefighter.domain.persistence.FirefightersRepository;
import com.takehome.firefighter.infrastructure.database.entities.FirefighterEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class FirefightersH2Repository implements FirefightersRepository {

    private final FirefighterEntityH2Repository firefighterEntityH2Repository;

    public FirefightersH2Repository(FirefighterEntityH2Repository firefighterEntityH2Repository) {
        this.firefighterEntityH2Repository = firefighterEntityH2Repository;
    }


    @Override
    public Optional<Firefighter> findNextFirefighter(Firefighter currentFirefighterName) {
        return firefighterEntityH2Repository.findNextFirefighter(currentFirefighterName.getId(), currentFirefighterName.getName())
                .or(firefighterEntityH2Repository::findFirstFirefighterAlphabetically).map(FirefighterEntity::toDomain);
    }

    @Override
    public Optional<Firefighter> findFirstFirefighterAlphabetically() {
        return firefighterEntityH2Repository.findFirstFirefighterAlphabetically().map(FirefighterEntity::toDomain);
    }

    @Override
    public void saveFirefighter(Firefighter firefighter) {
        firefighterEntityH2Repository.save(FirefighterEntity.toEntity(firefighter));
    }

    @Override
    public void updateFirefighterAvailability(UUID firefighterId, boolean available) {
        var firefighter = firefighterEntityH2Repository.findById(firefighterId).orElseThrow();
        firefighter.setAvailable(available);
    }


    public void deleteAll() {
        firefighterEntityH2Repository.deleteAll();
    }

    public List<Firefighter> findAll() {
        return firefighterEntityH2Repository.findAll().stream().map(FirefighterEntity::toDomain)
                .collect(Collectors.toList());
    }
}
