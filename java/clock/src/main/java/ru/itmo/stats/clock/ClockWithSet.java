package ru.itmo.stats.clock;

import java.time.Instant;

public class ClockWithSet implements Clock {
    private Instant now;

    public ClockWithSet(final Instant now) {
        this.now = now;
    }

    @Override
    public Instant now() {
        return now;
    }

    public void setNow(final Instant now) {
        this.now = now;
    }
}
