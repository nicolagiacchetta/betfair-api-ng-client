package it.nicolagiacchetta.betfair.utils;

import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;
import java.util.Map;

import static it.nicolagiacchetta.betfair.utils.MiscUtils.isNullOrEmpty;
import static it.nicolagiacchetta.betfair.utils.StringUtils.isNullOrEmpty;

public class HttpUtils {

    private HttpUtils() {}

    public static String appendQueryString(String uri, Map<String, String> queryParams) throws URISyntaxException {
        if(isNullOrEmpty(uri) || isNullOrEmpty(queryParams))
            return uri;
        URIBuilder builder = new URIBuilder(uri);
        for(Map.Entry<String, String> queryParam : queryParams.entrySet()) {
            builder.addParameter(queryParam.getKey(), queryParam.getValue());
        }
        return builder.toString();
    }
}
