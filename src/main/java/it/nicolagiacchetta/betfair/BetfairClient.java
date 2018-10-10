package it.nicolagiacchetta.betfair;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.nicolagiacchetta.betfair.entities.EventResult;
import it.nicolagiacchetta.betfair.entities.EventTypeResult;
import it.nicolagiacchetta.betfair.entities.Filter;
import it.nicolagiacchetta.betfair.entities.LoginResponse;
import it.nicolagiacchetta.betfair.entities.RequestBody;
import it.nicolagiacchetta.betfair.exceptions.RequestFailedException;
import it.nicolagiacchetta.betfair.utils.ApacheComponentsHttpClient;
import it.nicolagiacchetta.betfair.utils.HttpClient;
import it.nicolagiacchetta.betfair.utils.HttpResponse;
import it.nicolagiacchetta.betfair.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static it.nicolagiacchetta.betfair.utils.BetfairUtils.defaultHeaders;

public class BetfairClient implements AutoCloseable {

    private final static Logger LOGGER = LoggerFactory.getLogger(BetfairClient.class);

    // Login & Session Management
    public static final String IDENTITY_SSO_URL = "https://identitysso.betfair.com/api";
    public static final String LOGIN_URL = IDENTITY_SSO_URL + "/login";
    public static final String LOGOUT_URL = IDENTITY_SSO_URL + "/logout";
    public static final String SESSION_KEEPALIVE_URL = IDENTITY_SSO_URL + "/keepAlive";
    public static final String USERNAME_PARAM = "username";
    public static final String PASSWORD_PARAM = "password";

    // Betting
    public static final String BETTING_API_URL = "https://api.betfair.com/exchange/betting/rest/v1.0";
    public static final String LIST_EVENTS_URL = BETTING_API_URL + "/listEvents/";
    public static final String LIST_EVENT_TYPES_URL = BETTING_API_URL + "/listEventTypes/";

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    private BetfairClient(Builder builder) {
        this.httpClient = Objects.requireNonNull(builder.httpClient, "HttpClient cannot be null");
        this.objectMapper = Objects.requireNonNull(builder.objectMapper, "ObjectMapper cannot be null");
    }

    public LoginResponse login(String username, String password, String appKey) throws Exception {
        checkArgumentsNonNull(username, password, appKey);
        String uri = appendCredentialsToUri(LOGIN_URL, username, password);
        LoginResponse loginResponse = sendSessionManagementRequest(appKey, uri);
        return loginResponse;
    }

    public LoginResponse keepAliveSession(String appKey, String sessionToken) throws Exception {
        checkArgumentsNonNull(appKey, sessionToken);
        return sendSessionManagementRequest(appKey, sessionToken, SESSION_KEEPALIVE_URL);
    }

    public LoginResponse logout(String appKey, String sessionToken) throws Exception {
        checkArgumentsNonNull(appKey, sessionToken);
        LoginResponse loginResponse = sendSessionManagementRequest(appKey, sessionToken, LOGOUT_URL);
        return loginResponse;
    }

    private String appendCredentialsToUri(String uri, String username, String password) throws URISyntaxException {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put(USERNAME_PARAM, username);
        queryParams.put(PASSWORD_PARAM, password);
        return HttpUtils.appendQueryString(uri, queryParams);
    }

    private LoginResponse sendSessionManagementRequest(String appKey, String url) throws Exception {
        return sendSessionManagementRequest(appKey, null, url);
    }

    private LoginResponse sendSessionManagementRequest(String appKey, String sessionToken, String url) throws Exception {
        Map<String, String> headers = defaultHeaders(appKey, sessionToken);
        HttpResponse response = this.httpClient.post(url, headers);
        return parseHttpResponseOrFail(response, LoginResponse.class);
    }

    public EventResult[] listEvents(String appKey, String sessionToken, Filter filter) throws Exception {
        return sendRequestWithFilter(LIST_EVENTS_URL, EventResult[].class, appKey, sessionToken, filter);
    }

    public EventTypeResult[] listEventTypes(String appKey, String sessionToken, Filter filter) throws Exception {
        return sendRequestWithFilter(LIST_EVENT_TYPES_URL, EventTypeResult[].class, appKey, sessionToken, filter);
    }

    private <R> R sendRequestWithFilter(String url,
                                        Class<R> returnType,
                                        String appKey,
                                        String sessionToken,
                                        Filter filter) throws Exception {
        checkArgumentsNonNull(appKey, sessionToken, filter);
        Map<String, String> headers = defaultHeaders(appKey, sessionToken);
        RequestBody body = new RequestBody.Builder(filter).build();
        String jsonBody = this.objectMapper.writeValueAsString(body);
        HttpResponse response = this.httpClient.post(url, headers, jsonBody);
        return parseHttpResponseOrFail(response, returnType);
    }

    private void checkArgumentsNonNull(Object... args) {
        for(Object arg : args)
            if(arg == null)
                throw new IllegalArgumentException("Invalid argument: null value not allowed");
    }

    private <R> R parseHttpResponseOrFail(HttpResponse httpResponse, Class<R> clazz) throws RequestFailedException, IOException {
        int statusCode = httpResponse.getStatusCode();
        if(statusCode != 200) {
            throw new RequestFailedException("Request failed. Returned status code " + statusCode, statusCode);
        }
        return this.objectMapper.readValue(httpResponse.getContent(), clazz);
    }

    @Override
    public void close() throws Exception {
        if(this.httpClient != null) this.httpClient.close();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private HttpClient httpClient;
        private ObjectMapper objectMapper;

        public Builder withHttpClient(HttpClient httpClient) {
            this.httpClient = httpClient;
            return this;
        }

        public Builder withObjectMapper(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
            return this;
        }

        public BetfairClient build() {
            if(this.httpClient == null)
                this.httpClient = ApacheComponentsHttpClient.newInstance();
            if(this.objectMapper == null)
                this.objectMapper = new ObjectMapper();
            return new BetfairClient(this);
        }
    }
}
