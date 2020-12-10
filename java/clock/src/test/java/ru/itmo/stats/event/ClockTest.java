package ru.itmo.stats.event;

import org.junit.jupiter.api.Test;
import ru.itmo.stats.clock.ClockWithSet;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClockTest {
    private static final double EPS = 1e-8;

    private boolean equals(final double lhs, final double rhs) {
        return Math.abs(lhs - rhs) <= EPS;
    }

    @Test
    public void testNoEventsStatTime() {
        final var clock = new ClockWithSet(Instant.ofEpochSecond(0L));
        final var statsManager = new ClockEventsStatistics(clock);

        final var firstEvent = "first event";

        assertTrue(equals(statsManager.getEventStatisticsByName(firstEvent), 0.0));
        assertEquals(0, statsManager.getAllEventStatistics().size());
    }

    @Test
    public void testNoEventsChangeTime() {
        final var clock = new ClockWithSet(Instant.ofEpochSecond(0L));
        final var statsManager = new ClockEventsStatistics(clock);

        final var simpleEvent = "simple event";

        assertTrue(equals(statsManager.getEventStatisticsByName(simpleEvent), 0.0));
        assertEquals(0, statsManager.getAllEventStatistics().size());

        clock.setNow(Instant.ofEpochSecond(1234));

        assertTrue(equals(statsManager.getEventStatisticsByName(simpleEvent), 0.0));
        assertEquals(0, statsManager.getAllEventStatistics().size());

        clock.setNow(Instant.ofEpochSecond(2345));

        assertTrue(equals(statsManager.getEventStatisticsByName(simpleEvent), 0.0));
        assertEquals(0, statsManager.getAllEventStatistics().size());
    }

    @Test
    public void testOneEventStatTime() {
        final var clock = new ClockWithSet(Instant.ofEpochSecond(0L));
        final var statsManager = new ClockEventsStatistics(clock);

        final var firstEvent = "first event";
        final var secondEvent = "second event";

        statsManager.incEvent(firstEvent);

        assertTrue(equals(statsManager.getEventStatisticsByName(secondEvent), 0.0));
        assertEquals(1, statsManager.getAllEventStatistics().size());
    }

    @Test
    public void testOneEventChangeTime() {
        final var clock = new ClockWithSet(Instant.ofEpochSecond(0L));
        final var statsManager = new ClockEventsStatistics(clock);

        final var simpleEvent = "simple event";
        final var anotherEventName = "another event";

        statsManager.incEvent(simpleEvent);

        assertTrue(equals(statsManager.getEventStatisticsByName(anotherEventName), 0.0));
        assertEquals(1, statsManager.getAllEventStatistics().size());

        clock.setNow(Instant.ofEpochSecond(1234));

        assertTrue(equals(statsManager.getEventStatisticsByName(anotherEventName), 0.0));
        assertEquals(1, statsManager.getAllEventStatistics().size());

        clock.setNow(Instant.ofEpochSecond(2345));

        assertTrue(equals(statsManager.getEventStatisticsByName(anotherEventName), 0.0));
        assertEquals(1, statsManager.getAllEventStatistics().size());
    }

    @Test
    public void testOneEventOneIncrementStatTime() {
        final var clock = new ClockWithSet(Instant.ofEpochSecond(0L));
        final var statsManager = new ClockEventsStatistics(clock);

        final var firstEvent = "first event";

        statsManager.incEvent(firstEvent);

        assertTrue(equals(statsManager.getEventStatisticsByName(firstEvent), 1.0 / 60.0));
        assertEquals(1, statsManager.getAllEventStatistics().size());
    }

    @Test
    public void testOneEventOneIncrementChangeTime() {
        final var clock = new ClockWithSet(Instant.ofEpochSecond(0L));
        final var statsManager = new ClockEventsStatistics(clock);

        final var simpleEvent = "simple event";

        statsManager.incEvent(simpleEvent);

        assertTrue(equals(statsManager.getEventStatisticsByName(simpleEvent), 1.0 / 60.0));
        assertEquals(1, statsManager.getAllEventStatistics().size());

        clock.setNow(Instant.ofEpochSecond(1234));

        assertTrue(equals(statsManager.getEventStatisticsByName(simpleEvent), 1.0 / 60.0));
        assertEquals(1, statsManager.getAllEventStatistics().size());
    }

    @Test
    public void testOneEventManyIncrementsStatTime() {
        final var clock = new ClockWithSet(Instant.ofEpochSecond(0L));
        final var statsManager = new ClockEventsStatistics(clock);

        final var simpleEvent = "simple event";
        final var count = 1337;

        for (var i = 1; i < count; i++) {
            statsManager.incEvent(simpleEvent);
            assertTrue(equals(statsManager.getEventStatisticsByName(simpleEvent), i / 60.0));
            assertEquals(1, statsManager.getAllEventStatistics().size());
        }
    }

    @Test
    public void testManyEventsManyIncrementsChangeTime() {
        final var clock = new ClockWithSet(Instant.ofEpochSecond(0L));
        final var statsManager = new ClockEventsStatistics(clock);
        final var events = new String[]{"event0", "event1", "event2", "event3", "event4", "event5", "event6", "event7"};
        final var periods = new int[]{10, 11, 24, 31, 47, 53, 66, 79};
        final var count = 500;
        for (var i = 1; i <= count; i++) {
            for (var j = 0; j < events.length; j++) {
                final var eventName = events[j];
                final var period = periods[j];
                clock.setNow(Instant.ofEpochSecond(i * period));
                statsManager.incEvent(eventName);
            }
        }
        assertEquals(events.length, statsManager.getAllEventStatistics().size());
        clock.setNow(Instant.ofEpochSecond(0L));
        for (final var eventName : events) {
            assertTrue(equals(statsManager.getEventStatisticsByName(eventName), 0.0));
        }
        final var max = count * periods[periods.length - 1];
        for (var i = 0; i < max; i++) {
            clock.setNow(Instant.ofEpochSecond(i));
            for (var j = 0; j < events.length; j++) {
                final var eventName = events[j];
                final var period = periods[j];
                if (i < count * period) {
                    var first = period * ((i - 3600) / period);
                    if ((i - 3600) % period != 0) {
                        first += period;
                    }
                    final var start = Math.max(period, first);
                    final var cnt = Math.max(0, i - start + period) / period;
                    assertTrue(equals(statsManager.getEventStatisticsByName(eventName), cnt / 60.0));
                }
            }
        }
    }
}
