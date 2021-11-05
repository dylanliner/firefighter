package com.takehome.firefighter.infrastructure.controller;

import com.takehome.firefighter.domain.model.Firefighter;
import com.takehome.firefighter.domain.model.FirefighterHistory;
import com.takehome.firefighter.domain.model.Team;
import com.takehome.firefighter.domain.usecases.CreateFirefighterUseCase;
import com.takehome.firefighter.domain.usecases.ChangeFirefighterAvailabilityUseCase;
import com.takehome.firefighter.domain.usecases.DesignateFirefighterUsecase;
import com.takehome.firefighter.domain.usecases.QueryFirefighterHistoryUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@WebMvcTest
class FirefighterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DesignateFirefighterUsecase designateFirefighterUsecase;

    @MockBean
    private QueryFirefighterHistoryUseCase queryFirefighterHistoryUseCase;

    @MockBean
    private ChangeFirefighterAvailabilityUseCase deactivateFirefighterUseCase;

    @MockBean
    private CreateFirefighterUseCase createFirefighterUseCase;

    @Test
    public void shouldReturnDesignatedFirefighter() throws Exception {
        var team1 = new Team(UUID.fromString("16047f1e-e7e1-3c1d-b416-1d8d090f9df8"), "Core qualite");
        var firefighter = new Firefighter(UUID.fromString("16047f1e-e7e1-3c1d-b416-1d8d090f9df8"), "Fanch", team1);
        when(designateFirefighterUsecase.designateFirefighter()).thenReturn(firefighter);
        this.mockMvc.perform(post("/firefighter/designateFirefighter")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(("{\n" +
                        "    \"id\": \"16047f1e-e7e1-3c1d-b416-1d8d090f9df8\",\n" +
                        "    \"name\": \"Fanch\",\n" +
                        "    \"team\": {\n" +
                        "        \"id\": \"16047f1e-e7e1-3c1d-b416-1d8d090f9df8\",\n" +
                        "        \"teamName\": \"Core qualite\"\n" +
                        "    }\n" +
                        "}")));
    }

    @Test
    public void shouldReturnDesignatedFirefighter_withNoTeam() throws Exception {
        var firefighter = new Firefighter(UUID.fromString("16047f1e-e7e1-3c1d-b416-1d8d090f9df8"), "Fanch", null);
        when(designateFirefighterUsecase.designateFirefighter()).thenReturn(firefighter);
        this.mockMvc.perform(post("/firefighter/designateFirefighter")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(("{\n" +
                        "    \"id\": \"16047f1e-e7e1-3c1d-b416-1d8d090f9df8\",\n" +
                        "    \"name\": \"Fanch\"" +
                        "}")));
    }


    @Test
    public void shouldReturnFirefighterHistory() throws Exception {
        var team1 = new Team(UUID.fromString("16047f1e-e7e1-3c1d-b416-1d8d090f9df8"), "Core qualite");
        var firefighter1 = new Firefighter(UUID.fromString("16047f1e-e7e1-3c1d-b416-1d8d090f9df8"), "Fanch", team1);
        var firefighter2 = new Firefighter(UUID.fromString("16047f1e-e7e1-3c1d-b416-1d8d090f9df8"), "Fanch", team1);
        var date1 = ZonedDateTime.parse("2021-11-04T18:07:28.932099+01:00");
        var date2 = ZonedDateTime.parse("2021-11-04T18:07:28.932099+01:00");
        var firefighterHistory1 = new FirefighterHistory(firefighter1, date1);
        var firefighterHistory2 = new FirefighterHistory(firefighter2, date2);
        when(queryFirefighterHistoryUseCase.findAllFirefighterHistory()).thenReturn(List.of(firefighterHistory1, firefighterHistory2));
        this.mockMvc.perform(get("/firefighter")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(("[\n" +
                        "    {\n" +
                        "        \"date\": \"2021-11-04T18:07:28.932099+01:00\",\n" +
                        "        \"firefighter\": {\n" +
                        "            \"id\": \"16047f1e-e7e1-3c1d-b416-1d8d090f9df8\",\n" +
                        "            \"name\": \"Fanch\",\n" +
                        "            \"team\": {\n" +
                        "                \"id\": \"16047f1e-e7e1-3c1d-b416-1d8d090f9df8\",\n" +
                        "                \"teamName\": \"Core qualite\"\n" +
                        "            }\n" +
                        "        }\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"date\": \"2021-11-04T18:07:28.932099+01:00\",\n" +
                        "        \"firefighter\": {\n" +
                        "            \"id\": \"16047f1e-e7e1-3c1d-b416-1d8d090f9df8\",\n" +
                        "            \"name\": \"Fanch\",\n" +
                        "            \"team\": {\n" +
                        "                \"id\": \"16047f1e-e7e1-3c1d-b416-1d8d090f9df8\",\n" +
                        "                \"teamName\": \"Core qualite\"\n" +
                        "            }\n" +
                        "        }\n" +
                        "    }\n" +
                        "]")));
    }

    @Test
    public void shouldDeleteFirefighter() throws Exception {
        var firefighterId = UUID.randomUUID();
        this.mockMvc.perform(post("/firefighter/" + firefighterId)).andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    public void shouldSaveFirefighter() throws Exception {
        this.mockMvc.perform(post("/firefighter").content("{\n" +
                "    \"name\": \"Fanch\",\n" +
                "    \"team\": {\n" +
                "        \"teamName\": \"Core qualite\"\n" +
                "    }\n" +
                "}").contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    public void shouldSaveFirefighter_withNoTeam() throws Exception {
        this.mockMvc.perform(post("/firefighter").content("{\n" +
                "    \"name\": \"Fanch\"  " +
                "}").contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isNoContent());
    }

}