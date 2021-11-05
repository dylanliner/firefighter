package com.takehome.firefighter.infrastructure.database.h2repositories;

import com.takehome.firefighter.infrastructure.database.entities.FirefighterAvailabilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FirefighterAvailabilityEntityH2Repository extends JpaRepository<FirefighterAvailabilityEntity, UUID> {
}
