package org.personal.details.controller;

import lombok.extern.slf4j.Slf4j;
import org.personal.details.exceptions.CustomerDoesNotExistException;
import org.personal.details.exceptions.CustomerRefExistsException;
import org.personal.details.exceptions.EncryptionException;
import org.personal.details.model.ErrorMessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for the Personal Details application.
 * This class handles specific exceptions and maps them to appropriate HTTP responses.
 */
@Slf4j
@ControllerAdvice
@SuppressWarnings("unused")
public class PersonalDetailsControllerAdvice {

    /**
     * Generates an error message response entity with the provided HTTP status and error message.
     *
     * @param httpStatus   The HTTP status to be returned in the response.
     * @param errorMessage The error message to be included in the response body.
     * @return ResponseEntity containing the error message and HTTP status.
     */
    private static ResponseEntity<ErrorMessageDTO> generateErrorMessage(final HttpStatus httpStatus, final String errorMessage) {
        log.error(errorMessage);
        ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO(errorMessage);
        return ResponseEntity.status(httpStatus).body(errorMessageDTO);
    }

    /**
     * Handles {@link CustomerRefExistsException} by returning a 422 Unprocessable Entity status with an error message.
     *
     * @param exception The exception thrown when a customer reference already exists.
     * @return ResponseEntity containing the error message and a 422 Unprocessable Entity status.
     */
    @ExceptionHandler(value = {CustomerRefExistsException.class})
    protected ResponseEntity<ErrorMessageDTO> handleCustomerExistsException(final CustomerRefExistsException exception) {
        return generateErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage());
    }

    /**
     * Handles {@link CustomerDoesNotExistException} by returning a 404 Not Found status with an error message.
     *
     * @param exception The exception thrown when a customer reference does not exist.
     * @return ResponseEntity containing the error message and a 404 Not Found status.
     */
    @ExceptionHandler(value = {CustomerDoesNotExistException.class})
    protected ResponseEntity<ErrorMessageDTO> handleCustomerDoesNotExistException(final CustomerDoesNotExistException exception) {
        return generateErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    /**
     * Handles {@link EncryptionException} by returning a 500 Internal Server Error status with an error message.
     *
     * @param exception The exception thrown when an encryption error occurs.
     * @return ResponseEntity containing the error message and a 500 Internal Server Error status.
     */
    @ExceptionHandler(value = {EncryptionException.class})
    protected ResponseEntity<ErrorMessageDTO> handleEncryptionException(final EncryptionException exception) {
        return generateErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }
}