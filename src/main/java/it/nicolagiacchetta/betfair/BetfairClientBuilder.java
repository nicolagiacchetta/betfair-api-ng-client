package it.nicolagiacchetta.betfair;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.nicolagiacchetta.betfair.utils.HttpClient;
import it.nicolagiacchetta.betfair.validation.ValidationHandler;

import java.lang.reflect.Proxy;
import java.util.Locale;

public class BetfairClientBuilder {

    private HttpClient httpClient;
    private ObjectMapper objectMapper;
    private Locale locale;

    protected BetfairClientBuilder(){}

    public static BetfairClientBuilder create(){
        return new BetfairClientBuilder();
    }

    public BetfairClientBuilder withHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
        return this;
    }

    public BetfairClientBuilder withObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        return this;
    }

    public BetfairClientBuilder withLocale(Locale locale) {
        this.locale = locale;
        return this;
    }

    public BetfairClient build() {
        BetfairClientImpl betfairClient = new BetfairClientImpl(this.httpClient, this.objectMapper, this.locale);
        BetfairClient validationProxy = (BetfairClient) Proxy.newProxyInstance(
                BetfairClientImpl.class.getClassLoader(),
                new Class[] { BetfairClient.class },
                new ValidationHandler(betfairClient));
        return validationProxy;
    }
}
