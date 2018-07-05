package it.nicolagiacchetta.betfair.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class MarketStartTime {

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date from;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date to;

    public MarketStartTime(Date from, Date to) {
        this.from = from;
        this.to = to;
    }

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }
}
