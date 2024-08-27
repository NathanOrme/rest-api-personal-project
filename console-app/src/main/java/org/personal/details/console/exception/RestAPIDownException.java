package org.personal.details.console.exception;

/**
 * Custom runtime exception used for indicating that a REST API endpoint is down.
 * This exception is thrown when an error occurs while trying to access or interact with a REST API that is unavailable.
 */
public class RestAPIDownException extends RuntimeException {

    /**
     * Constructs a new {@code RestAPIDownException} with the specified detail message.
     *
     * @param message The detail message that explains the reason for the exception.
     */
    public RestAPIDownException(final String message) {
        super(message);
    }
}