package it.nicolagiacchetta.betfair;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.nicolagiacchetta.betfair.annotations.Required;
import it.nicolagiacchetta.betfair.entities.EventResult;
import it.nicolagiacchetta.betfair.entities.EventTypeResult;
import it.nicolagiacchetta.betfair.entities.LoginResponse;
import it.nicolagiacchetta.betfair.entities.MarketCatalogue;
import it.nicolagiacchetta.betfair.entities.MarketFilter;
import it.nicolagiacchetta.betfair.entities.MarketType;
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
import java.util.Locale;
import java.util.Map;

import static it.nicolagiacchetta.betfair.utils.BetfairUtils.defaultHeaders;


public class BetfairClientImpl implements BetfairClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(BetfairClientImpl.class);

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
    public static final String LIST_MARKET_CATALOGUE_URL = BETTING_API_URL + "/listMarketCatalogue/";
    public static final String LIST_MARKET_TYPES_URL = BETTING_API_URL + "/listMarketTypes/";

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final Locale locale;

    protected BetfairClientImpl(HttpClient httpClient, ObjectMapper objectMapper, Locale locale) {
        this.httpClient = httpClient == null ? ApacheComponentsHttpClient.newInstance() : httpClient;
        this.objectMapper = objectMapper == null ? new ObjectMapper() : objectMapper;
        this.locale = locale == null ? Locale.UK : locale;
    }

    @Override
    public LoginResponse login(@Required String username,
                               @Required String password,
                               @Required String appKey) throws Exception {
        String uri = appendCredentialsToUri(LOGIN_URL, username, password);
        return sendSessionManagementRequest(appKey, uri);
    }

    @Override
    public LoginResponse keepAliveSession(@Required String appKey,
                                          @Required String sessionToken) throws Exception {
        return sendSessionManagementRequest(appKey, sessionToken, SESSION_KEEPALIVE_URL);
    }

    @Override
    public LoginResponse logout(@Required String appKey,
                                @Required String sessionToken) throws Exception {
        LoginResponse loginResponse = sendSessionManagementRequest(appKey, sessionToken, LOGOUT_URL);
        return loginResponse;
    }

    private String appendCredentialsToUri(String uri,
                                          String username,
                                          String password) throws URISyntaxException {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put(USERNAME_PARAM, username);
        queryParams.put(PASSWORD_PARAM, password);
        return HttpUtils.appendQueryString(uri, queryParams);
    }

    private LoginResponse sendSessionManagementRequest(String appKey,
                                                       String url) throws Exception {
        return sendSessionManagementRequest(appKey, null, url);
    }

    private LoginResponse sendSessionManagementRequest(String appKey,
                                                       String sessionToken,
                                                       String url) throws Exception {
        Map<String, String> headers = defaultHeaders(appKey, sessionToken);
        HttpResponse response = this.httpClient.post(url, headers);
        LoginResponse loginResponse = parseHttpResponseOrFail(response, LoginResponse.class);
        return loginResponse;
    }

    public EventResult[] listEvents(@Required String appKey,
                                    @Required String sessionToken,
                                    @Required MarketFilter marketFilter) throws Exception {
        return listEvents(appKey, sessionToken, marketFilter, this.locale);
    }

    @Override
    public EventResult[] listEvents(@Required String appKey,
                                    @Required String sessionToken,
                                    @Required MarketFilter marketFilter,
                                    Locale locale) throws Exception {
        return sendRequest(LIST_EVENTS_URL, EventResult[].class, appKey, sessionToken, marketFilter, null, locale);
    }

    public EventTypeResult[] listEventTypes(@Required String appKey,
                                            @Required String sessionToken,
                                            @Required MarketFilter marketFilter) throws Exception {
        return listEventTypes(appKey, sessionToken, marketFilter, this.locale);
    }

    @Override
    public EventTypeResult[] listEventTypes(@Required String appKey,
                                            @Required String sessionToken,
                                            @Required MarketFilter marketFilter,
                                            Locale locale) throws Exception {
        return sendRequest(LIST_EVENT_TYPES_URL, EventTypeResult[].class, appKey, sessionToken, marketFilter, null, locale);
    }

    public MarketType[] listMarketTypes(@Required String appKey,
                                        @Required String sessionToken,
                                        @Required MarketFilter marketFilter) throws Exception {
        return listMarketTypes(appKey, sessionToken, marketFilter, this.locale);
    }

    @Override
    public MarketType[] listMarketTypes(@Required String appKey,
                                        @Required String sessionToken,
                                        @Required MarketFilter marketFilter,
                                        Locale locale) throws Exception {
        return sendRequest(LIST_MARKET_TYPES_URL, MarketType[].class, appKey, sessionToken, marketFilter, null, locale);
    }

    public MarketCatalogue[] listMarketCatalogue(@Required String appKey,
                                                 @Required String sessionToken,
                                                 @Required MarketFilter marketFilter,
                                                 Integer maxResults) throws Exception {
        return listMarketCatalogue(appKey, sessionToken, marketFilter, maxResults, this.locale);
    }

    @Override
    public MarketCatalogue[] listMarketCatalogue(@Required String appKey,
                                                 @Required String sessionToken,
                                                 @Required MarketFilter marketFilter,
                                                 Integer maxResults,
                                                 Locale locale) throws Exception {
        return sendRequest(LIST_MARKET_CATALOGUE_URL, MarketCatalogue[].class, appKey, sessionToken, marketFilter, maxResults, locale);
    }

    private <R> R sendRequest(String url,
                              Class<R> returnType,
                              String appKey,
                              String sessionToken,
                              MarketFilter marketFilter,
                              Integer maxResults,
                              Locale locale) throws Exception {
        Map<String, String> headers = defaultHeaders(appKey, sessionToken);
        RequestBody body = new RequestBody.Builder()
                                          .withMarketFilter(marketFilter)
                                          .withMaxResults(maxResults)
                                          .withLocale(locale)
                                          .build();
        String jsonBody = this.objectMapper.writeValueAsString(body);
        HttpResponse response = this.httpClient.post(url, headers, jsonBody);
        return parseHttpResponseOrFail(response, returnType);
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

}
