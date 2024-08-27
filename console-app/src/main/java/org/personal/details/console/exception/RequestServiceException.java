package org.personal.details.console.exception;

/**
 * Custom runtime exception used for errors occurring in the request service.
 * This exception is thrown to indicate issues encountered while making or processing HTTP requests.
 */
public class RequestServiceException extends RuntimeException {

    /**
     * Constructs a new {@code RequestServiceException} with the specified detail message.
     *
     * @param message The detail message that explains the reason for the exception.
     */
    public RequestServiceException(final String message) {
        super(message);
    }

    /**
     * Constructs a new {@code RequestServiceException} with the specified detail message and cause.
     *
     * @param message The detail message that explains the reason for the exception.
     * @param e       The underlying exception that caused this exception to be thrown.
     */
    public RequestServiceException(final String message, final Exception e) {
        super(message, e);
    }
}