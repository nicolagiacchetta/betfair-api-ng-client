package it.nicolagiacchetta.betfair.utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class ApacheComponentsHttpClient implements HttpClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(ApacheComponentsHttpClient.class);

    private final CloseableHttpClient httpClient;

    private ApacheComponentsHttpClient(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public static ApacheComponentsHttpClient newInstance() {

        CloseableHttpClient httpClient = HttpClients.custom()
                                                    .disableContentCompression()
                                                    .build();

        return new ApacheComponentsHttpClient(httpClient);
    }

    @Override
    public HttpResponse post(String uri, Map<String, String> headers) throws IOException {
        return post(uri, headers, null);
    }

    @Override
    public HttpResponse post(String uri, Map<String, String> headers, String jsonBody) throws IOException {
        HttpPost httpPost = new HttpPost(uri);
        if(jsonBody != null) {
            StringEntity entity = new StringEntity(jsonBody, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
        }
        if(headers != null && !headers.isEmpty()) {
            for(Map.Entry<String, String> header : headers.entrySet()) {
                httpPost.addHeader(header.getKey(), header.getValue());
            }
        }
        CloseableHttpResponse response = httpClient.execute(httpPost);
        return toHttpResponse(response);
    }

    private static HttpResponse toHttpResponse(CloseableHttpResponse response) throws IOException {
        int statusCode = response.getStatusLine().getStatusCode();
        String content = StringUtils.toString(response.getEntity().getContent());
        return new HttpResponse(statusCode, content);
    }

    @Override
    public void close() {
        try {
            this.httpClient.close();
        } catch (final IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }
}
