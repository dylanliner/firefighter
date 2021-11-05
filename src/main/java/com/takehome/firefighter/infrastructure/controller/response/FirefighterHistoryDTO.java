package com.takehome.firefighter.infrastructure.controller.response;

import com.takehome.firefighter.domain.model.FirefighterHistory;

import java.time.ZonedDateTime;

public class FirefighterHistoryDTO {

    private ZonedDateTime date;

    private FirefighterDTO firefighter;

    public FirefighterHistoryDTO(ZonedDateTime date, FirefighterDTO firefighter) {
        this.date = date;
        this.firefighter = firefighter;
    }

    public FirefighterHistoryDTO() {
    }

    public static FirefighterHistoryDTO toDto(FirefighterHistory f) {
        return new FirefighterHistoryDTO(f.getDate(), FirefighterDTO.toDTO(f.getFirefighter()));
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public FirefighterDTO getFirefighter() {
        return firefighter;
    }
}
