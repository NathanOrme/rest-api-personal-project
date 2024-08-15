package org.personal.details.exceptions;

public class EncryptionException extends RuntimeException {

    public EncryptionException(final String message, final Exception e) {
        super(message, e);
    }
}
