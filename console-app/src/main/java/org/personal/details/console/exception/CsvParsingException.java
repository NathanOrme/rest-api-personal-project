package org.personal.details.console.exception;

/**
 * Custom runtime exception used for errors occurring during CSV parsing.
 * This exception is thrown to indicate issues encountered while parsing CSV files.
 */
public class CsvParsingException extends RuntimeException {

    /**
     * Constructs a new {@code CsvParsingException} with the specified detail message.
     *
     * @param message The detail message that explains the reason for the exception.
     */
    public CsvParsingException(final String message) {
        super(message);
    }

}