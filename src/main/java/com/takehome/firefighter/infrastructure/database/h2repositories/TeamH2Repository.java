package com.takehome.firefighter.infrastructure.database.h2repositories;

import com.takehome.firefighter.domain.model.Team;
import com.takehome.firefighter.domain.persistence.TeamRepository;
import com.takehome.firefighter.infrastructure.database.entities.TeamEntity;
import org.springframework.stereotype.Repository;

@Repository
public class TeamH2Repository implements TeamRepository {

    private final TeamEntityH2Repository teamEntityH2Repository;

    public TeamH2Repository(TeamEntityH2Repository teamEntityH2Repository) {
        this.teamEntityH2Repository = teamEntityH2Repository;
    }

    @Override
    public void save(Team team) {
        teamEntityH2Repository.save(TeamEntity.toEntity(team));
    }

    public void deleteAll() {
        teamEntityH2Repository.deleteAll();
    }
}
