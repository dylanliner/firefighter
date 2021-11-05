package com.takehome.firefighter.domain.persistence;

import com.takehome.firefighter.domain.model.Team;

import java.util.UUID;

public interface TeamRepository {

    void save(Team team);

    Team findById(UUID teamId);
}
