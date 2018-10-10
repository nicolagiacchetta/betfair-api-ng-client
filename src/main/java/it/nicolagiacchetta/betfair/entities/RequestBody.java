package it.nicolagiacchetta.betfair.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestBody {

    @JsonProperty("filter")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MarketFilter marketFilter;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer maxResults;

    private RequestBody(Builder builder) {
        this.marketFilter = builder.marketFilter;
        this.maxResults = builder.maxResults;
    }

    public MarketFilter getMarketFilter() {
        return marketFilter;
    }

    public Integer getMaxResults() {
        return maxResults;
    }

    public static class Builder {

        private MarketFilter marketFilter;
        private Integer maxResults;

        public Builder() {
        }

        public Builder withMarketFilter(MarketFilter marketFilter) {
            this.marketFilter = marketFilter;
            return this;
        }

        public Builder withMaxResults(Integer maxResults) {
            this.maxResults = maxResults;
            return this;
        }

        public RequestBody build() {
            return new RequestBody(this);
        }
    }
}
