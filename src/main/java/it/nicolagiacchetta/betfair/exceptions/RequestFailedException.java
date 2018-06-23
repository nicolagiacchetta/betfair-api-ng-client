package it.nicolagiacchetta.betfair.exceptions;

public class RequestFailedException extends Exception {
    public RequestFailedException(String message) {
        super(message);
    }
}
