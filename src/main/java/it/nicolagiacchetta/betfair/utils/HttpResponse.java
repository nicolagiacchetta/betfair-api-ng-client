package it.nicolagiacchetta.betfair.utils;

public class HttpResponse {

    private final int statusCode;
    private final String content;

    public HttpResponse(int statusCode, String content) {
        this.statusCode = statusCode;
        this.content = content;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getContent() {
        return content;
    }
}
