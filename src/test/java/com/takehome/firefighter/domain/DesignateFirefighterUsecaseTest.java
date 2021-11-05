package com.takehome.firefighter.domain;

import com.takehome.firefighter.domain.usecases.DesignateFirefighterUsecase;
import com.takehome.firefighter.domain.persistence.CurrentFirefighterRepository;
import com.takehome.firefighter.domain.persistence.FirefighterHistoryRepository;
import com.takehome.firefighter.domain.persistence.FirefightersRepository;
import com.takehome.firefighter.domain.model.Firefighter;
import com.takehome.firefighter.domain.model.Team;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DesignateFirefighterUsecaseTest {

    @Spy
    private FirefightersRepository firefightersRepository;

    @Spy
    private CurrentFirefighterRepository currentFirefighterRepository;

    @Spy
    private FirefighterHistoryRepository firefighterHistoryRepository;

    @Test
    public void shouldReturnCurrentFirefighterAndUpdateNextFirefighter() {
        //GIVEN
        var team1 = new Team(UUID.randomUUID(), "Core qualite");
        var team2 = new Team(UUID.randomUUID(), "Core qualite 2");
        var previousFirefighter = new Firefighter(UUID.randomUUID(), "name", team1, true);
        doReturn(Optional.of(previousFirefighter)).when(currentFirefighterRepository).findPreviousFirefighter();
        var nextFirefighter = new Firefighter(UUID.randomUUID(), "name2", team2, true);
        doReturn(Optional.of(nextFirefighter)).when(firefightersRepository).findNextFirefighterAlphabetically(previousFirefighter);

        var designatedFirefighterUsecase = new DesignateFirefighterUsecase(firefightersRepository, currentFirefighterRepository, firefighterHistoryRepository);

        //WHEN
        var actualFirefighter = designatedFirefighterUsecase.designateFirefighter();

        //THEN
        assertThat(actualFirefighter).isEqualTo(nextFirefighter);
        verify(currentFirefighterRepository, times(1)).updateCurrentFirefighter(nextFirefighter.getId());
        verify(firefighterHistoryRepository, times(1)).updateFireFighterHistory(eq(nextFirefighter.getId()), any(ZonedDateTime.class));
    }

    @Test
    public void shouldFindNextFirefighter_whenNoMoreFirefighterAlphabetically() {

        //GIVEN
        var team1 = new Team(UUID.randomUUID(), "Core qualite");
        var team2 = new Team(UUID.randomUUID(), "Core qualite 2");
        var previousFirefighter = new Firefighter(UUID.randomUUID(), "name", team1, true);
        doReturn(Optional.of(previousFirefighter)).when(currentFirefighterRepository).findPreviousFirefighter();
        var nextFirefighter = new Firefighter(UUID.randomUUID(), "name2", team2, true);
        doReturn(Optional.empty()).when(firefightersRepository).findNextFirefighterAlphabetically(previousFirefighter);
        doReturn(Optional.of(nextFirefighter)).when(firefightersRepository).findFirstFirefighterAlphabetically();

        var designatedFirefighterUsecase = new DesignateFirefighterUsecase(firefightersRepository, currentFirefighterRepository, firefighterHistoryRepository);

        //WHEN
        var actualFirefighter = designatedFirefighterUsecase.designateFirefighter();

        //THEN
        assertThat(actualFirefighter).isEqualTo(nextFirefighter);

    }

    @Test
    public void shouldFindNextFirefighter_whenNoCurrentFirefighter() {
        //GIVEN

        var team2 = new Team(UUID.randomUUID(), "Core qualite 2");
        doReturn(Optional.empty()).when(currentFirefighterRepository).findPreviousFirefighter();
        var nextFirefighter = new Firefighter(UUID.randomUUID(), "name2", team2, true);
        doReturn(Optional.of(nextFirefighter)).when(firefightersRepository).findFirstFirefighterAlphabetically();

        var designatedFirefighterUsecase = new DesignateFirefighterUsecase(firefightersRepository, currentFirefighterRepository, firefighterHistoryRepository);

        //WHEN
        var actualFirefighter = designatedFirefighterUsecase.designateFirefighter();

        //THEN
        assertThat(actualFirefighter).isEqualTo(nextFirefighter);

    }

}