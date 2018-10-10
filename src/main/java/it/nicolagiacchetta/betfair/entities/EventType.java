package it.nicolagiacchetta.betfair.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EventType {

    @JsonProperty
    private String id;

    @JsonProperty
    String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
