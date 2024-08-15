package org.personal.details.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomerDoesNotExistException extends RuntimeException {

    public CustomerDoesNotExistException(final String message) {
        super(message);
    }
}
