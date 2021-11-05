package com.takehome.firefighter.infrastructure.database;

import com.takehome.firefighter.domain.model.Firefighter;
import com.takehome.firefighter.domain.model.FirefighterHistory;
import com.takehome.firefighter.domain.model.Team;
import com.takehome.firefighter.infrastructure.database.h2repositories.FirefighterHistoryH2Repository;
import com.takehome.firefighter.infrastructure.database.h2repositories.FirefightersH2Repository;
import com.takehome.firefighter.infrastructure.database.h2repositories.TeamH2Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.time.ZonedDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class FirefighterHistoryH2RepositoryTest {

    @Autowired
    FirefighterHistoryH2Repository firefighterHistoryH2Repository;

    @Autowired
    FirefightersH2Repository firefightersH2Repository;

    @Autowired
    TeamH2Repository teamH2Repository;

    @BeforeEach
    public void clean(){
        firefightersH2Repository.deleteAll();
        firefighterHistoryH2Repository.deleteAll();
        teamH2Repository.deleteAll();
    }

    @Test
    public void shouldUpdateFireFighterHistory() {
        //GIVEN
        var team1 = new Team(UUID.randomUUID(), "Core qualite");
        var id = UUID.randomUUID();
        var firefighter = new Firefighter(id, "name", team1);
        var date = ZonedDateTime.now();
        teamH2Repository.save(team1);
        firefightersH2Repository.saveFirefighter(firefighter);

        //WHEN
        firefighterHistoryH2Repository.updateFireFighterHistory(id, date);

        //THEN
        var fireFighterHistoryList = firefighterHistoryH2Repository.findAllFirefighterHistory();
        var expectedFirefighterHistory = new FirefighterHistory(firefighter, date);
        assertThat(fireFighterHistoryList).containsExactly(expectedFirefighterHistory);
    }

}