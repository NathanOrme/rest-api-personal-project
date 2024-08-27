package org.personal.details.console.exception;

/**
 * Custom runtime exception used in the console application.
 * This exception is thrown to wrap other exceptions that occur
 * during the execution of the console application tasks.
 */
public class ConsoleAppException extends RuntimeException {

    /**
     * Constructs a new {@code ConsoleAppException} with the specified cause.
     *
     * @param e The underlying exception that caused this exception to be thrown.
     */
    public ConsoleAppException(final Exception e) {
        super(e);
    }
}