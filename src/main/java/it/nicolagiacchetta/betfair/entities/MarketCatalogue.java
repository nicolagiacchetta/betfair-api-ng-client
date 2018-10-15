package it.nicolagiacchetta.betfair.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class MarketCatalogue {

    @JsonProperty(required = true)
    private String marketId;

    @JsonProperty(required = true)
    private String marketName;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double totalMatched;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date marketStartTime;

    public String getMarketId() {
        return marketId;
    }

    public String getMarketName() {
        return marketName;
    }

    public Double getTotalMatched() {
        return totalMatched;
    }

    public Date getMarketStartTime() {
        return marketStartTime;
    }
}
