package it.nicolagiacchetta.betfair.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Locale;

public class RequestBody {

    @JsonProperty("filter")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MarketFilter marketFilter;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer maxResults;

    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Locale locale;

    private RequestBody(Builder builder) {
        this.marketFilter = builder.marketFilter;
        this.maxResults = builder.maxResults;
        this.locale = builder.locale;
    }

    public MarketFilter getMarketFilter() {
        return marketFilter;
    }

    public Integer getMaxResults() {
        return maxResults;
    }

    public Locale getLocale() {
        return locale;
    }

    public static class Builder {

        private MarketFilter marketFilter;
        private Integer maxResults;
        private Locale locale;

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

        public Builder withLocale(Locale locale) {
            this.locale = locale;
            return this;
        }

        public RequestBody build() {
            return new RequestBody(this);
        }
    }
}
