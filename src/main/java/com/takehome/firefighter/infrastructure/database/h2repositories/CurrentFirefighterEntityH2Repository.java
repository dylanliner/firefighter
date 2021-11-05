package com.takehome.firefighter.infrastructure.database.h2repositories;

import com.takehome.firefighter.infrastructure.database.entities.CurrentFirefighterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CurrentFirefighterEntityH2Repository extends JpaRepository<CurrentFirefighterEntity, UUID> {

}
