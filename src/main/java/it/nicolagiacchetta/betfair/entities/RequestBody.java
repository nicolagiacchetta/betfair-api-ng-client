package it.nicolagiacchetta.betfair.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestBody {

    @JsonProperty
    private Filter filter;

    private RequestBody(Builder builder) {
        this.filter = builder.filter;
    }

    public Filter getFilter() {
        return filter;
    }

    public static class Builder {

        private Filter filter;

        public Builder(Filter filter) {
            this.filter = filter;
        }

        public RequestBody build() {
            return new RequestBody(this);
        }
    }
}
