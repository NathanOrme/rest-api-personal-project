package org.personal.details.console.exception;

public class RequestServiceException extends RuntimeException {

    public RequestServiceException(final String message) {
        super(message);
    }

    public RequestServiceException(final String message, final Exception e) {
        super(message, e);
    }
}
