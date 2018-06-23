package it.nicolagiacchetta.betfair.utils;

import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;
import java.util.Map;

public class HttpUtils {

    public static String appendQueryString(String uri, Map<String, String> queryParams) throws URISyntaxException {
        if(uri == null || uri.isEmpty() || queryParams == null || queryParams.isEmpty())
            return uri;
        URIBuilder builder = new URIBuilder(uri);
        for(Map.Entry<String, String> queryParam : queryParams.entrySet()) {
            builder.addParameter(queryParam.getKey(), queryParam.getValue());
        }
        return builder.toString();
    }
}
