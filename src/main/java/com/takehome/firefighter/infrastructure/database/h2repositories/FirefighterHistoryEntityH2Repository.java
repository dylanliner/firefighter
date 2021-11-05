package com.takehome.firefighter.infrastructure.database.h2repositories;

import com.takehome.firefighter.infrastructure.database.entities.FirefighterHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FirefighterHistoryEntityH2Repository extends JpaRepository<FirefighterHistoryEntity, UUID> {

}
