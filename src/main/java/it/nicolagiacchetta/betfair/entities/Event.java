package it.nicolagiacchetta.betfair.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Event {

    @JsonProperty
    private String id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String countryCode;

    @JsonProperty
    private String timezone;

    @JsonProperty
    private Date openDate;

    @JsonProperty
    private String venue;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getTimezone() {
        return timezone;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public String getVenue() {
        return venue;
    }
}

