package it.nicolagiacchetta.betfair.utils;

import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

public class BetfairUtils {

    private BetfairUtils() {}

    public static Map<String, String> defaultHeaders(String appKey) {
        return defaultHeaders(appKey, null);
    }

    public static Map<String, String> defaultHeaders(String appKey, String sessionToken) {
        Map<String, String> defaultHeaders = new HashMap<>();
        defaultHeaders.put("X-Application", appKey);
        if(sessionToken != null)
            defaultHeaders.put("X-Authentication", sessionToken);
        defaultHeaders.put("Accept", MediaType.APPLICATION_JSON);
        defaultHeaders.put("Content-Type", MediaType.APPLICATION_JSON);
        return defaultHeaders;
    }
}
