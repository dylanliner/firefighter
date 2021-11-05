package com.takehome.firefighter.domain.persistence;

import com.takehome.firefighter.domain.model.FirefighterHistory;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public interface FirefighterHistoryRepository {

    void updateFireFighterHistory(UUID currentFirefighter, ZonedDateTime date);

    List<FirefighterHistory> findAllFirefighterHistory();
}
