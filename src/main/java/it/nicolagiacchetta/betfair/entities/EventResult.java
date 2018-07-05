package it.nicolagiacchetta.betfair.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EventResult {

    @JsonProperty
    private Event event;

    @JsonProperty
    private int marketCount;

    public Event getEvent() {
        return event;
    }

    public int getMarketCount() {
        return marketCount;
    }
}
