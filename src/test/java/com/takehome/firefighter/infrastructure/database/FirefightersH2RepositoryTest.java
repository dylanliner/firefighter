package com.takehome.firefighter.infrastructure.database;

import com.takehome.firefighter.domain.model.Firefighter;
import com.takehome.firefighter.domain.model.Team;
import com.takehome.firefighter.infrastructure.database.h2repositories.CurrentFirefighterH2Repository;
import com.takehome.firefighter.infrastructure.database.h2repositories.FirefighterHistoryH2Repository;
import com.takehome.firefighter.infrastructure.database.h2repositories.FirefightersH2Repository;
import com.takehome.firefighter.infrastructure.database.h2repositories.TeamH2Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class FirefightersH2RepositoryTest {

    @Autowired
    FirefightersH2Repository firefighterH2Repository;

    @Autowired
    TeamH2Repository teamH2Repository;

    @BeforeEach
    public void clean() {
        firefighterH2Repository.deleteAll();
        teamH2Repository.deleteAll();
    }

    @Test
    public void shouldFindNextFirefighter_whenOtherFirefightersWithDifferentNames() {
        //GIVEN
        var team1 = new Team(UUID.randomUUID(), "team");
        teamH2Repository.save(team1);

        var firefighter1 = new Firefighter(UUID.randomUUID(), "name", team1, true);
        var firefighter2 = new Firefighter(UUID.randomUUID(), "name1", team1, true);
        var firefighter3 = new Firefighter(UUID.randomUUID(), "name2", team1, true);
        firefighterH2Repository.saveFirefighter(firefighter2);
        firefighterH2Repository.saveFirefighter(firefighter1);
        firefighterH2Repository.saveFirefighter(firefighter3);

        //WHEN
        var actualFirefighter = firefighterH2Repository.findNextFirefighter(firefighter1);

        //THEN
        assertThat(actualFirefighter).isEqualTo(Optional.of(firefighter2));
    }

    @Test
    public void shouldFindNextFirefighter_whenNextFirefighterAlphabeticallyIsUnavailable() {
        //GIVEN
        var team1 = new Team(UUID.randomUUID(), "team");
        teamH2Repository.save(team1);

        var firefighter1 = new Firefighter(UUID.randomUUID(), "name", team1, true);
        var firefighter2 = new Firefighter(UUID.randomUUID(), "name1", team1, false);
        var firefighter3 = new Firefighter(UUID.randomUUID(), "name2", team1, true);
        firefighterH2Repository.saveFirefighter(firefighter2);
        firefighterH2Repository.saveFirefighter(firefighter1);
        firefighterH2Repository.saveFirefighter(firefighter3);

        //WHEN
        var actualFirefighter = firefighterH2Repository.findNextFirefighter(firefighter1);

        //THEN
        assertThat(actualFirefighter).isEqualTo(Optional.of(firefighter3));
    }


    @Test
    public void shouldFindNextFirefighter_whenOtherFirefightersWithSameNames() {
        //GIVEN
        var team1 = new Team(UUID.randomUUID(), "team");
        var team2 = new Team(UUID.randomUUID(), "team2");
        var team3 = new Team(UUID.randomUUID(), "team3");
        teamH2Repository.save(team1);
        teamH2Repository.save(team2);
        teamH2Repository.save(team3);
        var firefighter1 = new Firefighter(UUID.randomUUID(), "name", team1, true);
        var firefighter2 = new Firefighter(UUID.randomUUID(), "name", team2, true);
        var firefighter3 = new Firefighter(UUID.randomUUID(), "name", team3, true);
        firefighterH2Repository.saveFirefighter(firefighter2);
        firefighterH2Repository.saveFirefighter(firefighter1);
        firefighterH2Repository.saveFirefighter(firefighter3);

        //WHEN
        var actualFirefighter = firefighterH2Repository.findNextFirefighter(firefighter2);

        //THEN
        assertThat(actualFirefighter).isEqualTo(Optional.of(firefighter1));
    }

    @Test
    public void shouldFindNextFirefighter_whenFirefighterIsUnique() {
        //GIVEN
        var team1 = new Team(UUID.randomUUID(), "team");
        var firefighter1 = new Firefighter(UUID.randomUUID(), "name", team1, true);
        teamH2Repository.save(team1);
        firefighterH2Repository.saveFirefighter(firefighter1);

        //WHEN
        var actualFirefighter = firefighterH2Repository.findNextFirefighter(firefighter1);

        //THEN
        assertThat(actualFirefighter).isEqualTo(Optional.of(firefighter1));
    }

    @Test
    public void shouldFindNull_whenNoFirefighter() {
        //GIVEN
        var team1 = new Team(UUID.randomUUID(), "team");
        var firefighter1 = new Firefighter(UUID.randomUUID(), "name", team1, true);

        //WHEN
        var actualFirefighter = firefighterH2Repository.findNextFirefighter(firefighter1);

        //THEN
        assertThat(actualFirefighter).isEmpty();
    }

    @Test
    public void shouldFindNextFirefighter_whenNoMoreFirefighterAlphabetically() {
        //GIVEN
        var team1 = new Team(UUID.randomUUID(), "team");
        var firefighter1 = new Firefighter(UUID.randomUUID(), "name", team1, true);
        var firefighter2 = new Firefighter(UUID.randomUUID(), "name1", team1, true);
        var firefighter3 = new Firefighter(UUID.randomUUID(), "name2", team1, true);
        teamH2Repository.save(team1);
        firefighterH2Repository.saveFirefighter(firefighter1);
        firefighterH2Repository.saveFirefighter(firefighter2);
        firefighterH2Repository.saveFirefighter(firefighter3);

        //WHEN
        var actualFirefighter = firefighterH2Repository.findNextFirefighter(firefighter3);

        //THEN
        assertThat(actualFirefighter).isEqualTo(Optional.of(firefighter1));
    }

    @Test
    public void shoulSaveFirefighterWithTeam_whenTeamUnknown() {
        //GIVEN
        var team1 = new Team(UUID.randomUUID(), "team");
        var firefighter1 = new Firefighter(UUID.randomUUID(), "name", team1, true);

        //WHEN
        firefighterH2Repository.saveFirefighter(firefighter1);

        //THEN
        var actualFirefighters = firefighterH2Repository.findAll();
        assertThat(actualFirefighters).containsExactly(firefighter1);
    }

    @Test
    public void shoulUpdateAvailability() {
        //GIVEN
        var team1 = new Team(UUID.randomUUID(), "team");
        final UUID id = UUID.randomUUID();
        var firefighter1 = new Firefighter(id, "name", team1, true);
        firefighterH2Repository.saveFirefighter(firefighter1);

        //WHEN
        firefighterH2Repository.updateFirefighterAvailability(id, false);

        //THEN
        var actualFirefighters = firefighterH2Repository.findAll();
        var expected = new Firefighter(id, "name", team1, false);
        assertThat(actualFirefighters).containsExactly(expected);
    }

    @Test
    public void shoulSaveFirefighter_whenNoTeam() {
        //GIVEN
        var firefighter1 = new Firefighter(UUID.randomUUID(), "name", null, true);

        //WHEN
        firefighterH2Repository.saveFirefighter(firefighter1);

        //THEN
        var actualFirefighters = firefighterH2Repository.findAll();
        assertThat(actualFirefighters).containsExactly(firefighter1);
    }

    @Test
    public void shouldModifyFirefighterAndTeamOnSave_whenFirefighterAndTeamAlreadyExist() {
        //GIVEN
        final UUID teamId = UUID.randomUUID();
        var team1 = new Team(teamId, "team");
        var team2 = new Team(teamId, "team2");
        final UUID firefighterId = UUID.randomUUID();
        var firefighter1 = new Firefighter(firefighterId, "name", team1, true);
        var firefighter2 = new Firefighter(firefighterId, "name2", team2, true);
        teamH2Repository.save(team1);
        firefighterH2Repository.saveFirefighter(firefighter1);

        //WHEN
        firefighterH2Repository.saveFirefighter(firefighter2);

        //THEN
        var actualFirefighters = firefighterH2Repository.findAll();
        assertThat(actualFirefighters).containsExactly(firefighter2);
    }

}