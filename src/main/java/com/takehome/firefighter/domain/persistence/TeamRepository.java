package com.takehome.firefighter.domain.persistence;

import com.takehome.firefighter.domain.model.Team;

public interface TeamRepository {

    void save(Team team);

}
