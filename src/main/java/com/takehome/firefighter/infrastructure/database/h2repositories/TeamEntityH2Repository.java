package com.takehome.firefighter.infrastructure.database.h2repositories;

import com.takehome.firefighter.infrastructure.database.entities.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TeamEntityH2Repository extends JpaRepository<TeamEntity, UUID> {

}
