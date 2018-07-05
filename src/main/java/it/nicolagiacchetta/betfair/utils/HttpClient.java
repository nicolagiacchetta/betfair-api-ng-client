package it.nicolagiacchetta.betfair.utils;

import java.util.Map;

public interface HttpClient extends AutoCloseable {
    HttpResponse post(String uri, Map<String, String> headers) throws Exception;
    HttpResponse post(String uri, Map<String, String> headers, String jsonBody) throws Exception;
}
