package com.takehome.firefighter.infrastructure.database.h2repositories;

import com.takehome.firefighter.domain.model.FirefighterHistory;
import com.takehome.firefighter.domain.persistence.FirefighterHistoryRepository;
import com.takehome.firefighter.infrastructure.database.entities.FirefighterHistoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class FirefighterHistoryH2Repository implements FirefighterHistoryRepository {

    @Autowired
    private FirefighterHistoryEntityH2Repository firefighterHistoryEntityH2Repository;

    @Autowired
    private FirefighterEntityH2Repository firefighterEntityH2Repository;

    @Override
    public void updateFireFighterHistory(UUID currentFirefighterId, ZonedDateTime date) {
        var currentFirefighter = firefighterEntityH2Repository.findById(currentFirefighterId).orElseThrow();
        firefighterHistoryEntityH2Repository.save(FirefighterHistoryEntity.toEntity(currentFirefighter, date));
    }

    @Override
    public List<FirefighterHistory> findAllFirefighterHistory() {
        return firefighterHistoryEntityH2Repository.findAll().stream().map(FirefighterHistoryEntity::toDomain).collect(Collectors.toList());
    }

    public void deleteAll() {
        firefighterHistoryEntityH2Repository.deleteAll();
    }


}
