package it.nicolagiacchetta.betfair.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonRootName(value = "filter")
public class MarketFilter {

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String textQuery;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int[] eventTypeIds;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String[] marketCountries;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int[] competitionIds;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String[] marketTypeCodes;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MarketStartTime marketStartTime;


    private MarketFilter(Builder builder) {
        this.textQuery = builder.textQuery;
        this.eventTypeIds = builder.eventTypeIds;
        this.marketCountries = builder.marketCountries;
        this.marketStartTime = builder.marketStartTime;
        this.marketTypeCodes = builder.marketTypeCodes;
        this.competitionIds = builder.competitionIds;
    }

    public String getTextQuery() {
        return textQuery;
    }

    public int[] getEventTypeIds() {
        return eventTypeIds;
    }

    public String[] getMarketCountries() {
        return marketCountries;
    }

    public int[] getCompetitionIds() {
        return competitionIds;
    }

    public String[] getMarketTypeCodes() {
        return marketTypeCodes;
    }

    public MarketStartTime getMarketStartTime() {
        return marketStartTime;
    }

    public static class Builder {

        private String textQuery;

        private int[] eventTypeIds;

        private String[] marketCountries;

        private int[] competitionIds;

        private String[] marketTypeCodes;

        private MarketStartTime marketStartTime;

        public Builder() {}

        public Builder withTextQuery(String textQuery) {
            this.textQuery = textQuery;
            return this;
        }

        public Builder withEventTypeIds(int[] eventTypeIds) {
            this.eventTypeIds = eventTypeIds;
            return this;
        }

        public Builder withMarketCountries(String[] marketCountries) {
            this.marketCountries = marketCountries;
            return this;
        }

        public Builder withCompetitionIds(int[] competitionIds) {
            this.competitionIds = competitionIds;
            return this;
        }

        public Builder withMarketTypeCodes(String[] marketTypeCodes) {
            this.marketTypeCodes = marketTypeCodes;
            return this;
        }

        public Builder withMarketStartTime(MarketStartTime marketStartTime) {
            this.marketStartTime = marketStartTime;
            return this;
        }

        public MarketFilter build() {
            return new MarketFilter(this);
        }
    }
}