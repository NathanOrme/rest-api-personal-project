package org.personal.details.exceptions;

/**
 * Exception thrown when an error occurs during encryption or decryption operations.
 * This exception is used to signal issues related to encryption processes.
 */
public class EncryptionException extends RuntimeException {

    /**
     * Constructs a new {@link EncryptionException} with the specified detail message and cause.
     *
     * @param message The detail message which is saved for later
     *                retrieval by the {@link Throwable#getMessage()} method.
     * @param e       The cause of the exception which is saved for later
     *                retrieval by the {@link Throwable#getCause()} method.
     */
    public EncryptionException(final String message, final Exception e) {
        super(message, e);
    }
}