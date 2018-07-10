package it.nicolagiacchetta.betfair;

import it.nicolagiacchetta.betfair.entities.LoginResponse;
import it.nicolagiacchetta.betfair.entities.LoginStatus;
import it.nicolagiacchetta.betfair.utils.HttpClient;
import it.nicolagiacchetta.betfair.utils.HttpResponse;
import it.nicolagiacchetta.betfair.utils.HttpUtils;
import org.junit.Assert;
import org.junit.Test;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static it.nicolagiacchetta.betfair.BetfairClient.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BetfairClientTest {

    public static final String USERNAME = "root";
    public static final String PASSWORD = "password";
    public static final String WRONG_PASSWORD = "wrong" + PASSWORD;
    public static final String API_KEY = "NPm69pXZoQrW7yut";
    public static final String SESSION_TOKEN = "+THAUrwK+pcW736SAaKBML9HPio3+W7hvf3S0y/5rwE=";

    private HttpClient httpClient = mock(HttpClient.class);
    private BetfairClient betfairClient = BetfairClient.builder()
                                                       .withHttpClient(httpClient)
                                                       .build();

    @Test
    public void login() throws Exception {
        testLoginWith(USERNAME, PASSWORD, SESSION_TOKEN, API_KEY, LoginStatus.SUCCESS, "");
    }

    @Test
    public void loginWrongCredentials() throws Exception {
        testLoginWith(USERNAME, WRONG_PASSWORD, "", API_KEY, LoginStatus.FAIL, "INVALID_USERNAME_OR_PASSWORD");
    }

    private void testLoginWith(String username,
                               String password,
                               String sessionToken,
                               String apiKey,
                               LoginStatus status,
                               String error) throws Exception {
        String url = buildLoginUrlWith(username, password);
        String response = buildResponse(sessionToken, apiKey, status, error);
        when(httpClient.post(eq(url), any())).thenReturn(new HttpResponse(200, response));
        LoginResponse loginResponse = betfairClient.login(username, password, apiKey);
        assertCorrectLoginResponse(loginResponse, sessionToken, apiKey, status, error);
    }

    private void assertCorrectLoginResponse(LoginResponse actualLoginResponse,
                                            String expectedToken,
                                            String expectedApiKey,
                                            LoginStatus expectedStatus,
                                            String expectedError) {
        Assert.assertEquals(expectedToken, actualLoginResponse.getToken());
        Assert.assertEquals(expectedApiKey, actualLoginResponse.getProduct());
        Assert.assertEquals(expectedStatus, actualLoginResponse.getStatus());
        Assert.assertEquals(expectedError, actualLoginResponse.getError());
    }

    private String buildLoginUrlWith(String username, String password) throws URISyntaxException {
        Map<String,String> params = new HashMap<>();
        params.put(USERNAME_PARAM, username);
        params.put(PASSWORD_PARAM, password);
        return HttpUtils.appendQueryString(LOGIN_URL, params);
    }

    private String buildResponse(String token,
                                 String product,
                                 LoginStatus status,
                                 String error) {
        String responseJson =
                "{\n" +
                "    \"token\": \"" + token + "\",\n" +
                "    \"product\": \""+ product +"\",\n" +
                "    \"status\": \"" + status.name() + "\",\n" +
                "    \"error\": \"" + error + "\"\n" +
                "}";
        return responseJson;
    }
}
