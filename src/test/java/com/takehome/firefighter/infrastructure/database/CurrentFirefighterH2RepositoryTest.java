package com.takehome.firefighter.infrastructure.database;

import com.takehome.firefighter.domain.model.Firefighter;
import com.takehome.firefighter.domain.model.Team;
import com.takehome.firefighter.infrastructure.database.h2repositories.CurrentFirefighterH2Repository;
import com.takehome.firefighter.infrastructure.database.h2repositories.FirefightersH2Repository;
import com.takehome.firefighter.infrastructure.database.h2repositories.TeamH2Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class CurrentFirefighterH2RepositoryTest {

    @Autowired
    CurrentFirefighterH2Repository currentFirefighterH2Repository;

    @Autowired
    FirefightersH2Repository firefightersH2Repository;

    @Autowired
    TeamH2Repository teamH2Repository;

    @BeforeEach
    public void clean(){
        firefightersH2Repository.deleteAll();
        currentFirefighterH2Repository.deleteAll();
        teamH2Repository.deleteAll();
    }

    @Test
    public void shouldUpdateFireFighterHistory() {
        //GIVEN
        var id = UUID.randomUUID();
        var team1 = new Team(UUID.randomUUID(), "Core qualite");
        var expectedFirefighter = new Firefighter(id, "name", team1, true);
        teamH2Repository.save(team1);
        firefightersH2Repository.saveFirefighter(expectedFirefighter);

        //WHEN
        currentFirefighterH2Repository.updateCurrentFirefighter(expectedFirefighter.getId());

        //THEN
        var previousFirefighter =  currentFirefighterH2Repository.findPreviousFirefighter();
        assertThat(previousFirefighter).isEqualTo(Optional.of(expectedFirefighter));
    }

    @Test
    public void shouldReturnNull_WhenNoPreviousFirefighter() {

        //WHEN
        var previousFirefighter =  currentFirefighterH2Repository.findPreviousFirefighter();

        //THEN
        assertThat(previousFirefighter).isEmpty();
    }

}