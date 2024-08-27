package org.personal.details.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a customer reference already exists and cannot be processed.
 * This exception indicates that the request cannot be completed due to a conflict
 * with the current state of the resource.
 * It is mapped to the HTTP status code 400 Bad Request.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerRefExistsException extends RuntimeException {

    /**
     * Constructs a new {@link CustomerRefExistsException} with the specified detail message.
     *
     * @param message The detail message which is saved for later retrieval by the {@link Throwable#getMessage()} method.
     */
    public CustomerRefExistsException(final String message) {
        super(message);
    }
}