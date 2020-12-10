package ru.itmo.stats.event;

import java.util.Map;

public interface EventsStatistics {

    void incEvent(String name);

    double getEventStatisticsByName(String name);

    Map<String, Double> getAllEventStatistics();

    void printStatistics();
}
