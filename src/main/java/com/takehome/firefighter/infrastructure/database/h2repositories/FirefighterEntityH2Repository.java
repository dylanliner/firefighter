package com.takehome.firefighter.infrastructure.database.h2repositories;

import com.takehome.firefighter.infrastructure.database.entities.FirefighterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface FirefighterEntityH2Repository extends JpaRepository<FirefighterEntity, UUID> {


    @Query(value = "select * from FIREFIGHTER f where f.name >= ?2 and f.id != ?1 and f.available is true order by f.name ASC limit 1", nativeQuery = true)
    Optional<FirefighterEntity> findNextFirefighter(UUID currentFirefighterid, String currentFirefighterName);

    @Query(value = "select * from FIREFIGHTER f where f.available is true order by f.name ASC limit 1", nativeQuery = true)
    Optional<FirefighterEntity> findFirstFirefighterAlphabetically();
}
