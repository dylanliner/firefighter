package com.takehome.firefighter.domain.model;

import java.time.ZonedDateTime;
import java.util.Objects;

public class FirefighterHistory {

    private Firefighter firefighter;

    private ZonedDateTime date;

    public FirefighterHistory(Firefighter firefighter, ZonedDateTime date) {
        this.firefighter = firefighter;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirefighterHistory that = (FirefighterHistory) o;
        return Objects.equals(firefighter, that.firefighter) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firefighter, date);
    }

    public Firefighter getFirefighter() {
        return firefighter;
    }

    public ZonedDateTime getDate() {
        return date;
    }
}
