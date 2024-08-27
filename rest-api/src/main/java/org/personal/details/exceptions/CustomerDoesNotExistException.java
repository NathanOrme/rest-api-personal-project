package org.personal.details.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a requested customer does not exist.
 * This exception indicates that the requested resource could not be found.
 * It is mapped to the HTTP status code 404 Not Found.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomerDoesNotExistException extends RuntimeException {

    /**
     * Constructs a new {@link CustomerDoesNotExistException} with the specified detail message.
     *
     * @param message The detail message which is saved for later retrieval by the {@link Throwable#getMessage()} method.
     */
    public CustomerDoesNotExistException(final String message) {
        super(message);
    }
}