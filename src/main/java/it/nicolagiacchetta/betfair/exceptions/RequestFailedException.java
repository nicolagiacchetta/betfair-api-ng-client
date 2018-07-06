package it.nicolagiacchetta.betfair.exceptions;

public class RequestFailedException extends Exception {

    private final int statusCode;

    public RequestFailedException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
