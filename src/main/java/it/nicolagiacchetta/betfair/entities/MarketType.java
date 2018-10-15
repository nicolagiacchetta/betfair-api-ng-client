package it.nicolagiacchetta.betfair.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MarketType {

    @JsonProperty(required = true)
    private String marketType;

    @JsonProperty
    private int marketCount;

    public String getMarketType() {
        return marketType;
    }

    public int getMarketCount() {
        return marketCount;
    }
}
