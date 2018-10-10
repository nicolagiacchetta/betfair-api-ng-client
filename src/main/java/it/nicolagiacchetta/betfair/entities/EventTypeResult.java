package it.nicolagiacchetta.betfair.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EventTypeResult {


    @JsonProperty
    private EventType eventType;

    @JsonProperty
    private int marketCount;


    public EventType getEventType() {
        return eventType;
    }

    public int getMarketCount() {
        return marketCount;
    }

}
