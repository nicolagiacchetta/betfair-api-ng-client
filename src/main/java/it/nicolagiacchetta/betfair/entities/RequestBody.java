package it.nicolagiacchetta.betfair.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestBody {

    @JsonProperty("filter")
    private MarketFilter marketFilter;

    private RequestBody(Builder builder) {
        this.marketFilter = builder.marketFilter;
    }

    public MarketFilter getMarketFilter() {
        return marketFilter;
    }

    public static class Builder {

        private MarketFilter marketFilter;

        public Builder(MarketFilter marketFilter) {
            this.marketFilter = marketFilter;
        }

        public RequestBody build() {
            return new RequestBody(this);
        }
    }
}
