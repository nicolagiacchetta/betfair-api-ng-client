package it.nicolagiacchetta.betfair.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {

    @JsonProperty
    private String token;

    @JsonProperty
    private String product;


    @JsonProperty
    private LoginStatus status;

    @JsonProperty
    private String error;

    public String getToken() {
        return token;
    }

    public String getProduct() {
        return product;
    }

    public LoginStatus getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }
}
