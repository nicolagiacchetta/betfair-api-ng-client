package it.nicolagiacchetta.betfair;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.nicolagiacchetta.betfair.entities.LoginResponse;
import it.nicolagiacchetta.betfair.exceptions.RequestFailedException;
import it.nicolagiacchetta.betfair.utils.ApacheComponentsHttpClient;
import it.nicolagiacchetta.betfair.utils.HttpClient;
import it.nicolagiacchetta.betfair.utils.HttpResponse;
import it.nicolagiacchetta.betfair.utils.HttpUtils;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BetfairClient {

    public static final String IDENTITY_SSO_URL = "https://identitysso.betfair.com/api";
    public static final String LOGIN_URL = IDENTITY_SSO_URL + "/login";
    public static final String SESSION_KEEPALIVE_URL = IDENTITY_SSO_URL + "/keepAlive";
    public static final String USERNAME_PARAM = "username";
    public static final String PASSWORD_PARAM = "password";

    private HttpClient httpClient;
    private ObjectMapper objectMapper;

    private BetfairClient(HttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    public LoginResponse login(String username, String password, String appKey) throws Exception {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put(USERNAME_PARAM, username);
        queryParams.put(PASSWORD_PARAM, password);
        String uri = HttpUtils.appendQueryString(LOGIN_URL, queryParams);
        Map<String, String> headers = defaultHeaders(appKey);
        HttpResponse response = this.httpClient.post(uri, headers);
        return parseHttpResponseOrFail(response, LoginResponse.class);
    }

    public LoginResponse keepAliveSession(String appKey, String sessionToken) throws Exception {
        Map<String, String> headers = defaultHeaders(appKey, sessionToken);
        HttpResponse response = this.httpClient.post(SESSION_KEEPALIVE_URL, headers);
        return parseHttpResponseOrFail(response, LoginResponse.class);
    }

    private static Map<String, String> defaultHeaders(String appKey) {
        return defaultHeaders(appKey, null);
    }

    private static Map<String, String> defaultHeaders(String appKey, String sessionToken) {
        Map<String, String> defaultHeaders = new HashMap<>();
        defaultHeaders.put("X-Application", appKey);
        if(sessionToken != null) {
            defaultHeaders.put("X-Authentication", sessionToken);
        }
        defaultHeaders.put("Accept", MediaType.APPLICATION_JSON);
        return defaultHeaders;
    }

    private <R> R parseHttpResponseOrFail(HttpResponse httpResponse, Class<R> clazz) throws RequestFailedException, IOException {
        if(httpResponse.getStatusCode() != 200) {
            throw new RequestFailedException("Request failed. Returned status code " + httpResponse.getStatusCode());
        }
        return objectMapper.readValue(httpResponse.getContent(), clazz);
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
            return new BetfairClient(this.httpClient, this.objectMapper);
        }
    }
}
